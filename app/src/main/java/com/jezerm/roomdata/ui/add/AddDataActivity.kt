package com.jezerm.roomdata.ui.add

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jezerm.roomdata.data.AppDatabase
import com.jezerm.roomdata.databinding.ActivityAddDataBinding
import com.jezerm.roomdata.entities.Student
import com.jezerm.roomdata.entities.StudentDAO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AddDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDataBinding
    private lateinit var studentDao: StudentDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getInstance(applicationContext)
        studentDao = db.studentDao()

        binding.btnAdd.setOnClickListener {
            addStudent()
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Añadir estudiante"

            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun addStudent() {
        try {
            val st = Student(
                Integer.parseInt(binding.etID.text.toString()),
                binding.etPrimerNombre.text.toString(),
                binding.etSegundoNombre.text.toString(),
                binding.etPrimerApellido.text.toString(),
                binding.etSegundoApellido.text.toString(),
                binding.etCarrera.text.toString(),
                Integer.parseInt(binding.etEdad.text.toString()),
            )

            Toast.makeText(this, "¡Estudiante añadido con éxito!", Toast.LENGTH_SHORT).show()
            finish()
            runBlocking {
                launch {
                    studentDao.insert(st)
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}