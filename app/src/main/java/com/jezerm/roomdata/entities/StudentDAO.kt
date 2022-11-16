package com.jezerm.roomdata.entities

<<<<<<< Updated upstream
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
=======
import androidx.room.*
>>>>>>> Stashed changes

@Dao
interface StudentDAO {
    @Query("SELECT * FROM Student")
    suspend fun getAll(): List<Student>

    @Insert
    suspend fun insert(student: Student)
<<<<<<< Updated upstream
=======

    @Update
    suspend fun update(student: Student)

    @Query("DELETE FROM Student WHERE id =:id")
    suspend fun delete(id:Int)
>>>>>>> Stashed changes
}