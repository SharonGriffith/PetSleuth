package com.sharonahamon.petsleuth

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.sharonahamon.petsleuth.databinding.InstructionsFragmentBinding
import com.sharonahamon.petsleuth.models.Pet
import com.sharonahamon.petsleuth.models.PetDetail
import com.sharonahamon.petsleuth.models.PetLastSeenLocation
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InstructionsFragment : Fragment() {

    private lateinit var viewModel: PetSleuthViewModel

    private lateinit var binding: InstructionsFragmentBinding

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
            DataBindingUtil.inflate(inflater, R.layout.instructions_fragment, container, false)

        binding.petSleuthViewModel = viewModel
        binding.lifecycleOwner = this

        binding.instructionsButtonSave.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            saveDataFromUserInputToViewModel()
            view.findNavController().navigate(R.id.action_instructionsFragment_to_detailFragment)
        }

        return binding.root
    }

    private fun saveDataFromUserInputToViewModel() {
        getSpeciesDataFromUserInput(viewModel.pet)
        getDescriptionDataFromUserInput(viewModel.pet)
        getLocationDataFromUserInput(viewModel.pet)

        // add the current pet to the list in viewModel
        Timber.i("saved the Pet object in the list")
        viewModel.petList.add(viewModel.pet)
    }

    private fun getDescriptionDataFromUserInput(pet: LiveData<Pet>) {
        val description = binding.instructionsAnswerDescription.text.toString()

        // create the PetDetail object for the first time
        Timber.i("created the PetDetail object")

        val petDetail = PetDetail(
            pet.value?.petId,
            MutableLiveData(description),
            null,
            MutableLiveData(false),
            viewModel.contactPerson
        )

        Timber.i("updated (but not saved) the PetDetail object")
        pet.value?.petDetail = MutableLiveData(petDetail)
    }

    private fun getLocationDataFromUserInput(pet: LiveData<Pet>) {
        val city = binding.instructionsAnswerLocationCity.text.toString()
        val state = binding.instructionsAnswerLocationState.text.toString()
        val zip = binding.instructionsAnswerLocationZip.text.toString()

        val currentDateTime = LocalDateTime.now()
        val today = currentDateTime.format(DateTimeFormatter.ofPattern("MM/dd/yy"))

        // create the PetLastSeenLocation object for the first time
        Timber.i("created the PetLastSeenLocation object")
        val petLastSeenLocation = PetLastSeenLocation(
            pet.value?.petId,
            MutableLiveData(today),
            null,
            MutableLiveData(city),
            MutableLiveData(state),
            MutableLiveData(zip)
        )

        Timber.i("updated (but not saved) the PetLastSeenLocation object")
        pet.value?.petLastSeenLocation = MutableLiveData(petLastSeenLocation)
    }

    private fun getSpeciesDataFromUserInput(pet: LiveData<Pet>) {
        var species = ""
        val checkedId = binding.instructionsRadioSpeciesContainer.checkedRadioButtonId

        // Do nothing if nothing is checked (id == -1)
        if (-1 != checkedId) {
            when (checkedId) {
                R.id.instructions_radio_species_dog -> species = "Dog"
                R.id.instructions_radio_species_cat -> species = "Cat"
            }
        }

        Timber.i("updated (but not saved) the PetSummary object")
        pet.value?.petSummary?.value?.species = MutableLiveData(species)
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