package com.example.retrofitapp.data.network.entities

import com.example.retrofitapp.domain.Definition

data class NetworkDefinition(
    val antonyms: List<Any>,
    val definition: String,
    val example: String,
    val synonyms: List<Any>
)

fun NetworkDefinition.asDefinition(): Definition = Definition(
    antonyms = antonyms,
    definition = definition,
    example = example,
    synonyms = synonyms
)