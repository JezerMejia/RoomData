package com.jezerm.roomdata.ui.delete

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.jezerm.roomdata.R
import com.jezerm.roomdata.data.AppDatabase
import com.jezerm.roomdata.databinding.ActivityEditDataBinding
import com.jezerm.roomdata.entities.Student
import com.jezerm.roomdata.entities.StudentAdapter
import com.jezerm.roomdata.entities.StudentDAO
import com.jezerm.roomdata.ui.edit.EditStudentActivity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DeleteDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditDataBinding
    private lateinit var studentDao: StudentDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Borrar estudiante"
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
            val dialog= AlertDialog.Builder(this).apply {
                setTitle("Borrar Estudiante")
                    .setMessage("Estas seguro de borrar este estudiante?")
                    .setCancelable(true)
                    .setPositiveButton("Si"){ _: DialogInterface, _: Int ->
                        val db = AppDatabase.getInstance(applicationContext)
                        val studentDao = db.studentDao()
                        runBlocking {
                            launch {
                                studentDao.delete(it.id)
                            }
                        }
                    }
                    .setNegativeButton("No",null)
            }
            dialog.show()
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