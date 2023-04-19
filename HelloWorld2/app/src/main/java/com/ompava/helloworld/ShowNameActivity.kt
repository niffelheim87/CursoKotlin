package com.ompava.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ShowNameActivity : AppCompatActivity() {

    private lateinit var btnBack: Button
    private lateinit var tvGreeting: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_name)

        // asignamos binding a etiquetas
        btnBack = findViewById(R.id.btnBack)
        tvGreeting = findViewById(R.id.tvGreeting)

        getAndShowName()
        btnBack.setOnClickListener { onBackPressed() }
    }

    private fun getAndShowName(){
        val bundle = intent.extras
        val name = bundle?.getString("INTENT_NAME")
        tvGreeting.text = getString(R.string.welcome, name)
    }
}