package com.example.hardwarestore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.hardwarestore.R

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_test) as NavHostFragment
        val navController = navHostFragment.navController


    }


}