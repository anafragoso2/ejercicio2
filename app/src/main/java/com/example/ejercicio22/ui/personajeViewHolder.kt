package com.example.ejercicio22.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.ejercicio22.data.remote.model.personajesDto
import com.example.ejercicio22.databinding.GameElementBinding

class personajeViewHolder(private val binding: GameElementBinding):
    RecyclerView.ViewHolder(binding.root) {

        val ivImagen = binding.ivImagen

        //VINCULACIÓN DE LAS VISTAS
        fun bind(personaje: personajesDto) {
            binding.tvNombre.text = personaje.name

            //binding.tvAfiliacion.text = personaje.affiliation
            binding.tvAfiliacion.text = personaje.affiliation ?: "Afiliación no disponible"
        }
}



