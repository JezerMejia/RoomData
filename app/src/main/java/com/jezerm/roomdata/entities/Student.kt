package com.jezerm.roomdata.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Student (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "primer_nombre") val primerNombre: String,
    @ColumnInfo(name = "segundo_nombre") val segundoNombre: String,
    @ColumnInfo(name = "primer_apellido") val primerApellido: String,
    @ColumnInfo(name = "segundo_apellido") val segundoApellido: String,
    @ColumnInfo(name = "carrera") val carrera: String,
    @ColumnInfo(name = "edad") val edad: Int,
) {
    @Ignore var nombreCompleto: String = ""
        get() = "$primerNombre $segundoNombre $primerApellido $segundoApellido"

    override fun toString(): String {
        return "ID: $id, Nombre: $nombreCompleto, Carrera: $carrera"
    }
}