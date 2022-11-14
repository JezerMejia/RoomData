package com.jezerm.roomdata.ui.request

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jezerm.roomdata.data.AppDatabase
import com.jezerm.roomdata.databinding.ActivityRequestDataBinding
import com.jezerm.roomdata.entities.Student
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RequestDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRequestDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRequestDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getInstance(applicationContext)
        val studentDao = db.studentDao()

        runBlocking {
            launch {
                val students = studentDao.getAll()
                println(students)

//                studentDao.insert(
//                    Student(
//                        12,
//                        "Juan",
//                        "",
//                        "Pérez",
//                        "Rodríguez",
//                        "ISI",
//                        20
//                    )
//                )
            }
        }
    }
}