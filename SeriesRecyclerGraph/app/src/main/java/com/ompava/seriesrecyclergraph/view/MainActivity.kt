package com.ompava.seriesrecyclergraph.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.ompava.seriesrecyclergraph.OnItemClick
import com.ompava.seriesrecyclergraph.R
import com.ompava.seriesrecyclergraph.databinding.ActivityMainBinding
import com.ompava.seriesrecyclergraph.model.Serie

class MainActivity : AppCompatActivity(), OnItemClick {

    private lateinit var binding: ActivityMainBinding
    var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentPosition = savedInstanceState?.getInt("position") ?: 0

        loadRecyclerView()

    }

    fun isLandScape(): Boolean {
        return resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    private fun loadRecyclerView() {
        loadListFragment()
        if (isLandScape()) {
            loadDetailFragment(currentPosition)
        }
    }

    private fun loadListFragment() {
        val seriesListFragment = SeriesListFragment.newInstance(currentPosition)
        seriesListFragment.listener = this
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerList, SeriesListFragment.newInstance(currentPosition))
            .addToBackStack(null).commit()
    }

    private fun loadDetailFragment(position: Int) {
        val id = if (isLandScape()) R.id.containerDetail else R.id.containerList

        supportFragmentManager.beginTransaction()
            .replace(id, SeriesDetailsFragment.newInstance(position)).addToBackStack(null).commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("position", currentPosition)
    }

    override fun onItemClick(serie: Serie, position: Int) {
        Log.d("click", "Has clicado en ${serie.name}")
        currentPosition = position
        val toastText = "Clicked on ${serie.name}"
        if (isLandScape()) {
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
            loadDetailFragment(position)
        } else {
            val snackbar = Snackbar.make(binding.root, "", Snackbar.LENGTH_LONG)
            snackbar.setAction("Ver detalles de ${serie.name}") {
                loadDetailFragment(position)
            }
            snackbar.show()
        }


    }

}