package com.example.xamodeliveryloja.keys

object Keys {

    init {
        System.loadLibrary("native-lib")
    }

    external fun placesKey(): String

}