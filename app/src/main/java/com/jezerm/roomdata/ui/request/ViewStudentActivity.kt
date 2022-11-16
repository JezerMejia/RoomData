package com.jezerm.roomdata.ui.request

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jezerm.roomdata.databinding.ActivityViewStudentBinding
import com.jezerm.roomdata.entities.Student

class ViewStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewStudentBinding
    private lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = student.nombreCompleto

            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun initData(student: Student) {
        binding.tvID.setText(student.id.toString())
        binding.tvPrimerNombre.setText(student.primerNombre)
        binding.tvSegundoNombre.setText(student.segundoNombre)
        binding.tvPrimerApellido.setText(student.primerApellido)
        binding.tvSegundoApellido.setText(student.segundoApellido)
        binding.tvCarrera.setText(student.carrera)
        binding.tvEdad.setText(student.edad.toString())
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}