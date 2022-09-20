package com.example.peliculasgratiskotlin

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_contacto.*
import java.util.*
import javax.mail.*
import javax.mail.internet.*



class Contacto : AppCompatActivity() {
    private val db = FirebaseAuth.getInstance().currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacto)

        supportActionBar!!.setTitle("Ver Perfil")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        formu()
    }

    private fun formu() {
        buttonEnviarEmail.setOnClickListener {
            sendEmail()
        }

    }

    private fun sendEmail() {
        val correoReceptor = arrayOf("peliskotlin@gmail.com")
        val correoEmisor = arrayOf("")
        val asunto = editTextAsunto.text
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.setData(Uri.parse("mailto:"))
        emailIntent.setType("text/plain")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, correoReceptor)
        emailIntent.putExtra(Intent.EXTRA_CC, correoEmisor)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto.toString() + ", " + db.displayName)
        emailIntent.putExtra(Intent.EXTRA_TEXT, editTextMensajeContacto.text)

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."))
            finish()
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                    this@Contacto, "No tienes clientes de email instalados.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}