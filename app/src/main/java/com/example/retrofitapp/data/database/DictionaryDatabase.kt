package com.example.retrofitapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DatabaseEntity::class], version = 1)
@TypeConverters(TypeConverter::class)

abstract class DictionaryDatabase: RoomDatabase() {

    abstract val dao: WordInfoDao
}