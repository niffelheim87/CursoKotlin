package com.ompava.lottietest

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieCompositionFactory

class MainActivity : AppCompatActivity() {
    private lateinit var animationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animationView = findViewById(R.id.animationView)

        val animationRawResId = R.raw.animation

            animationView.setAnimation(animationRawResId)
            animationView.playAnimation()

    }

    override fun onResume() {
        super.onResume()
        animationView.resumeAnimation()
    }

    override fun onPause() {
        super.onPause()
        animationView.pauseAnimation()
    }

    override fun onDestroy() {
        super.onDestroy()
        animationView.cancelAnimation()
    }
}