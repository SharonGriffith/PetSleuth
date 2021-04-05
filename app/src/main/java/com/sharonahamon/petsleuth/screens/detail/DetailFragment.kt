package com.sharonahamon.petsleuth.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.sharonahamon.petsleuth.R
import com.sharonahamon.petsleuth.common.PetSleuthViewModel
import com.sharonahamon.petsleuth.databinding.DetailFragmentBinding
import timber.log.Timber

class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()

    private lateinit var viewModel: PetSleuthViewModel

    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i("called OnCreateView")

        // get the existing instance of the viewModel instead of creating a new one
        // tie the viewModel to the parent activity so that it does not get
        // destroyed when a fragment is popped off the back stack
        viewModel = ViewModelProvider(requireActivity()).get(PetSleuthViewModel::class.java)
        Timber.i("called ViewModelProvider")

        binding =
            DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)

        binding.petSleuthViewModel = viewModel
        binding.lifecycleOwner = this

        binding.detailButtonAddNew.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_instructionsFragment))
        binding.detailButtonList.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_listItemFragment))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("called OnViewCreated")

        var petId = args.petId
        Timber.i("DetailFragment petId=%s", petId)

        // save off the petId in the view model, to maintain state
        viewModel.currentPetId = MutableLiveData<Int>(petId)

        // retrieve the requested pet ID from the list
        viewModel.loadPet(petId)
    }
}