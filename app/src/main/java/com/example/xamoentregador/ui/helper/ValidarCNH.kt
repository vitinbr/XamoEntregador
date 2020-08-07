package com.example.xamoentregador.ui.helper

import android.util.Log

fun validarCnh(cnh:String): Boolean{

    val char1: Char = cnh[0]

    if(cnh.replace("[\\\\D+]".toRegex(),"").length != 11
        || String.format("%0" + 11 + "d", 0).replace('0', char1) == cnh) return false

    var v: Long = 0
    var j: Long = 9

    for(i in 0 until 9){
        --j
        v += (cnh[i].toLong()- 48)* j
    }

    var dsc: Long = 0
    var vl1: Long = v % 11

    if(vl1>= 10){
        vl1 = 0
        dsc = 2
    }

    v = 0
    j = 1

    for(i in 0 until 9){
        ++j
        v += ((cnh[i].toLong() - 48) * j)
    }

    var x: Long = v % 11
    var vl2 = if (x >= 10) 0 else x - dsc


    val tamanho = cnh.length - 2

    val soma = vl1 + vl2

    Log.d("validaCNHTamanho", tamanho.toString())
    Log.d("validaCNHSoma", soma.toString())

    val teste = soma.equals(tamanho)

    return (teste)
}