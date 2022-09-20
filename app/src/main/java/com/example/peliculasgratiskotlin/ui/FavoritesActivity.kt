package com.example.peliculasgratiskotlin.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.peliculasgratiskotlin.R
import com.example.peliculasgratiskotlin.UserViewHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_favorites.*
import kotlinx.android.synthetic.main.activity_listado2.*
import kotlinx.android.synthetic.main.content_scrolling.*

data class pelis2(
    val Nombre: String ="",
    val Año: String = "",
    val reparto: String = "",
    val sinopsis: String = "",
    val duracion: String = "",
    val Caratula: String = "",
    val Favorita: Boolean = false
)

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class FavoritesActivity : AppCompatActivity() {

    private val db = Firebase.firestore

    //private lateinit var mInterstitialAd: InterstitialAd



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        supportActionBar!!.setTitle("Peliculas destacadas")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        /*mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-8595247018294748/2621378382"
        mInterstitialAd.loadAd(AdRequest.Builder().build())
*/

        val query = db.collection("peliculas").whereEqualTo("Favorita", true)

        val options = FirestoreRecyclerOptions.Builder<pelis2>().setQuery(query, pelis2::class.java)
            .setLifecycleOwner(this).build()

        val adapter = object: FirestoreRecyclerAdapter<pelis2, UserViewHolder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
                val view = LayoutInflater.from(this@FavoritesActivity).inflate(android.R.layout.simple_list_item_2, parent, false)
                return UserViewHolder(view)
            }

            override fun onBindViewHolder(holder: UserViewHolder, position: Int, model: pelis2) {

                //val tvFavorita: Boolean = model.Favorita

                //if(tvFavorita==true){
                    val tvNombre: TextView = holder.itemView.findViewById(android.R.id.text1)
                    val tvAnyo: TextView = holder.itemView.findViewById(android.R.id.text2)

                    tvNombre.text = model.Nombre
                    tvAnyo.text = model.Año

                    holder.itemView.setOnClickListener{v->

                        /*if (mInterstitialAd.isLoaded) {
                            mInterstitialAd.show()
                            } else {
                                Log.d("TAG", "The interstitial wasn't loaded yet.")
                            }*/
                        //Toast.makeText(v.context.applicationContext, model.nombre.toString(), Toast.LENGTH_SHORT).show()
                            val intent = Intent (v.context, PeliculaSeleccionada::class.java).apply{
                                putExtra("nombre", model.Nombre)
                            }
                        v.context.startActivity(intent)

                    }

                //}

            }

        }
        recyclerviewFavoritas.adapter = adapter
        recyclerviewFavoritas.layoutManager = LinearLayoutManager(this)

        //metodoSetup()
    }

    private fun metodoSetup() {


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