package com.example.retrofitapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.retrofitapp.domain.model.Meaning
import com.example.retrofitapp.domain.model.Word


@Entity(tableName = "word_info")
data class DatabaseEntity(
    @PrimaryKey
    val word: String,
    val meanings: List<Meaning>
    )

fun DatabaseEntity.asWord(): Word = Word(
    word = word,
    meanings = meanings
)
