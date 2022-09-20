package com.example.peliculasgratiskotlin

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_trailer.*

class Trailer : YouTubeBaseActivity() {


    companion object{
        var VIDEO_ID : String = ""
        val YOUTUBE_API_KEY : String = "AIzaSyAaiPeSqVzYwJizKCEMo4gTi7hh8ZilaHw"
    }

    lateinit var youTubePlayerInit : YouTubePlayer.OnInitializedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)
        //supportActionBar!!.setTitle("TRAILER")
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)




        intent = this.intent

        val name = intent.getStringExtra("trailerName").toString()

        VIDEO_ID = name

        //val int = YouTubeStandalonePlayer.createVideoIntent(parent, YOUTUBE_API_KEY, VIDEO_ID)

        initUI()


    }

    private fun initUI() {
        youTubePlayerInit = object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                youtubePlayer: YouTubePlayer?,
                p2: Boolean
            ) {
                youtubePlayer?.loadVideo(VIDEO_ID)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext, "Algo falló", Toast.LENGTH_LONG).show()
            }

        }

        btnPlay.setOnClickListener(View.OnClickListener { v -> youtubeplayer.initialize(
            YOUTUBE_API_KEY, youTubePlayerInit) })
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