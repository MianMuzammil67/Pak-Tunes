package com.example.paktunes.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.paktunes.R
import com.example.paktunes.ui.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var glide: RequestManager
    private lateinit var navHost: NavHostFragment
    val authViewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

            navHost =
                supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHost.navController

        val currentUser = authViewModel.currentUser
        if (currentUser != null){
//            val navController = findNavController(R.id.navHostFragment)
//            navController.navigate(R.id.homeFragment)
            navHost.findNavController().navigate(R.id.homeFragment)
        }
    }
}