package com.example.retrofitapp.domain.use_case

import com.example.retrofitapp.domain.model.Word
import com.example.retrofitapp.domain.repository.WordInfoRepository

class GetWordInfoFromDatabase(private val repository: WordInfoRepository) {

    suspend operator fun invoke(word: String): List<Word>{
       return repository.getWordFormDatabase(word)
    }

}
