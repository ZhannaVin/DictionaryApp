package com.example.retrofitapp.domain

import com.example.retrofitapp.data.network.entities.NetworkDefinition

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)