package com.jezerm.roomdata.entities

import androidx.room.Dao
import androidx.room.Query

@Dao
interface StudentDAO {
    @Query("SELECT * FROM Student")
    fun getAll(): List<Student>
}