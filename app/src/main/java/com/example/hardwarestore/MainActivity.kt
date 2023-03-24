package com.example.hardwarestore

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.plusAssign
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
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