package com.examen.appsolissol.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblsol")
data class Mascota(
    @PrimaryKey
    val codigo: String,
    val nombre: String,
    val tipo: String,
    val edad: Int
)
