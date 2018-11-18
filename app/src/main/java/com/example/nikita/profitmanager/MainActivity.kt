package com.example.nikita.profitmanager

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.Menu


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment  = FirstFragment.newInstance()

        replaceFragment(fragment)


    }
     private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {

                val fragment  = FirstFragment.newInstance()

                replaceFragment(fragment)

                return@OnNavigationItemSelectedListener true
            }


            R.id.navigation_dashboard -> {

                val fragment  = SecondFragment.newInstance()
                replaceFragment(fragment)


                return@OnNavigationItemSelectedListener true
            }


            R.id.navigation_notifications -> {

                val fragment  = ThirdFragment.newInstance()
                replaceFragment(fragment)


                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }


    private fun replaceFragment(fragment: Fragment){
        val ft : FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentholder, fragment)
        ft.commit()

    }




}
