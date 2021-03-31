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
import com.sharonahamon.petsleuth.databinding.InstructionsFragmentBinding
import com.sharonahamon.petsleuth.models.*
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
        savePet(createPet())

        // add the current pet to the list in viewModel
        Timber.i("saved the Pet object in the list")
        viewModel.petList.add(viewModel.pet)
    }

    private fun createPet(): Pet {
        val nextPetId = viewModel.petList.size + 1
        Timber.i("the next pet ID to be used is %s", nextPetId)

        // create the Pet object for the first time
        val pet = Pet(
            MutableLiveData(nextPetId),
            MutableLiveData(createPetSummary(nextPetId)),
            MutableLiveData(createPetDetail(nextPetId)),
            MutableLiveData(createPetLastSeenLocation(nextPetId))
        )
        Timber.i("created the Pet object")

        return pet
    }

    private fun savePet(pet: Pet) {
        viewModel.pet = MutableLiveData(pet)
        Timber.i("saved the Pet object")
    }

    private fun createPetSummary(petId: Int): PetSummary? {
        val petSummary = PetSummary(
            MutableLiveData(petId),
            MutableLiveData(getSpeciesDataFromUserInput()),
            null,
            null,
            MutableLiveData(getStatusDataFromUserInput())
        )

        Timber.i("created the PetSummary object")
        return petSummary
    }

    private fun getStatusDataFromUserInput(): String? {
        var status = "Lost"
        val checkedId = binding.instructionsRadioPurposeContainer.checkedRadioButtonId

        // Do nothing if nothing is checked (id == -1)
        if (-1 != checkedId) {
            when (checkedId) {
                R.id.instructions_radio_purpose_found -> status = "Found"
                R.id.instructions_radio_purpose_lost -> status = "Lost"
            }
        }

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

        return sex
    }

    private fun createPetDetail(petId: Int): PetDetail {
        val petDetail = PetDetail(
            MutableLiveData(petId),
            MutableLiveData(getBreedDataFromUserInput()),
            null,
            MutableLiveData(false),
            viewModel.contactPerson as @kotlinx.android.parcel.RawValue MutableLiveData<ContactPerson>,
            null,
            MutableLiveData(getSexDataFromUserInput())
        )

        Timber.i("created the PetDetail object")
        return petDetail
    }

    private fun getBreedDataFromUserInput(): String? {
        val breed = binding.instructionsAnswerBreed.text.toString()
        return breed
    }

    private fun createPetLastSeenLocation(nextPetId: Int): PetLastSeenLocation {
        val city = getCityDataFromUserInput()
        val state = getStateDataFromUserInput()
        val zip = getZipDataFromUserInput()


        // create the PetLastSeenLocation object for the first time
        val petLastSeenLocation = PetLastSeenLocation(
            MutableLiveData(nextPetId),
            MutableLiveData(getToday()),
            null,
            MutableLiveData(city),
            MutableLiveData(state),
            MutableLiveData(zip)
        )

        Timber.i("created the PetLastSeenLocation object")
        return petLastSeenLocation
    }

    private fun getToday(): String? {
        val currentDateTime = LocalDateTime.now()
        val today = currentDateTime.format(DateTimeFormatter.ofPattern("MM/dd/yy"))

        Timber.i("today= %s", today)
        return today
    }

    private fun getZipDataFromUserInput(): String? {
        val zip = binding.instructionsAnswerLocationZip.text.toString()

        Timber.i("zip= %s", zip)
        return zip
    }

    private fun getStateDataFromUserInput(): String? {
        val state = binding.instructionsAnswerLocationState.text.toString()

        Timber.i("state= %s", state)
        return state
    }

    private fun getCityDataFromUserInput(): String? {
        val city = binding.instructionsAnswerLocationCity.text.toString()

        Timber.i("city= %s", city)
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

        Timber.i("species= %s", species)
        return species
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