package com.sharonahamon.petsleuth.screens.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.sharonahamon.petsleuth.R
import com.sharonahamon.petsleuth.common.PetSleuthViewModel
import com.sharonahamon.petsleuth.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment() {

    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    private val viewModel: PetSleuthViewModel by activityViewModels()

    private lateinit var binding: WelcomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.welcome_fragment, container, false)

        binding.petSleuthViewModel = viewModel
        binding.lifecycleOwner = this

        // Set the onClickListener for the buttons
        binding.welcomeButtonAdd.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_instructionsFragment))
        binding.welcomeButtonList.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_listItemFragment))

        return binding.root
    }
}