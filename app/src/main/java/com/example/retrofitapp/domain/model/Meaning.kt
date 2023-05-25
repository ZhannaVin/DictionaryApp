package com.example.retrofitapp.domain.model

import com.example.retrofitapp.domain.model.Definition

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)