package com.demo.dailynews.ui.news.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.demo.dailynews.R
import com.demo.dailynews.databinding.ActivityHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HostActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_host)
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_host)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_host)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}