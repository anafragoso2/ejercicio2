package com.example.ejercicio22.data.remote.model

import android.icu.text.CaseMap.Title

data class personajeDetail(
    var name: String,
    var gender: String,
    var profession: String,
    var position: String,
    var hair: String,
    var enemies: List<String>,
    var allies: List<String>,
    var photoUrl: String,

    //falta la imagen del elemento del que es maestro
)
