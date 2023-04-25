package com.ompava.myrecycler

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ompava.myrecycler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val currentOrientation = resources.configuration.orientation

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            val listSerieFragment = ListSerie()
            val detailsSerieFragment = DetailsSerie()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.containerList, listSerieFragment)
                replace(R.id.containerDetail, detailsSerieFragment)
                commit()
            }

        } else {
            val listSerieFragment = ListSerie()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.containerList, listSerieFragment)
                commit()
            }

        }


    }


}