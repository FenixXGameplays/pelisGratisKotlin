package com.example.peliculasgratiskotlin.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.peliculasgratiskotlin.Pelicula
import com.example.peliculasgratiskotlin.R
import com.example.peliculasgratiskotlin.Trailer
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_scrolling.*


class PeliculaSeleccionada : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        intent = this.intent

        val name = intent.getStringExtra("nombre").toString()
        super.onCreate(savedInstanceState)

        //supportActionBar!!.setTitle(name)
       // supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //MobileAds.initialize(this) {}

        setContentView(R.layout.activity_pelicula_seleccionada)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = name

        var trailer = ""
        var url= ""

        db.collection("peliculas").document(name).get().addOnSuccessListener {

            nombreText.text = it.get("Nombre") as String?
            duracion.text = it.get("Duracion") as String?
            anyo.text = it.get("Año") as String?
            reparto.text = it.get("Reparto") as String?
            sinopsis.text = it.get("Sinopsis") as String?
            trailer = (it.get("trailer") as String?).toString()
            url = (it.get("enlace") as String?).toString()
            textViewGenero.text = it.get("Género") as String?
            Picasso.get().load(it.get("Caratula") as String?).into(caratulaImage)

        }



        buttonTrailer.setOnClickListener {
            val trailerIntent = Intent(this, Trailer::class.java).apply {
                putExtra("trailerName", trailer)
            }


            startActivity(trailerIntent)
        }

        botonVerPeli.setOnClickListener {

            val trailerIntent = Intent(this, Pelicula::class.java).apply {
                putExtra("enlacePelicula", url)
            }
            startActivity(trailerIntent)
        }

    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }*/



}


