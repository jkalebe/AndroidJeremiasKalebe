package com.jkalebe.androidjeremiaskalebe.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.jkalebe.androidjeremiaskalebe.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AndroidNoActionBar)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            MainActivity.navigateToMainActivity(this@SplashActivity)
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }

    companion object {
        private const val SPLASH_DISPLAY_LENGTH = 1000L
    }
}