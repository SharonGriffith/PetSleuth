package com.sharonahamon.petsleuth.screens.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.sharonahamon.petsleuth.R
import com.sharonahamon.petsleuth.common.SpeciesTypeEnum
import com.sharonahamon.petsleuth.data.PetLD

/**
 * The base code for this class was generated within the Android Studio IDE
 * and modified to fit the needs of this project.
 */
class PetCardListViewAdapter() : RecyclerView.Adapter<PetCardListViewAdapter.ViewHolder>() {

    // pet list, for list fragment
    var _petList: MutableList<LiveData<PetLD>> = mutableListOf()
    var petList: List<LiveData<PetLD>>
        get() = _petList
        set(value) {
            _petList = value.toMutableList()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pet_card_fragment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pet = petList[position]

        holder.bind(pet)
    }

    override fun getItemCount(): Int = petList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val petImage: ImageView = view.findViewById(R.id.list_item_image)
        val petId: TextView = view.findViewById(R.id.list_item_number)
        val lastSeenDate: TextView = view.findViewById(R.id.list_item_date_value)
        val status: TextView = view.findViewById(R.id.list_item_status_value)
        val species: TextView = view.findViewById(R.id.list_item_species_value)
        val lastSeenLocation: TextView = view.findViewById(R.id.list_item_location_value)

        fun bind(
            pet: LiveData<PetLD>
        ) {
            // https://gist.github.com/garuma/f0f1662a6587e2c8d916
            petImage.setImageResource(
                when (pet.value?.petSummary?.value?.species?.value.toString()) {
                    SpeciesTypeEnum.CAT.species -> R.drawable.cat_face_vector
                    //SpeciesTypeEnum.DOG.species -> R.drawable.ic_dog
                    else -> R.drawable.ic_add
                }
            )

            petId.text = "Pet ID: " + pet.value?.petId?.value.toString()
            lastSeenDate.text =
                pet.value?.petLastSeenLocation?.value?.lastSeenDate?.value.toString()
            status.text = pet.value?.petSummary?.value?.status?.value.toString()
            species.text = pet.value?.petSummary?.value?.species?.value.toString()
            lastSeenLocation.text =
                pet.value?.petLastSeenLocation?.value?.city?.value.toString() + " " + pet.value?.petLastSeenLocation?.value?.state?.value.toString() + " " + pet.value?.petLastSeenLocation?.value?.zip?.value.toString()
        }
    }
}