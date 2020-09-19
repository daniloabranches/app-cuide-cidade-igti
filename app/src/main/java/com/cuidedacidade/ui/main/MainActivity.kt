package com.cuidedacidade.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.cuidedacidade.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        setupNavController()
        setupFloatingButton()
    }

    private fun setupFloatingButton() {
        fab.setOnClickListener {
            findNavController(nav_host_fragment).navigate(R.id.CategoriesFragment)
        }
    }

    private fun setupNavController() {
        val navController = findNavController(nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        toolbar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.RequestsFragment, R.id.AllRequestsFragment -> fab.show()
                else -> fab.hide()
            }
        }
    }
}