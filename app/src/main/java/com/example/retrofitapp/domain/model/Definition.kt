package com.example.retrofitapp.domain.model

data class Definition (
    val antonyms: List<Any>,
    val definition: String,
    val example: String?,
    val synonyms: List<Any>
        )