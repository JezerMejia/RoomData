package com.jezerm.roomdata.ui.request

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.jezerm.roomdata.data.AppDatabase
import com.jezerm.roomdata.databinding.ActivityRequestDataBinding
import com.jezerm.roomdata.entities.Student
import com.jezerm.roomdata.entities.StudentAdapter
import com.jezerm.roomdata.entities.StudentDAO
import com.jezerm.roomdata.ui.edit.EditStudentActivity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RequestDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRequestDataBinding
    private lateinit var studentDao: StudentDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRequestDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Datos de estudiantes"

            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val db = AppDatabase.getInstance(applicationContext)
        studentDao = db.studentDao()

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
        binding.rcvStudentList.adapter = StudentAdapter(students) {
            openViewStudent(it)
        }
    }

    private fun openViewStudent(student: Student) {
        val intent = Intent(this, ViewStudentActivity::class.java)
        intent.putExtra("id", student.id)
        intent.putExtra("primerNombre", student.primerNombre)
        intent.putExtra("segundoNombre", student.segundoNombre)
        intent.putExtra("primerApellido", student.primerApellido)
        intent.putExtra("segundoApellido", student.segundoApellido)
        intent.putExtra("carrera", student.carrera)
        intent.putExtra("edad", student.edad)
        startActivity(intent)
    }

    override fun onResume() {
        runBlocking {
            launch {
                val students = studentDao.getAll()
                println(students)
                initData(students)
            }
        }
        super.onResume()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}