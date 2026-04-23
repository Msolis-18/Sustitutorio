package com.examen.appsolissol

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.examen.appsolissol.databinding.ActivityRegistroBinding
import com.examen.appsolissol.db.AppDatabase
import com.examen.appsolissol.db.Mascota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)

        binding.btnGuardar.setOnClickListener {
            guardarMascota()
        }

        binding.btnIngresar.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun guardarMascota() {
        val codigo = binding.etCodigo.text.toString().trim()
        val nombre = binding.etNombre.text.toString().trim()
        val tipo = binding.etTipo.text.toString().trim()
        val edadStr = binding.etEdad.text.toString().trim()

        if (codigo.isEmpty() || nombre.isEmpty() || tipo.isEmpty() || edadStr.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        val edad = edadStr.toIntOrNull() ?: 0
        if (edad <= 0) {
            Toast.makeText(this, "La edad debe ser mayor a 0", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch(Dispatchers.IO) {
            val exists = db.mascotaDao().checkCodigoExists(codigo)
            if (exists > 0) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RegistroActivity, "El código ya existe", Toast.LENGTH_SHORT).show()
                }
            } else {
                val mascota = Mascota(codigo, nombre, tipo, edad)
                db.mascotaDao().insertMascota(mascota)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RegistroActivity, "Mascota guardada correctamente", Toast.LENGTH_SHORT).show()
                    binding.etCodigo.text.clear()
                    binding.etNombre.text.clear()
                    binding.etTipo.text.clear()
                    binding.etEdad.text.clear()
                }
            }
        }
    }
}
