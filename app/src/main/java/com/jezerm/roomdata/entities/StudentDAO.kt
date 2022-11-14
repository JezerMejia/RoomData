package com.jezerm.roomdata.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDAO {
    @Query("SELECT * FROM Student")
    suspend fun getAll(): List<Student>

    @Insert
    suspend fun insert(student: Student)
}