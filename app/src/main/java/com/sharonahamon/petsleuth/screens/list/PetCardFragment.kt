package com.sharonahamon.petsleuth.screens.list

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sharonahamon.petsleuth.R
import com.sharonahamon.petsleuth.databinding.PetCardFragmentBinding
import timber.log.Timber

class PetCardFragment : Fragment() {

    private lateinit var binding: PetCardFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i("called OnCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.pet_card_fragment, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }
}