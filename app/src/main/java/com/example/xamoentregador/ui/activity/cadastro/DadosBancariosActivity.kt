package com.example.xamoentregador.ui.activity.cadastro

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.xamoentregador.R
import com.example.xamoentregador.ui.activity.cadastro.dialog.FormularioDadosBancarios
import com.example.xamoentregador.ui.helper.verificaIntent
import com.example.xamoentregador.ui.model.CadastroMotoca
import com.example.xamoentregador.ui.model.Endereco
import com.example.xamoentregador.ui.model.Motoca

class DadosBancariosActivity: AppCompatActivity() {


    private val formularioDadosBancarios by lazy {
        FormularioDadosBancarios(this)
    }

    private lateinit var cadastroMotoca: CadastroMotoca

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dados_bancarios)
        inicializarComponentes()


    }

    private fun inicializarComponentes() {

        if(verificaIntent(intent,"motoca")
            != null && verificaIntent(intent, "endereco") != null){
            val motoca = verificaIntent(intent,"motoca") as Motoca
            val endereco = verificaIntent(intent, "endereco") as Endereco
            formularioDadosBancarios.validarDados {
                cadastroMotoca.contaBancaria = it
                cadastroMotoca.motoca = motoca
                cadastroMotoca.endereco = endereco
                Log.d("NomeDoMotoca", motoca.nome)

            }





        }else{
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Erro inesperado, tente novamente")
            builder.setPositiveButton("OK"){dialog, which ->
                finish()
            }

        }

    }
}