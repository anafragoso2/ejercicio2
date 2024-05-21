package com.example.ejercicio22.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.ejercicio22.data.remote.model.personajeDetail
import com.example.ejercicio22.data.remote.personajesApi
import com.example.ejercicio22.databinding.ActivityDetailsBinding
import com.example.ejercicio22.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Details : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val id = bundle?.getString("id", "")

        Log.d(Constants.LOGTAG, "Id recibido $id")

        if (id.isNullOrEmpty()) {
            Log.e(Constants.LOGTAG, "ID is null or empty")
            Toast.makeText(this, "ID is null or empty", Toast.LENGTH_SHORT).show()
            return
        }

        // Generamos una instancia a retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Ligar el API
        val personajesApi = retrofit.create(personajesApi::class.java)

        val call: Call<personajeDetail> = personajesApi.getPersonajeDetail(id)

        call.enqueue(object: Callback<personajeDetail> {
            @SuppressLint("CheckResult")
            override fun onResponse(call: Call<personajeDetail>, response: Response<personajeDetail>) {
                binding.pbLoading.visibility = View.INVISIBLE
                if (response.isSuccessful) {
                    response.body()?.let { personaje ->
                        binding.apply {

                            tvName.text = personaje.name?: "Nombre no disponible"
                            binding.tvGender.text = personaje.gender?.let { "Género: $it" } ?: "Género no disponible"
                            binding.tvProfession.text = personaje.profession?. let{"Profesión: $it"} ?: "Profesión no disponible"
                            binding.tvPosition.text = personaje.position?.let{"Posición: $it"} ?: "Posición no disponible"
                            binding.tvHair.text = personaje.hair?.let {"Cabello: $it"} ?: "Cabello no disponible"
                            binding.tvEnemies.text = personaje.enemies?.let { "Enemigos: ${it.joinToString(", ")}" } ?: "Enemigos no disponibles"

                            //tvPosition.text = personaje.position?: "Posición no disponible"
                           // tvHair.text = personaje.hair?: "Cabello no disponible"

                            Glide.with(this@Details)
                                .load(personaje.photoUrl)
                                .into(ivImage)
                        }
                    }
                } else {
                    binding.pbLoading.visibility = View.INVISIBLE
                    Log.e(Constants.LOGTAG, "Error en la respuesta: ${response.errorBody()}")
                    Toast.makeText(this@Details, "Error al cargar los detalles", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<personajeDetail>, t: Throwable) {
                Log.e(Constants.LOGTAG, "Error de conexión", t)
                Toast.makeText(this@Details, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}