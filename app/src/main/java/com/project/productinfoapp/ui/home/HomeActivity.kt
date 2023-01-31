package com.project.productinfoapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.project.productinfoapp.R
import com.project.productinfoapp.ui.login.AuthActivity
import com.project.productinfoapp.utils.TokenManager
import com.project.productinfoapp.utils.startNewActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var userPreferences: TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        userPreferences = TokenManager(this)

    }

    fun performLogout() = lifecycleScope.launch {

        userPreferences.delete()
        startNewActivity(AuthActivity::class.java)
    }
}