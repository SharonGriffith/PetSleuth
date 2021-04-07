package com.sharonahamon.petsleuth.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.sharonahamon.petsleuth.R
import com.sharonahamon.petsleuth.common.PetSleuthViewModel
import com.sharonahamon.petsleuth.data.PetLD
import com.sharonahamon.petsleuth.databinding.DetailFragmentBinding
import timber.log.Timber

class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()

    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    private val viewModel: PetSleuthViewModel by activityViewModels()

    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)

        binding.selectedPet = viewModel.selectedPet.value
        binding.lifecycleOwner = this

        binding.detailButtonAddNew.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_instructionsFragment))
        binding.detailButtonList.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_listItemFragment))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var requestedPetId = args.petId
        Timber.i("requestedPetId=%s", requestedPetId)

        // save off the petId in the view model, to maintain state
        viewModel.requestedPetId = MutableLiveData<Int>(requestedPetId)

        viewModel.selectedPet.observe(viewLifecycleOwner, Observer<PetLD> {
            // retrieve the requested pet ID from the list
            viewModel.selectPet(requestedPetId)
        })
    }
}