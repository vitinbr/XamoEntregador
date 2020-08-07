package com.example.xamoentregador.ui.activity.cadastro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.xamoentregador.R
import com.example.xamoentregador.ui.helper.PlacesAutoComplete
import com.example.xamoentregador.ui.helper.verificaIntent
import com.example.xamoentregador.ui.model.Endereco
import com.example.xamoentregador.ui.model.Motoca
import kotlinx.android.synthetic.main.layout_cadastro_2.*

class Cadastro2Activity: AppCompatActivity() {

    companion object{
        private const val REQUEST_CODE = 100
    }

    private val placesAutoComplete by lazy {
        PlacesAutoComplete(this)
    }


    private lateinit var motoca: Motoca

    private lateinit var endereco: Endereco


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_cadastro_2)
        inicializarComponentes()


    }

    private fun inicializarComponentes() {

        if(verificaIntent(intent,"motoca") != null){
             motoca = verificaIntent(intent,"motoca") as Motoca
            Log.d("NomeDoMotoca", motoca.nome)
            placesAutoComplete.placesAutocomplete {
                endereco = it
            }
            configuraBtn()


        }else{
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Erro inesperado, tente novamente")
            builder.setPositiveButton("OK"){dialog, which ->
                finish()
            }

        }

    }

    private fun configuraBtn() {
        btncontinuar.setOnClickListener {
            if(!placesAutoComplete.validaCampos(endereco)){
                Log.d("NomeDoBairro", endereco.bairro)
                val textoPlacaVeiculo = txtPlacaVeiculo.text.toString()
                motoca.placaVeiculo = textoPlacaVeiculo
                val dadosBancarios = Intent(this, DadosBancariosActivity::class.java)
                dadosBancarios.putExtra("motoca",motoca)
                dadosBancarios.putExtra("endereco", endereco)
                startActivity(dadosBancarios)

            }else{
               autoCompleteEditText.error = "Digite o endereço corretamente"
            }
        }
    }
}