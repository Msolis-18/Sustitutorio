package com.examen.appsolissol.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MascotaDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertMascota(mascota: Mascota)

    @Query("SELECT * FROM tblsol")
    fun getAllMascotas(): LiveData<List<Mascota>>

    @Query("SELECT COUNT(*) FROM tblsol WHERE codigo = :codigo")
    suspend fun checkCodigoExists(codigo: String): Int
}
