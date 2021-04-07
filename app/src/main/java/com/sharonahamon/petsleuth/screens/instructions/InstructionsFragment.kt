package com.sharonahamon.petsleuth.screens.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.sharonahamon.petsleuth.R
import com.sharonahamon.petsleuth.common.PetSleuthViewModel
import com.sharonahamon.petsleuth.databinding.InstructionsFragmentBinding
import timber.log.Timber

class InstructionsFragment : Fragment() {

    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    private val viewModel: PetSleuthViewModel by activityViewModels()

    private lateinit var binding: InstructionsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i("called OnCreateView")

        binding =
            DataBindingUtil.inflate(inflater, R.layout.instructions_fragment, container, false)

        binding.lifecycleOwner = this

        // create a new Pet object with null petId since that will be determined when the new pet is saved
        viewModel.selectedPet = MutableLiveData(
            viewModel.createPet(
                viewModel.currentUserEmail.value.toString(),
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                viewModel.getToday(),
                null
            )
        )

        // send the new Pet object to the fragment to work with
        binding.newPet = viewModel.selectedPet.value

        binding.instructionsButtonCancel.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_instructionsFragment_to_listItemFragment
            )
        )

        binding.instructionsButtonSave.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            savePet(view)
        }

        return binding.root
    }

    private fun savePet(view: View) {
        // pick up the values from the radio buttons
        viewModel.selectedPet.value?.petSummary?.value?.status?.value = getStatusDataFromUserInput()
        viewModel.selectedPet.value?.petSummary?.value?.status?.value =
            getSpeciesDataFromUserInput()
        viewModel.selectedPet.value?.petDetail?.value?.sex?.value = getSexDataFromUserInput()
        viewModel.selectedPet.value?.petDetail?.value?.breed?.value = getBreedDataFromUserInput()

        // add the current pet to the pet list
        viewModel.addPetToList(viewModel.selectedPet.value!!)

        val action =
            InstructionsFragmentDirections.actionInstructionsFragmentToDetailFragment(viewModel.selectedPet.value?.petId?.value!!)

        view.findNavController().navigate(action)
    }

    private fun getStatusDataFromUserInput(): String {
        var status = "Lost"
        val checkedId = binding.instructionsRadioPurposeContainer.checkedRadioButtonId

        // Do nothing if nothing is checked (id == -1)
        if (-1 != checkedId) {
            when (checkedId) {
                R.id.instructions_radio_purpose_found -> status = "Found"
                R.id.instructions_radio_purpose_lost -> status = "Lost"
            }
        }

        Timber.i("status=%s", status)
        return status
    }

    private fun getSexDataFromUserInput(): String {
        var sex = "Male"
        val checkedId = binding.instructionsRadioSexContainer.checkedRadioButtonId

        // Do nothing if nothing is checked (id == -1)
        if (-1 != checkedId) {
            when (checkedId) {
                R.id.instructions_radio_sex_male -> sex = "Male"
                R.id.instructions_radio_sex_female -> sex = "Female"
            }
        }

        Timber.i("sex=%s", sex)
        return sex
    }

    private fun getBreedDataFromUserInput(): String {
        val breed = binding.instructionsAnswerBreed.text.toString()

        Timber.i("breed=%s", breed)
        return breed
    }

    private fun getZipDataFromUserInput(): String {
        val zip = binding.instructionsAnswerLocationZip.text.toString()

        Timber.i("zip=%s", zip)
        return zip
    }

    private fun getStateDataFromUserInput(): String {
        val state = binding.instructionsAnswerLocationState.text.toString()

        Timber.i("state=%s", state)
        return state
    }

    private fun getCityDataFromUserInput(): String {
        val city = binding.instructionsAnswerLocationCity.text.toString()

        Timber.i("city=%s", city)
        return city
    }

    private fun getSpeciesDataFromUserInput(): String {
        var species = ""
        val checkedId = binding.instructionsRadioSpeciesContainer.checkedRadioButtonId

        // Do nothing if nothing is checked (id == -1)
        if (-1 != checkedId) {
            when (checkedId) {
                R.id.instructions_radio_species_dog -> species = "Dog"
                R.id.instructions_radio_species_cat -> species = "Cat"
            }
        }

        Timber.i("species=%s", species)
        return species
    }
}