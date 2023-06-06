package com.ompava.mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ompava.mediaplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var mediaPlayer: MediaPlayer? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        val btnPlay: Button = findViewById(R.id.btnPlay)
        val btnPause: Button = findViewById(R.id.btnPause)
        val btnStop: Button = findViewById(R.id.btnStop)

        btnPlay.setOnClickListener {
            // Acción para el botón Play
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.bola_drac_intro)
            }
            mediaPlayer?.start()
            mediaPlayer?.setOnCompletionListener {
                mediaPlayer?.release()
            }
        }

        btnPause.setOnClickListener {
            // Acción para el botón Pause
            mediaPlayer?.pause()
        }

        btnStop.setOnClickListener {
            // Acción para el botón Stop
            mediaPlayer?.stop()
            mediaPlayer?.prepare()
        }
    }


    override fun onStop() {
        super.onStop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}