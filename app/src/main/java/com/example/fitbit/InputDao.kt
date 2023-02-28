package com.example.fitbit
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface InputDao {
    @Query("SELECT * FROM input_table")
    fun getAll(): Flow<List<InputEntity>>

    @Insert
    fun insert(inputs: InputEntity)

    @Query("DELETE FROM input_table")
    fun deleteAll()
}