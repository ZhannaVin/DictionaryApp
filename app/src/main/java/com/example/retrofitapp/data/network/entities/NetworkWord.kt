package com.example.retrofitapp.data.network.entities

import com.example.retrofitapp.data.network.entities.NetworkMeaning
import com.example.retrofitapp.domain.Word

data class NetworkWord(
    val meanings: List<NetworkMeaning>,
    val word: String
)

fun NetworkWord.asWord(): Word = Word(
    meanings = meanings.map { it.asMeaning() },
    word = word
)