package com.example.hardwarestore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.hardwarestore.R
import com.example.hardwarestore.databinding.ActivityMainBinding
import com.example.hardwarestore.model.Users
import com.example.hardwarestore.viewmodel.ActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

     val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!





        val activityViewModel = ViewModelProvider(this)[ActivityViewModel::class.java]
        navHost.findNavController()
       setupWithNavController( binding.bottomNav,navHost.findNavController())

        val a = intent.getParcelableExtra<Users>("title")

        if (a != null) {
            activityViewModel.user = a
        }




    }



}