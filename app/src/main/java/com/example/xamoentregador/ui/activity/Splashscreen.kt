package com.example.xamoentregador.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.xamoentregador.R
import com.example.xamoentregador.ui.helper.validarCnh

class Splashscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entrega_andamento_3)


      //  Log.d("validaCNH", validarCnh("50385407599").toString())

    }
}
