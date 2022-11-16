package com.jezerm.roomdata.ui.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jezerm.roomdata.data.AppDatabase
import com.jezerm.roomdata.databinding.ActivityEditDataBinding
import com.jezerm.roomdata.entities.Student
import com.jezerm.roomdata.entities.StudentAdapter
import com.jezerm.roomdata.entities.StudentDAO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class EditDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditDataBinding
    private lateinit var studentDao: StudentDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Editar estudiantes"
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
        binding.rcvStudentList.adapter = StudentAdapter(students) { student, i ->
            openEditStudent(student)
        }
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
    private fun openEditStudent(student: Student) {
        val intent = Intent(this, EditStudentActivity::class.java)
        intent.putExtra("id", student.id)
        intent.putExtra("primerNombre", student.primerNombre)
        intent.putExtra("segundoNombre", student.segundoNombre)
        intent.putExtra("primerApellido", student.primerApellido)
        intent.putExtra("segundoApellido", student.segundoApellido)
        intent.putExtra("carrera", student.carrera)
        intent.putExtra("edad", student.edad)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}