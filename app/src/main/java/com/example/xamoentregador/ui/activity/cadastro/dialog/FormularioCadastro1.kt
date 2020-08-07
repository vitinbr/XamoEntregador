package com.example.xamoentregador.ui.activity.cadastro.dialog

import android.app.Activity
import com.example.xamoentregador.ui.helper.isCPF
import com.example.xamoentregador.ui.model.Motoca
import kotlinx.android.synthetic.main.layout_cadastro_1.*

class FormularioCadastro1(private val activity: Activity) {
    val campoNome = activity.txtNomeCompletoMotoca
    val campoCpf = activity.txtCpfMotoca
    val campoCnh = activity.txtCnhMotoca
    val campoEmail = activity.txtEmailMotoca


    fun validarDados(sucesso: (motoca: Motoca) -> Unit) {
        val textoNome = campoNome.text.toString()
        val textoCpf = campoCpf.text.toString()
        val textoCnh = campoCnh.text.toString()
        val textoEmail = campoEmail.text.toString()
        val campoCheck = activity.checkBoxCadastro
        var vazio = false



            if (textoNome.isEmpty()) {
                campoNome.error = "Digite o seu nome"
                vazio = true
            }
            if (textoCpf.isEmpty()) {
                campoCpf.error = "Digite o seu cpf"
                vazio = true
            } else {
                if (isCPF(textoCpf)) {

                } else {
                    campoCpf.error = "Digite um CPF valido"
                    vazio = true
                }
            }
            if (textoCnh.isEmpty()) {
                campoCnh.error = "Digite a sua cnh"
                vazio = true
            }
        if (textoEmail.isEmpty()) {
            campoEmail.error = "Digite o seu email"
            vazio = true
        }
            if (!vazio) {
                if(isCPF(textoCpf))
                 sucesso(Motoca(
                    nome = textoNome,
                    cpf = textoCpf,
                    cnh = textoCnh,
                    email = textoEmail,
                    placaVeiculo = ""
                ))
            }

    }

}