package com.example.retrofitapp.di

import android.content.Context
import androidx.room.Room
import com.example.retrofitapp.data.database.DictionaryDatabase
import com.example.retrofitapp.data.database.TypeConverter
import com.example.retrofitapp.data.database.WordInfoDao
import com.example.retrofitapp.data.network.DictionaryApiService
import com.example.retrofitapp.data.repository.WordInfoRepositoryImpl
import com.example.retrofitapp.domain.repository.WordInfoRepository
import com.example.retrofitapp.domain.use_case.DictionaryUseCases
import com.example.retrofitapp.domain.use_case.GetWordInfoFromDatabase
import com.example.retrofitapp.domain.use_case.GetWordInfoFromNetwork
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    //1
    @Singleton
    @Provides
    fun provideMoshi(): Moshi{
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }


    //2
    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.dictionaryapi.dev/")
            .build()
    }

    //3
    @Singleton
    @Provides
    fun provideDictionaryApiService(retrofit: Retrofit): DictionaryApiService{
        return retrofit.create(DictionaryApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideTypeConverter(moshi: Moshi): TypeConverter {
        return TypeConverter(moshi)
    }


    //разобарть эту инъекцию и связана ли она с базой данных, там напрямую внедряестя или только через вью модель?
    @Provides
    @Singleton
    fun provideDictionaryDatabase(
        @ApplicationContext appContext: Context,
        typeConverter: TypeConverter
    ): DictionaryDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            DictionaryDatabase::class.java,
            "dictionary_database"
        )
            .addTypeConverter(typeConverter)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideDatabaseDao(database: DictionaryDatabase): WordInfoDao {
        return database.dao
    }

    @Provides
    @Singleton
    fun provideDictionaryRepository(
        databaseDao: WordInfoDao,
        dictionaryApiService: DictionaryApiService
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(dictionaryApiService, databaseDao)
    }

    @Provides
    @Singleton
    fun provideDictionaryUseCases(repository: WordInfoRepository): DictionaryUseCases {
        return DictionaryUseCases(
            getWordInfoFromNetwork = GetWordInfoFromNetwork(repository),
            getWordInfoFromDatabase = GetWordInfoFromDatabase(repository),

        )
    }
}