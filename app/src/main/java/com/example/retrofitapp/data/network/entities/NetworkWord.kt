package com.example.retrofitapp.data.network.entities

import com.example.retrofitapp.data.database.DatabaseEntity
import com.example.retrofitapp.domain.model.Word

data class NetworkWord(
    val meanings: List<NetworkMeaning>,
    val word: String
)

fun NetworkWord.asWord(): Word = Word(
    meanings = meanings.map { it.asMeaning() },
    word = word
)

fun NetworkWord.asDatabaseEntity(): DatabaseEntity = DatabaseEntity(
    word = word,
    meanings = meanings.map{it.asMeaning()}
)
