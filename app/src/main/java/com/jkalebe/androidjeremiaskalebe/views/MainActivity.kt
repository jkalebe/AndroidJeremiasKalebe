package com.jkalebe.androidjeremiaskalebe.views

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jkalebe.androidjeremiaskalebe.databinding.ActivityMainBinding
import com.jkalebe.androidjeremiaskalebe.views.client.ClientDetailsActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupClickListener()
    }

    private fun setupClickListener() {
        binding.apply {
            cardClient.setOnClickListener {
                ClientDetailsActivity.navigateToClientDetailsActivity(
                    this@MainActivity
                )
            }
        }
    }

    companion object {
        fun navigateToMainActivity(context: Context) {
            val detailsIntent = Intent(context, MainActivity::class.java).apply {
                if (context !is Activity) {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            }
            context.startActivity(detailsIntent)
        }
    }
}