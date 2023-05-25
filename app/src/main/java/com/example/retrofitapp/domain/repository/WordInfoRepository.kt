package com.example.retrofitapp.domain.repository

import com.example.retrofitapp.data.network.Result
import com.example.retrofitapp.domain.model.Word
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    suspend fun getWordInfoFromNetwork(word: String): Flow<Result<List<Word>>>
    suspend fun getWordFormDatabase(word: String): List<Word>

}