package com.project.imagesearchingadvancedapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.project.imagesearchingadvancedapplication.R
import com.project.imagesearchingadvancedapplication.databinding.ActivityMainBinding
import com.project.imagesearchingadvancedapplication.ui.fragment.MyArchiveFragment
import com.project.imagesearchingadvancedapplication.ui.fragment.SearchingFragment
import com.project.imagesearchingadvancedapplication.ui.viewmodel.MainViewModel
import com.project.imagesearchingadvancedapplication.ui.viewmodel.factory.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val navController: NavController by lazy {
        val hostFragment =  supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        hostFragment.navController
    }
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding.bnMenu) { v, insets ->
            v.setPadding(10, 0, 0, 10)
            insets
        }

        setBottomNav()
    }

    private fun setBottomNav() {
        with(binding.bnMenu) {
            setupWithNavController(navController)
            setOnItemSelectedListener { item ->
                val currentFragment = supportFragmentManager
                    .findFragmentById(R.id.nav_host_fragment_container)!!
                    .childFragmentManager
                    .fragments[0]

                Log.d("Navigation", "backStack: ${navController.currentBackStack.value.size}")


                when(item.itemId) {
                    R.id.navigation_search_img -> {
                        if(currentFragment !is SearchingFragment) {
                            Log.d("Navigation", "to Search")
                            navController.popBackStack()
                        }
                        true
                    }
                    R.id.navigation_my_archive -> {
                        if(currentFragment !is MyArchiveFragment) {
                            Log.d("Navigation", "to MyArchive")
                            navController.navigate(R.id.action_searchImageFragment_to_myArchiveFragment)
                        }
                        true
                    }
                    else -> {
                        Log.i("Navigation", "wtf")
                        return@setOnItemSelectedListener false
                    }
                }

            }
        }


    }
}