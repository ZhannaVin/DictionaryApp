package com.example.retrofitapp.data.network.entities

import com.example.retrofitapp.domain.Meaning

data class NetworkMeaning(
    val definitions: List<NetworkDefinition>,
    val partOfSpeech: String
)

fun NetworkMeaning.asMeaning(): Meaning = Meaning(
    definitions = definitions.map { it.asDefinition() },
    partOfSpeech = partOfSpeech
)