package com.jkalebe.androidjeremiaskalebe.views.client


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.jkalebe.androidjeremiaskalebe.R
import com.jkalebe.androidjeremiaskalebe.databinding.ActivityClientDetailsBinding


class ClientDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClientDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AndroidNoActionBar)
        binding = ActivityClientDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupToolBar()

        val navController =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val bottomNavigationView = binding.bottomNavigation

        bottomNavigationView.setupWithNavController(navController.navController);

    }

    private fun setupToolBar() {
        setSupportActionBar(binding.toolbar)
    }

    companion object {
        fun navigateToClientDetailsActivity(context: Context) {
            val detailsIntent = Intent(context, ClientDetailsActivity::class.java).apply {
                if (context !is Activity) {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            }
            context.startActivity(detailsIntent)
        }
    }
}