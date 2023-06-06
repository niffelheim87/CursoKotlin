package com.ompava.video

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.ompava.video.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val mediaController:MediaController by lazy { MediaController(this) }
    val urlVideo = "https://sdram58.github.io/apuntesPMDM/unidades2223/UD9/assets/bola_drac.mp4"
    val uri: Uri = Uri.parse(urlVideo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        //val path = "android.resource://" + packageName + "/" + R.raw.bola_drac
        mediaController.setAnchorView(binding.vvVideo)
        binding.vvVideo.setMediaController(mediaController)
        //binding.vvVideo.setVideoPath(path)
        binding.vvVideo.setVideoURI(uri)
        binding.vvVideo.requestFocus()
        binding.vvVideo.setOnPreparedListener {
                mp ->
            mp.isLooping = true
            binding.vvVideo.start()
            mp.playbackParams.speed = 1.0f
        }

    }
}