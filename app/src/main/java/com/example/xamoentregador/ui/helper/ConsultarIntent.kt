package com.example.xamoentregador.ui.helper

import android.content.Intent
import java.io.Serializable

fun verificaIntent(intent: Intent, nome: String): Serializable? {
    return if(intent.getSerializableExtra(nome) != null){
        intent.getSerializableExtra(nome)
    }else{
        null
    }

}