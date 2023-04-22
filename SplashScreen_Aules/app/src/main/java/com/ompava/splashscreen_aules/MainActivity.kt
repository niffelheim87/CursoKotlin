package com.ompava.splashscreen_aules


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MySplashScreen)
        super.onCreate(savedInstanceState)

        Thread.sleep(3000)
        setTheme(R.style.Base_Theme_SplashScreen_Aules)
        setContentView(R.layout.activity_main)

    }
}
