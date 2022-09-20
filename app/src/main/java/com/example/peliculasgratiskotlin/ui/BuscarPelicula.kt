


package com.example.peliculasgratiskotlin.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.peliculasgratiskotlin.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_listado.*

class BuscarPelicula : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

   // private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)
        supportActionBar!!.setTitle("Listado de peliculas")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

       /* mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-8595247018294748/2621378382"
        mInterstitialAd.loadAd(AdRequest.Builder().build())*/

        setup()

    }



    private fun setup() {

        button.setOnClickListener {
        val name = editTextTextPersonName.editableText

            /*if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.")
            }*/

            val intent = Intent(this, PeliculaSeleccionada::class.java)
            intent.putExtra("nombre", name.toString())

            startActivity(intent)
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



