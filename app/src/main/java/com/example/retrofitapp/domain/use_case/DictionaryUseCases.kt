package com.example.retrofitapp.domain.use_case

data class DictionaryUseCases(
    val getWordInfoFromNetwork: GetWordInfoFromNetwork,
    val getWordInfoFromDatabase: GetWordInfoFromDatabase,

)
