package com.sharonahamon.petsleuth.ui.list

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sharonahamon.petsleuth.R
import com.sharonahamon.petsleuth.databinding.PetCardFragmentBinding
import com.sharonahamon.petsleuth.common.PetSleuthViewModel
import timber.log.Timber

class PetCardFragment : Fragment() {

    private lateinit var viewModel: PetSleuthViewModel

    private lateinit var binding: PetCardFragmentBinding

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

        binding = DataBindingUtil.inflate(inflater, R.layout.pet_card_fragment, container, false)

        binding.petSleuthViewModel = viewModel
        binding.lifecycleOwner = this

        binding.listItemImage.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_listFragment_to_detailFragment))

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("called OnDestroyView")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("called OnCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("called OnDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.i("called OnDetach")
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        Timber.i("called OnInflate")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("called OnPause")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("called OnResume")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("called OnSaveInstanceState")
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        Timber.i("called OnAttachFragment")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("called OnStart")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("called OnStop")
    }
}