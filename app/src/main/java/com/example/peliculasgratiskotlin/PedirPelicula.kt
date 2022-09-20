package com.example.peliculasgratiskotlin

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_contacto.*
import kotlinx.android.synthetic.main.activity_pedir_pelicula.*

class PedirPelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedir_pelicula)

        sendPeli()
    }

    private fun sendPeli() {
        buttonEnviarSolicitud.setOnClickListener {
            val correoReceptor = arrayOf("peliskotlin@gmail.com")
            val correoEmisor = arrayOf("")
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.setData(Uri.parse("mailto:"))
            emailIntent.setType("text/plain")
            emailIntent.putExtra(Intent.EXTRA_EMAIL, correoReceptor)
            emailIntent.putExtra(Intent.EXTRA_CC, correoEmisor)
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Solicitud de pelicula")
            emailIntent.putExtra(Intent.EXTRA_TEXT, editTextSolicitud.text)

            try {
                startActivity(Intent.createChooser(emailIntent, "Enviar email..."))
                finish()
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    this@PedirPelicula, "No tienes clientes de email instalados.", Toast.LENGTH_LONG).show()
            }
        }
    }
}