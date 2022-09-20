package com.example.peliculasgratiskotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderType{
    BASIC,
    GOOGLE
}

class HomeActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //setup
        val bundle : Bundle? = intent.extras
        val email: String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")

        //Guardado de datos

        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }

    private fun setup(email: String, provider: String){
        title = "Inicio"
        emailTextView.text = email
        providerTextView.text = provider


        logOutButton.setOnClickListener {
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

        saveButton.setOnLongClickListener {

            db.collection("users").document(email).set(
                hashMapOf("email" to email,
                        "nickname" to nicknameTextView.text.toString())
            )

            val homeIntent = Intent(this, NavigationDrawer::class.java)
            startActivity(homeIntent)
        true

        }
        /*getButton.setOnClickListener {

            db.collection("users").document(email).get().addOnSuccessListener {
                nicknameTextView.setText(it.get("nickname") as String?)
            }

        }

        getButtonPelicula.setOnClickListener {

            db.collection("peliculas").document(email).get().addOnSuccessListener {
                nicknameTextView.setText(it.get("Caratula") as String?)
            }

        }

        deleteButton.setOnClickListener {
            db.collection("users").document(email).delete()
        }*/
    }
}