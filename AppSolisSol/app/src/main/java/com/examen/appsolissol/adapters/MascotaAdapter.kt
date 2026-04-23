package com.examen.appsolissol.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examen.appsolissol.databinding.ItemMascotaBinding
import com.examen.appsolissol.db.Mascota

class MascotaAdapter(private var list: List<Mascota>) : RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>() {

    class MascotaViewHolder(val binding: ItemMascotaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaViewHolder {
        val binding = ItemMascotaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MascotaViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MascotaViewHolder, position: Int) {
        val mascota = list[position]
        holder.binding.tvMascotaNombre.text = mascota.nombre
        holder.binding.tvMascotaCodigo.text = "Código: ${mascota.codigo}"
        holder.binding.tvMascotaTipo.text = "Tipo: ${mascota.tipo}"
        holder.binding.tvMascotaEdad.text = "Edad: ${mascota.edad}"
    }

    fun updateData(newList: List<Mascota>) {
        list = newList
        notifyDataSetChanged()
    }
}
