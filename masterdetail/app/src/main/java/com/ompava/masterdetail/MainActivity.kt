package com.ompava.masterdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar
import com.ompava.masterdetail.databinding.ActivityMainBinding
import com.ompava.masterdetail.model.SuperHero

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val layoutList: FrameLayout by lazy { findViewById(R.id.containerList) }
    private val layoutDetail:FrameLayout? by lazy { findViewById(R.id.containerDetail) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Load ListFragment on its container. (layoutList)
        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        supportFragmentManager.beginTransaction()
            .replace(layoutList.id, ListFragment.newInstance(1))
            .addToBackStack(null)
            .commit()
    }

    /*Work around that let  us to know if we're un landscape mode*/
    private fun isLandScape():Boolean {
        return layoutDetail != null
    }

    private fun loadDetailFragment(twoPane:Boolean, heroID:Int){
        //If layoutDetail is null (we're in portrait mode) then we load DetailFragment in layoutList
        val id = layoutDetail?.id?:layoutList.id

        supportFragmentManager.beginTransaction()
            .replace(id, DetailFragment.newInstance(heroID))
            .addToBackStack(null)
            .commit()
    }

}