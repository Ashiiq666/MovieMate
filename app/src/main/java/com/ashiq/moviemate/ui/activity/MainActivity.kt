package com.ashiq.moviemate.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.ashiq.moviemate.R
import com.ashiq.moviemate.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
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
        val drawerLayout: DrawerLayout? = binding?.drawerLayout

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.orderListFragment, R.id.profileFragment, R.id.loginFragment
            ), drawerLayout
        )

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



    private fun handleEvents() {
        binding?.navView?.menu?.findItem(R.id.homeFragment)?.setOnMenuItemClickListener {
            navController?.navigate(R.id.homeFragment)
            binding?.drawerLayout?.close()
            true
        }
        binding?.navView?.menu?.findItem(R.id.orderListFragment)?.setOnMenuItemClickListener {
            navController?.navigate(R.id.orderListFragment)
            binding?.drawerLayout?.close()
            true
        }
        binding?.navView?.menu?.findItem(R.id.profileFragment)?.setOnMenuItemClickListener {
            navController?.navigate(R.id.profileFragment)
            binding?.drawerLayout?.close()
            true
        }
        binding?.navView?.menu?.findItem(R.id.loginFragment)?.setOnMenuItemClickListener {
            navController?.navigate(R.id.loginFragment)
            binding?.drawerLayout?.close()
            true
        }
        binding?.menuDrawer?.setOnClickListener {
            binding?.drawerLayout?.open()
        }
        binding?.root?.setOnClickListener {
            binding?.drawerLayout?.close()
        }
    }
}