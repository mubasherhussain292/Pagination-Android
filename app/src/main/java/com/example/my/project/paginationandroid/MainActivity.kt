package com.example.my.project.paginationandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.my.project.paginationandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
    }

    private fun setUpView() {
        binding.bottomNavigation.setupWithNavController(
            findNavController(R.id.nav_host_fragment)
        )
    }
}