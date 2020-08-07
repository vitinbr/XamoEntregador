package com.example.xamoentregador.ui.activity.cadastro

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.xamoentregador.R
import com.example.xamoentregador.ui.activity.cadastro.dialog.FormularioCadastro1
import kotlinx.android.synthetic.main.layout_cadastro_1.*

class Cadastro1Activity : AppCompatActivity() {

    private val formularioCadastro1  by lazy{
        FormularioCadastro1(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_cadastro_1)

        btnEnviarCadastro1.setOnClickListener {
            if(checkBoxCadastro.isChecked){
                formularioCadastro1.validarDados {
                    val cadastro2 = Intent(this, Cadastro2Activity::class.java)
                    cadastro2.putExtra("motoca", it)
                    startActivity(cadastro2)
                }
            }else
                checkBoxCadastro.error = "É necessario ceitar os termos de uso para continuar"
        }



    }

}