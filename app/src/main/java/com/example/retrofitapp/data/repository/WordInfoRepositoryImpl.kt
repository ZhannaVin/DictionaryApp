package com.example.retrofitapp.data.repository

import com.example.retrofitapp.data.database.WordInfoDao
import com.example.retrofitapp.data.database.asWord
import com.example.retrofitapp.data.network.DictionaryApiService
import com.example.retrofitapp.domain.model.Word
import com.example.retrofitapp.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.retrofitapp.data.network.Result
import com.example.retrofitapp.data.network.entities.asDatabaseEntity
import com.example.retrofitapp.data.network.entities.asWord

class WordInfoRepositoryImpl(
    private val api: DictionaryApiService,
    private val dao: WordInfoDao
): WordInfoRepository {

    override suspend fun getWordInfoFromNetwork(word: String): Flow<Result<List<Word>>> = flow{

        emit(Result.Loading)
        //задача - сначала загрузить данные из сети, потом послать их в базу данных и оттуда достать и показать в ui
            // такой принцип работы кэша
        try {
            val result = api.getWords(word)
            dao.insertWords(result.map {
                it.asDatabaseEntity()
            })
            emit(Result.Success(result.map {
                it.asWord()
            }))
        } catch (exception: Exception) {
            emit(Result.Error(exception))
        }
        }


    //когда захотим отобразить уже в ui
    override suspend fun getWordFormDatabase(word: String): List<Word> =
        dao.getWordInfo(word).map { it.asWord() }



}