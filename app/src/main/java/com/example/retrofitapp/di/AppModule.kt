package com.example.retrofitapp.di

import com.example.retrofitapp.data.database.TypeConverter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideTypeConverter(moshi: Moshi): TypeConverter = TypeConverter(moshi)



    }
