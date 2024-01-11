package com.example.hse_application

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.RawRes
import java.lang.Math as Math1


class MainActivity : AppCompatActivity() {

    private var postcardImageView : ImageView? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postcardImageView = findViewById<ImageView>(R.id.image)
        val audioPlayer = AudioPlayer1()
        postcardImageView?.setOnClickListener {
            audioPlayer.playFromFile(this@MainActivity, R.raw.sound)
        }

    }

}

class AudioPlayer1{
    private var mediaPlayer: MediaPlayer? = null

    fun playFromFile(context: Context, @RawRes fileid : Int){
        stopPlaying()
        mediaPlayer = MediaPlayer.create(context, fileid)
        mediaPlayer?.setOnCompletionListener {
            // mediaPlayer?.stop()
            mediaPlayer?.start()
        }
        mediaPlayer?.start()
    }

    fun stopPlaying() {
        mediaPlayer?.stop()
    }
}