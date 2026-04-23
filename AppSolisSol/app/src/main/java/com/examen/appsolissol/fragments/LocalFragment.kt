package com.examen.appsolissol.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.examen.appsolissol.adapters.MascotaAdapter
import com.examen.appsolissol.databinding.FragmentLocalBinding
import com.examen.appsolissol.db.AppDatabase

class LocalFragment : Fragment() {

    private var _binding: FragmentLocalBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MascotaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MascotaAdapter(emptyList())
        binding.rvMascotas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMascotas.adapter = adapter

        val db = AppDatabase.getDatabase(requireContext())
        
        // Uso de LiveData y Observer
        db.mascotaDao().getAllMascotas().observe(viewLifecycleOwner) { mascotas ->
            adapter.updateData(mascotas)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
