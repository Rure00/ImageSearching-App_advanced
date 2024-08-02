package com.project.imagesearchingadvancedapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.project.imagesearchingadvancedapplication.databinding.ActivityMainBinding
import com.project.imagesearchingadvancedapplication.fragment.MyArchiveFragment
import com.project.imagesearchingadvancedapplication.fragment.SearchingFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val navController: NavController by lazy {
        val hostFragment =  supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        hostFragment.navController
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

                //Log.d("Navigation", "Cur fragment: ${currentFragment?.javaClass?.name}")
                when(item.itemId) {
                    R.id.navigation_search_img -> {
                        Log.d("Navigation", "Tab Search Image")
                        if(currentFragment !is SearchingFragment) {
                            navController.popBackStack(R.id.searchImageFragment, false)
                        }
                        true
                    }
                    R.id.navigation_my_archive -> {
                        Log.d("Navigation", "Tab My Archive")
                        if(currentFragment !is MyArchiveFragment) {
                            Log.d("Navigation", "From Search to MyArchive")
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