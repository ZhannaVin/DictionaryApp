package com.example.retrofitapp.data.database

import androidx.room.*
import retrofit2.http.GET


@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //зачем лист слов, а не одно слово?
    suspend fun insertWords(words: List<DatabaseEntity>)



    //почему не передаем слова на удаление
    //не мой код
    //    @Query("DELETE FROM word_info WHERE word IN (:words)")
   // @Delete
    //suspend fun deleteWord()


    @Query("SELECT * FROM word_info WHERE word LIKE '%' || :word || '%'")
    suspend fun getWordInfo(word: String): List<DatabaseEntity>
}