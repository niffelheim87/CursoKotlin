package com.ompava.uservip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ompava.uservip.UserVipApplication.Companion.prefs
import com.ompava.uservip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        checkUserValues()
    }

    fun checkUserValues(){
        if(prefs.getName().isNotEmpty()){
            goToDetail()
        }
    }

    fun initUI(){
        binding.btnContinue.setOnClickListener { accesToDetail()}

    }

    fun accesToDetail() {
            if(binding.etName.text.toString().isNotEmpty()){
                 prefs.saveName(binding.etName.text.toString())
                prefs.saveVIP(binding.cbVip.isChecked)
                goToDetail()
            }else {
                    //Hacer otra cosa
            }
    }

    fun goToDetail(){
        startActivity(Intent(this, ResultActivity::class.java))
    }
}