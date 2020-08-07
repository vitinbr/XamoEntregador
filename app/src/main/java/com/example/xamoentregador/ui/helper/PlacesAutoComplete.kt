package com.example.xamoentregador.ui.helper

import `in`.madapps.placesautocomplete.PlaceAPI
import `in`.madapps.placesautocomplete.adapter.PlacesAutoCompleteAdapter
import `in`.madapps.placesautocomplete.listener.OnPlacesDetailsListener
import `in`.madapps.placesautocomplete.model.Address
import `in`.madapps.placesautocomplete.model.Place
import `in`.madapps.placesautocomplete.model.PlaceDetails
import android.app.Activity
import android.util.Log
import android.widget.AdapterView
import com.example.xamodeliveryloja.keys.Keys
import com.example.xamoentregador.ui.model.Endereco
import kotlinx.android.synthetic.main.layout_cadastro_2.*

class PlacesAutoComplete(private val activity: Activity) {

   private var rua = ""
   private var numeroCasa = ""
   private var cidade   = ""
   private var estado = ""
   private var pais = ""
   private var cep = ""
   private var placeID = ""


   private val placesApi = PlaceAPI.Builder()
        .apiKey(Keys.placesKey())
        .build(activity)

    private lateinit var endereco: Endereco



    private fun configurarAutocomplete() = activity
        .autoCompleteEditText
        .setAdapter(PlacesAutoCompleteAdapter(activity, placesApi))


    private fun itemCLick(parent: AdapterView<*>, position: Int) {
        val place = parent.getItemAtPosition(position) as Place
        activity.autoCompleteEditText.setText(place.description)
        placeID = place.id

    }

    fun placesAutocomplete(sucesso: (endereco : Endereco) -> Unit) {
        activity.autoCompleteEditText.setAdapter(PlacesAutoCompleteAdapter(activity, placesApi))
        configurarAutocomplete()
        activity.autoCompleteEditText.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            itemCLick(parent, position)
            getPlaceDetails{
                sucesso(it)
            }

        }


    }

    private fun getPlaceDetails(sucesso: (endereco : Endereco) -> Unit) {
        placesApi.fetchPlaceDetails(placeID, object :
            OnPlacesDetailsListener {
            override fun onError(errorMessage: String) {
                Log.d("placeMSGErro", errorMessage)
            }

            override fun onPlaceDetailsFetched(placeDetails: PlaceDetails) {
                setupUI(placeDetails){
                 sucesso(it)
                }
            }
        })
    }


     private fun setupUI(placeDetails: PlaceDetails, sucesso: (endereco : Endereco) -> Unit) {
        val address = placeDetails.address
        parseAddress(address)
         sucesso(Endereco(
             logradouro = rua,
             cidade = cidade,
             estado = estado,
             cep = cep,
             latitude = placeDetails.lat.toString(),
             longitude = placeDetails.lng.toString(),
             bairro = placeDetails.vicinity,
             numero = numeroCasa
         ))



    }

    fun validaCampos(endereco: Endereco): Boolean{
        var vazio = false
        if(endereco.logradouro.isEmpty()) vazio = true

        if(endereco.cidade.isEmpty()) vazio = true

        if(endereco.estado.isEmpty()) vazio = true

        if(endereco.cep.isEmpty()) vazio = true

        if(endereco.bairro.isEmpty()) vazio = true

        if(endereco.numero.isEmpty()) vazio = true

        return vazio
    }


    private fun parseAddress(address: ArrayList<Address>) {
        (0 until address.size).forEach { i ->
            when {
                address[i].type.contains("street_number") -> numeroCasa += address[i].shortName
                address[i].type.contains("route") -> rua += address[i].shortName
                address[i].type.contains("administrative_area_level_2") -> cidade += address[i].shortName
                address[i].type.contains("administrative_area_level_1") -> estado += address[i].shortName
                address[i].type.contains("country") -> pais += address[i].shortName
                address[i].type.contains("postal_code") -> cep += address[i].shortName
            }
        }

    }
}


