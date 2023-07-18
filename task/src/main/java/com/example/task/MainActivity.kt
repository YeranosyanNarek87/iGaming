package com.example.igaming.com.example.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.igaming.com.example.task.ui.welcome.WelcomeFragment
import com.example.task.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WelcomeFragment.newInstance())
                .commitNow()
        }
    }
}