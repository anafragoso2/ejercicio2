package com.example.ejercicio22.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ejercicio22.data.remote.model.personajesDto
import com.example.ejercicio22.databinding.GameElementBinding

class personajeAdapter(
    private val personajes: List<personajesDto>,
    private val onPersonajeClicked: (personajesDto) -> Unit
): RecyclerView.Adapter<personajeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): personajeViewHolder {
        //generacion de viewHolder
        val binding = GameElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return personajeViewHolder(binding)
    }

    override fun getItemCount() = personajes.size

    override fun onBindViewHolder(holder: personajeViewHolder, position: Int) {

        val personaje = personajes[position]

        holder.bind(personaje)

        //usando glide
        Glide.with(holder.itemView.context)
            .load(personaje.photoUrl)
            .into(holder.ivImagen)

        holder.itemView.setOnClickListener{
            onPersonajeClicked(personaje)
        }



    }

}