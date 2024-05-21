package com.example.ejercicio22.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicio22.R
import com.example.ejercicio22.data.remote.model.personajesDto
import com.example.ejercicio22.data.remote.personajesApi
import com.example.ejercicio22.databinding.ActivityMainBinding
import com.example.ejercicio22.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Generamos una instancia a retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //ligarlo hacia el API
        val personajesApi = retrofit.create(personajesApi::class.java)

        val call: Call<List<personajesDto>> = personajesApi.getpersonajesDto()

        call.enqueue(object: Callback<List<personajesDto>>{
            override fun onResponse(
                p0: Call<List<personajesDto>>,
                response: Response<List<personajesDto>>
            ) {

                binding.pbLoading.visibility = View.INVISIBLE

                Log.d(Constants.LOGTAG, "Respuesta recibida: ${response.body()}")

                response.body()?.let {personajes ->
                    val miAdapter = personajeAdapter(personajes){personaje ->
                        //CLICK DE CADA ELEMENTO
                        personaje._id?.let { id ->
                            val bundle = bundleOf(
                                "id" to id
                            )


                            val intent = Intent(this@MainActivity, Details::class.java)

                            intent.putExtras(bundle)

                            startActivity(intent)
                        }
                    }

                    binding.rvGames.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = miAdapter
                    }}
            }

            override fun onFailure(p0: Call<List<personajesDto>>, p1: Throwable) {
                //error de conexion
                //Manejo de errores

                binding.pbLoading.visibility = View.INVISIBLE



                Toast.makeText(this@MainActivity, "No hay conexión disponible", Toast.LENGTH_SHORT).show()
            }

        } )
    }
}

