package com.example.peliculasgratiskotlin

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_about_me.*

class AboutMe : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)

        supportActionBar!!.setTitle("About me")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        inicio()
    }

    private fun inicio() {
        db.collection("datosCreador").document("Dp4vJRExtQLXBVltiOJF").get().addOnSuccessListener {
            textViewNombre.text = it.get("Nombre") as String?
            textViewEdad.text = it.get("Edad") as String?
            textViewProfesion.text = it.get("Profesión") as String?
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