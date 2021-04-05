package com.sharonahamon.petsleuth.screens.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sharonahamon.petsleuth.R
import com.sharonahamon.petsleuth.common.PetSleuthViewModel
import com.sharonahamon.petsleuth.databinding.WelcomeFragmentBinding
import timber.log.Timber

class WelcomeFragment : Fragment() {

    private lateinit var viewModel: PetSleuthViewModel

    private lateinit var binding: WelcomeFragmentBinding

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

        binding = DataBindingUtil.inflate(inflater, R.layout.welcome_fragment, container, false)

        binding.petSleuthViewModel = viewModel
        binding.lifecycleOwner = this

        // Set the onClickListener for the buttons
        binding.welcomeButtonAdd.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_instructionsFragment))
        binding.welcomeButtonList.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_listItemFragment))

        return binding.root
    }
}