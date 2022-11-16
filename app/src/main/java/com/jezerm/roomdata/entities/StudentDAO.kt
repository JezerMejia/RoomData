package com.jezerm.roomdata.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDAO {
    @Query("SELECT * FROM Student")
    suspend fun getAll(): List<Student>

    @Insert
    suspend fun insert(student: Student)

    @Update
    suspend fun update(student: Student)

    @Query("DELETE FROM Student WHERE id =:id")
    suspend fun delete(id:Int)
}