package com.jezerm.roomdata.entities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jezerm.roomdata.databinding.StudentItemBinding

class StudentAdapter(
    var list: List<Student>,
    private val clickListener: (Student, Int) -> Unit = { student: Student, i: Int -> }
) : RecyclerView.Adapter<StudentAdapter.StudentHolder>() {

    inner class StudentHolder(private val binding: StudentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun load(student: Student) {
            with(binding) {
                tvName.text = student.nombreCompleto
                tvDetails.text = "${student.id} - ${student.carrera}"

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val binding = StudentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.load(list[position])
        holder.itemView.setOnClickListener {
            clickListener(list[position], position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}