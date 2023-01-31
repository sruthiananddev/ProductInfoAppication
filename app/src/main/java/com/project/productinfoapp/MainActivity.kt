package com.project.productinfoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

import com.project.productinfoapp.databinding.ActivityMainBinding
import com.project.productinfoapp.ui.home.HomeActivity
import com.project.productinfoapp.ui.login.AuthActivity
import com.project.productinfoapp.utils.TokenManager
import com.project.productinfoapp.utils.startNewActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(navController.graph)
//        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        // val activity =  AuthActivity::class.java

        val userPreferences = TokenManager(this)

//        userPreferences.accessToken.asLiveData().observe(this, Observer {
//            val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
//            startNewActivity(activity)
//
//    }

       val activity= if(userPreferences.getToken()==null)AuthActivity::class.java else HomeActivity::class.java
        startNewActivity(activity)

//if(userPreferences.getToken()==null) {
//    val intent = Intent(this, AuthActivity::class.java)
//    startActivity(intent)
//}
//        else
//        {
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//        }


    }
}