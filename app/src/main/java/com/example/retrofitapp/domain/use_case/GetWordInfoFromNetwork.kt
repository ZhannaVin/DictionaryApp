package com.example.retrofitapp.domain.use_case

import com.example.retrofitapp.data.network.Result
import com.example.retrofitapp.domain.model.Word
import com.example.retrofitapp.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoFromNetwork(val repository: WordInfoRepository) {

    suspend operator fun invoke(word: String): Flow<Result<List<Word>>> {
        if (word.isBlank()) {
            return flow { }
        }
            else{
            return repository.getWordInfoFromNetwork(word)
        }
    }

}
