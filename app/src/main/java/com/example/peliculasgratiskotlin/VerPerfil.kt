package com.example.peliculasgratiskotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_ver_perfil.*


class VerPerfil : AppCompatActivity() {

    private val db = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_perfil)

        supportActionBar!!.setTitle("Ver Perfil")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        editTextCorreo.setText(db.email)
        editTextNombre.setText(db.displayName)

        borrarUsuario()
        modificarDatos()
    }

    private fun modificarDatos() {
        botonActualizar.setOnClickListener {
            db.updateEmail(editTextCorreo.toString())
            db.updatePassword(editTextContraseña.toString())
            Toast.makeText(applicationContext, "Usuario modificado", Toast.LENGTH_LONG)
            val homeIntent = Intent(this, NavigationDrawer::class.java)
            startActivity(homeIntent)
        }
    }

    private fun borrarUsuario() {
        botonEliminar.setOnClickListener {
            db.delete()
            Toast.makeText(applicationContext, "Usuario borrado", Toast.LENGTH_LONG)

            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            FirebaseAuth.getInstance().signOut()
            val i = Intent(this, AuthActivity::class.java)
            startActivity(i)
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