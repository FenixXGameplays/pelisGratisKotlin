package com.example.peliculasgratiskotlin

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pelicula.*

class Pelicula : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        intent = this.intent

        val url = intent.getStringExtra("enlacePelicula").toString()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelicula)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        val onlineUri = Uri.parse(url)

        videoView.setMediaController(mediaController)
        videoView.setVideoURI(onlineUri)
        videoView.requestFocus()
        videoView.start()
    }
}