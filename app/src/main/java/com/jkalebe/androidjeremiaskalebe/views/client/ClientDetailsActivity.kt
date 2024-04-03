package com.jkalebe.androidjeremiaskalebe.views.client


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
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