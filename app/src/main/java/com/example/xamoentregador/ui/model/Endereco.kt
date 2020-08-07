package com.example.xamoentregador.ui.model

import java.io.Serializable

class Endereco(val cidade: String,
               val estado: String,
               val logradouro: String,
               val numero: String,
               val bairro: String,
               val cep: String,
               val latitude: String,
               val longitude: String): Serializable