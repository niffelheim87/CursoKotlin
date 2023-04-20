package com.ompava.uservip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.ompava.uservip.UserVipApplication.Companion.prefs
import com.ompava.uservip.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    fun initUI(){
        binding.btnBack.setOnClickListener{
               prefs.wipe()
            onBackPressed()
        }
        val username = prefs.getName()
        val welcomeMessage = getString(R.string.bienvenido, username)
        binding.tvName.setText(welcomeMessage)
        if(prefs.getVip()){
            setVIPColorBackground()
        }
        
    }
    fun setVIPColorBackground(){
        binding.container.setBackgroundColor(ContextCompat.getColor(this, R.color.vip_yellow))
    }
}