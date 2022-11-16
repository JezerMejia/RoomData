package com.jezerm.roomdata.ui.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jezerm.roomdata.data.AppDatabase
import com.jezerm.roomdata.databinding.ActivityEditStudentBinding
import com.jezerm.roomdata.entities.Student
import com.jezerm.roomdata.entities.StudentDAO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class EditStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditStudentBinding
    private lateinit var student: Student
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getInstance(applicationContext)
        val studentDao = db.studentDao()

        student = Student(
            intent.getIntExtra("id", 0),
            intent.getStringExtra("primerNombre")!!,
            intent.getStringExtra("segundoNombre")!!,
            intent.getStringExtra("primerApellido")!!,
            intent.getStringExtra("segundoApellido")!!,
            intent.getStringExtra("carrera")!!,
            intent.getIntExtra("edad", 0),
            )
        initData(student)

        binding.btnCancelar.setOnClickListener { cancelar() }
        binding.btnGuardar.setOnClickListener { guardar(studentDao) }
    }

    private fun initData(student: Student) {
        binding.etID.setText(student.id.toString())
        binding.etPrimerNombre.setText(student.primerNombre)
        binding.etSegundoNombre.setText(student.segundoNombre)
        binding.etPrimerApellido.setText(student.primerApellido)
        binding.etSegundoApellido.setText(student.segundoApellido)
        binding.etCarrera.setText(student.carrera)
        binding.etEdad.setText(student.edad.toString())
    }

    private fun guardar(studentDAO: StudentDAO) {
        val st = Student(
            Integer.parseInt(binding.etID.text.toString()),
            binding.etPrimerNombre.text.toString(),
            binding.etSegundoNombre.text.toString(),
            binding.etPrimerApellido.text.toString(),
            binding.etSegundoApellido.text.toString(),
            binding.etCarrera.text.toString(),
            Integer.parseInt(binding.etEdad.text.toString()),
        )
        Toast.makeText(this, "Estudiante Actualizado", Toast.LENGTH_SHORT).show()
        finish()
        runBlocking {
            launch {
                studentDAO.update(st)
            }
        }
    }

    private fun cancelar() {
        finish()
    }
}