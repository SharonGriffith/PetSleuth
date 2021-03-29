package com.sharonahamon.petsleuth

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.sharonahamon.petsleuth.databinding.WelcomeFragmentBinding
import com.sharonahamon.petsleuth.models.Pet
import com.sharonahamon.petsleuth.models.PetSummary
import timber.log.Timber

class WelcomeFragment : Fragment() {

    private lateinit var viewModel: PetSleuthViewModel

    private lateinit var binding: WelcomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("called OnCreateView")

        // get the existing instance of the viewModel instead of creating a new one
        // tie the viewModel to the parent activity so that it does not get
        // destroyed when a fragment is popped off the back stack
        viewModel = ViewModelProvider(requireActivity()).get(PetSleuthViewModel::class.java)
        Timber.i("called ViewModelProvider")

        binding = DataBindingUtil.inflate(inflater, R.layout.welcome_fragment, container, false)

        binding.petSleuthViewModel = viewModel
        binding.lifecycleOwner = this

        // Set the onClickListener for the submitButton
        binding.welcomeButtonNext.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            saveDataFromUserInputToViewModel()
            view.findNavController().navigate(R.id.action_welcomeFragment_to_instructionsFragment)
        }

        return binding.root
    }

    private fun saveDataFromUserInputToViewModel() {
        var status = "Lost"
        val checkedId = binding.welcomeRadioPurposeContainer.checkedRadioButtonId

        // Do nothing if nothing is checked (id == -1)
        if (-1 != checkedId) {
            when (checkedId) {
                R.id.welcome_radio_purpose_found -> status = "Found"
                R.id.welcome_radio_purpose_lost -> status = "Lost"
            }
        }

        var isMine = true
        if (status == "Found") {
            isMine = false
        }

        var nextPetId = viewModel.petList.size + 1

        // create the PetSummary object for the first time
        Timber.i("created the PetSummary object")

        var petSummary = PetSummary(
            MutableLiveData(nextPetId),
            null,
            MutableLiveData(isMine),
            MutableLiveData(false),
            MutableLiveData(status)
        )

        // create the Pet object for the first time
        Timber.i("created the Pet object")

        var pet = Pet(MutableLiveData(nextPetId), MutableLiveData(petSummary), null, null)

        // save the current (incomplete) Pet in viewModel so the other fragments can add to it
        Timber.i("saved the Pet object")

        viewModel.pet = MutableLiveData(pet)
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