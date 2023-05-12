package com.example.retrofitapp.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.retrofitapp.domain.Meaning
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


@ProvidedTypeConverter
class TypeConverter(
    private val moshi: Moshi
) {
    @TypeConverter
    fun stringToMeaningsList(data: String): List<Meaning> {
        return moshi.adapter<List<Meaning>>(
            Types.newParameterizedType(
                List::class.java,
                Meaning::class.java
            )
        ).fromJson(data) ?: emptyList()
    }

    @TypeConverter
    fun meaningsListToString(data: List<Meaning>): String {
        return moshi.adapter<List<Meaning>>(
            Types.newParameterizedType(
                List::class.java,
                Meaning::class.java
            )
        ).toJson(data)
    }
}