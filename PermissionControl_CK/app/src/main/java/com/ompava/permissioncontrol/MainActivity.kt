package com.ompava.permissioncontrol

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ompava.permissioncontrol.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inicializamos el objeto binding con la vista de la actividad principal
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Asociamos el evento click del botón a la función checkPermission()
        binding.btnPhoto.setOnClickListener { checkPermission() }

    }
    //Función para verificar si se tienen los permisos necesarios para acceder a la cámara
    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //Si el permiso no ha sido concedido, se solicita al usuario
            requestCameraPermission()
        } else {
            //Si el permiso ya ha sido concedido, se abre la cámara
            openCamera()

        }
    }

    //Función para abrir la cámara
    private fun openCamera() {
        Toast.makeText(this, "Abriendo camara", Toast.LENGTH_SHORT).show()
    }

    //Función para solicitar el permiso de la cámara
    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            //Si el usuario ha rechazado previamente los permisos, se muestra un mensaje
            Toast.makeText(this, "Permisos rechazados", Toast.LENGTH_SHORT).show()
        } else {
            //Si el permiso no ha sido concedido, se solicita al usuario
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 777)
        }
    }

    //Función para manejar la respuesta de la solicitud de permisos
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 777){ // Nuestro permiso
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Si el permiso ha sido concedido, se abre la cámara
                openCamera()
            } else {
                //Si el permiso ha sido rechazado, se muestra un mensaje
                Toast.makeText(this, "Permisos rechazados por primera vez", Toast.LENGTH_SHORT).show()
            }
        }
    }
}