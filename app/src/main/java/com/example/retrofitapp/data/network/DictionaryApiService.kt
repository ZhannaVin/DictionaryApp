package com.example.retrofitapp.data.network

import com.example.retrofitapp.data.network.entities.NetworkWord
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApiService {

    @GET("api/v2/entries/en/{word}")
    suspend fun getWords(
        @Path("word") word: String
    ): List<NetworkWord>
}