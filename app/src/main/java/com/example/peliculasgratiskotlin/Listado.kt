package com.example.peliculasgratiskotlin

//import com.google.firebase.database.DatabaseReference
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
import com.example.peliculasgratiskotlin.ui.PeliculaSeleccionada
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_listado2.*

data class pelis(
        val Nombre: String ="",
        val Año: String = "",
        val reparto: String = "",
        val sinopsis: String = "",
        val duracion: String = "",
        val Caratula: String = ""
)

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class Listado : AppCompatActivity() {

    private val db = Firebase.firestore

    //private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado2)
        supportActionBar!!.setTitle("Buscar pelicula")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

       /*mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-8595247018294748/2621378382"
        mInterstitialAd.loadAd(AdRequest.Builder().build())*/

        val query = db.collection("peliculas")

        val options = FirestoreRecyclerOptions.Builder<pelis>().setQuery(query, pelis::class.java)
                .setLifecycleOwner(this).build()

        val adapter = object: FirestoreRecyclerAdapter<pelis, UserViewHolder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
                val view = LayoutInflater.from(this@Listado).inflate(android.R.layout.simple_list_item_2, parent, false)
                return UserViewHolder(view)
            }

            override fun onBindViewHolder(holder: UserViewHolder, position: Int, model: pelis) {

                val tvNombre: TextView = holder.itemView.findViewById(android.R.id.text1)
                //val tvCaratula: ImageView = holder.itemView.findViewById(android.R.id.text2)
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

            }
        }

        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

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