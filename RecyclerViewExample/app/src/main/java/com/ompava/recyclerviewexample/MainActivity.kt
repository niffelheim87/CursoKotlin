package com.ompava.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import com.ompava.recyclerviewexample.adapter.SuperHeroAdapter
import com.ompava.recyclerviewexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        binding.recyclerSuperHero.layoutManager = manager
        binding.recyclerSuperHero.adapter =
            SuperHeroAdapter(SuperHeroProvider.superHeroList, { superHero ->
                onItemSelected(
                    superHero
                )
            })

    }

    fun onItemSelected(superHero: SuperHero) {
        Toast.makeText(this, superHero.superhero, Toast.LENGTH_SHORT).show()
    }
}