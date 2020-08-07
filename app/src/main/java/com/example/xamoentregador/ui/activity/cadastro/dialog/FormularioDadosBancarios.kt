package com.example.xamoentregador.ui.activity.cadastro.dialog

import android.app.Activity
import com.example.xamoentregador.ui.model.ContaBancaria
import kotlinx.android.synthetic.main.layout_dados_bancarios.*

class FormularioDadosBancarios(activity: Activity) {

    val campoBanco = activity.txtNomeBanco
    val campoAgencia = activity.txtNumeroAgencia
    val campoConta = activity.txtNumeroConta
    val campoDigito = activity.txtDigito

    fun validarDados(sucesso:(contaBancaria: ContaBancaria) -> Unit){
        val textoBanco =  campoBanco.text.toString()
        val textoAgencia = campoAgencia.text.toString()
        val textoConta = campoConta.text.toString()
        val textoDigito = campoDigito.text.toString()

        var vazio = false

        if(textoBanco.isEmpty()){
            campoBanco.error = "Digite o nome do banco"
            vazio = true

        }
        if (textoAgencia.isEmpty()){
            campoAgencia.error = "Digite a agencia do seu banco"
            vazio = true

        }
        if (textoConta.isEmpty()){
            campoConta.error = "Digite a conta do seu banco"
            vazio = true

        }
        if (textoDigito.isEmpty()){
            campoDigito.error = "Digite o digito da conta"
            vazio = true

        }
        if (!vazio){
            sucesso(ContaBancaria(nomeBanco = textoBanco,
                numeroAgencia = textoAgencia,
                numeroConta = textoConta,
                digitoConta = textoDigito))
        }

    }
}