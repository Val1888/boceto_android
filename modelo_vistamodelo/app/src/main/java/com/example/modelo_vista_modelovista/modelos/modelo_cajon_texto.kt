package com.example.modelo_vista_modelovista.modelos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ModeloCajonTexto: ViewModel(){
    private val _cajon_de_texto = MutableLiveData<String>("Valor inicial")
    val cajon_de_texto: LiveData<String> = _cajon_de_texto

    fun pasar_nuevo_texto(texto_nuevo: String){
        Log.v("ENTRADA", "El texto nuevo es ${texto_nuevo}")

        _cajon_de_texto.value = texto_nuevo

    }
}