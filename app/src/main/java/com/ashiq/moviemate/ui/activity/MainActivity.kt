package com.ashiq.moviemate.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.ashiq.moviemate.R
import com.ashiq.moviemate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var navController: NavController? = null
    private var navHostFragment: NavHostFragment? = null
    private var graph: NavGraph? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        init()
        handleEvents()
    }

    private fun init() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val inflater = navHostFragment?.navController?.navInflater
        graph = inflater?.inflate(R.navigation.nav_graph)
        setDestination()
    }

    private fun setDestination() {

        graph?.setStartDestination(R.id.homeFragment)


        navController = navHostFragment?.navController
        graph?.let { navController?.setGraph(it, intent.extras) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeFragment -> {
                navController?.navigate(R.id.homeFragment)
                binding?.drawerLayout?.close()
            }
            R.id.nav_order -> {
                navController?.navigate(R.id.orderListFragment)
                binding?.drawerLayout?.close()
            }
            R.id.nav_profile -> {
                navController?.navigate(R.id.profileFragment)
                binding?.drawerLayout?.close()
            }
            R.id.nav_logout -> {
                navController?.navigate(R.id.loginFragment)
                binding?.drawerLayout?.close()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleEvents() {
        binding?.menuDrawer?.setOnClickListener {
            binding?.drawerLayout?.open()
        }
        binding?.root?.setOnClickListener {
            binding?.drawerLayout?.close()
        }
    }
}