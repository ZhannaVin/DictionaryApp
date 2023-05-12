package com.example.retrofitapp.domain

import com.example.retrofitapp.data.network.entities.NetworkMeaning

data class Word (
    val meanings: List<Meaning>,
    val word: String
)