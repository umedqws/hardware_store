package com.example.hardwarestore

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.fragment.NavHostFragment
import com.example.hardwarestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding:ActivityMainBinding
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController



        toggle  = ActionBarDrawerToggle(this,binding.drawerLayout,R.string.open,R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        binding.navigat.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1-> Toast.makeText(this,"Хай хай бля1", Toast.LENGTH_SHORT).show()
                R.id.item2-> Toast.makeText(this,"Хай хай бля2", Toast.LENGTH_SHORT).show()
                R.id.basketFragment-> navController.navigate(R.id.action_storeFragment_to_basketFragment2)

            }
            true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean{
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}