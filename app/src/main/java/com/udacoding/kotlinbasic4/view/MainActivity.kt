package com.udacoding.kotlinbasic4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.udacoding.kotlinbasic4.R
import com.udacoding.kotlinbasic4.databinding.ActivityMainBinding
import com.udacoding.kotlinbasic4.model.ArticlesItems
import com.udacoding.kotlinbasic4.view.fragment.ArtisFragment
import com.udacoding.kotlinbasic4.view.fragment.OlahragaFragment
import com.udacoding.kotlinbasic4.view.fragment.TeknologiFragment
import com.udacoding.kotlinbasic4.view.fragment.TrendingFragment
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = OlahragaFragment()
        addFragment(fragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this)
//        binding.inputSearch.setOnKeyListener { v, keyCode, event ->
//            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
//
//                displayList.clear()
//
//                val search = Locale.getDefault()
//                binding.frameContainer
//
//                return@setOnKeyListener true
//            }
//            return@setOnKeyListener false
//        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_olahraga ->{
                val fragment = OlahragaFragment()
                addFragment(fragment)
                true
            }

            R.id.menu_teknologi ->{
                val fragment = TeknologiFragment()
                addFragment(fragment)

                true
            }

            R.id.menu_trending ->{
                val fragment = TrendingFragment()
                addFragment(fragment)

                true
            }

            R.id.menu_artis ->{
                val fragment = ArtisFragment()
                addFragment(fragment)

                true
            }

            else -> false
        }
    }

    private fun addFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }

}