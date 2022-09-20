package com.example.peliculasgratiskotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.peliculasgratiskotlin.ui.BuscarPelicula
import com.example.peliculasgratiskotlin.ui.FavoritesActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class NavigationDrawer : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    val db = FirebaseAuth.getInstance().currentUser
    val database = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

       /* val username: TextView = findViewById(R.id.NombreUsuario)
        val email: TextView = findViewById(R.id.CorreoUsuario)

        username.setText(db.displayName)
        email.setText(db.email)*/

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)


        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.nav_home, R.id.nav_gallery), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.menu.findItem(R.id.nav_listado_activity).setCheckable(false)
        navView.menu.findItem(R.id.nav_listado_activity).setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.nav_listado_activity -> {
                    drawerLayout.close()
                    val intent = Intent(this, Listado::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        navView.menu.findItem(R.id.nav_buscar_activity).setCheckable(false)
        navView.menu.findItem(R.id.nav_buscar_activity).setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.nav_buscar_activity -> {
                    drawerLayout.close()
                    val intent = Intent(this, BuscarPelicula::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        navView.menu.findItem(R.id.nav_second_activity).setCheckable(false)
        navView.menu.findItem(R.id.nav_second_activity).setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.nav_second_activity -> {
                    drawerLayout.close()
                    val intent = Intent(this, FavoritesActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        navView.menu.findItem(R.id.nav_perfil_activity).setCheckable(false)
        navView.menu.findItem(R.id.nav_perfil_activity).setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.nav_perfil_activity -> {
                    drawerLayout.close()
                    val intent = Intent(this, VerPerfil::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        navView.menu.findItem(R.id.nav_aboutme_activity).setCheckable(false)
        navView.menu.findItem(R.id.nav_aboutme_activity).setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.nav_aboutme_activity -> {
                    drawerLayout.close()
                    val intent = Intent(this, AboutMe::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        navView.menu.findItem(R.id.nav_contact_activity).setCheckable(false)
        navView.menu.findItem(R.id.nav_contact_activity).setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.nav_contact_activity -> {
                    drawerLayout.close()
                    val intent = Intent(this, Contacto::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
       navView.menu.findItem(R.id.nav_pedir_activity).setCheckable(false)
        navView.menu.findItem(R.id.nav_pedir_activity).setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.nav_pedir_activity -> {
                    drawerLayout.close()
                    val intent = Intent(this, PedirPelicula::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        navView.menu.findItem(R.id.nav_logout_activity).setCheckable(false)
        navView.menu.findItem(R.id.nav_logout_activity).setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.nav_logout_activity -> {
                    drawerLayout.close()
                    val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                    prefs.clear()
                    prefs.apply()
                    FirebaseAuth.getInstance().signOut()
                    onBackPressed()
                    true
                }
                else -> false
            }
        }





    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation_drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}
