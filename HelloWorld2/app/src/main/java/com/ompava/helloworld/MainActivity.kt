package com.ompava.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // Iniciamos mas tarde
    private lateinit var btnChangeActivity: Button
    private lateinit var etName: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // asignamos binding a etiquetas
        btnChangeActivity = findViewById(R.id.btnChangeActivity)
        etName = findViewById(R.id.etName)

        // asignamos listener
        btnChangeActivity.setOnClickListener { checkValue() }
    }

    // funcion que comprueba valor si el et esta vacio, si no lo esta lo pasamos como intent
    private fun checkValue() {
        if (etName.text.toString().isNotEmpty()) {
            val intent = Intent(this, ShowNameActivity::class.java)
            intent.putExtra("INTENT_NAME", etName.text.toString())
            startActivity(intent)
        } else {
            showErrorName()
        }
    }

    private fun showErrorName() {
        Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
    }
}