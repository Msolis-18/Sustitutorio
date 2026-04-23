package com.examen.appsolissol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.examen.appsolissol.databinding.ActivityHomeBinding
import com.examen.appsolissol.fragments.ApiFragment
import com.examen.appsolissol.fragments.LocalFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cargamos el fragmento de la API por defecto al iniciar
        if (savedInstanceState == null) {
            cargarFragment(ApiFragment())
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_api -> {
                    cargarFragment(ApiFragment())
                    true
                }
                R.id.navigation_local -> {
                    cargarFragment(LocalFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun cargarFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
