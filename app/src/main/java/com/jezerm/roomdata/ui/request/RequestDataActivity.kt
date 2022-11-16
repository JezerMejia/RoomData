package com.jezerm.roomdata.ui.request

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.jezerm.roomdata.data.AppDatabase
import com.jezerm.roomdata.databinding.ActivityRequestDataBinding
import com.jezerm.roomdata.entities.Student
import com.jezerm.roomdata.entities.StudentAdapter
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
                initData(students)
            }
        }
    }

    private fun initData(students: List<Student>) {
        binding.rcvStudentList.layoutManager = LinearLayoutManager(this)
        binding.rcvStudentList.adapter = StudentAdapter(students)
    }
}