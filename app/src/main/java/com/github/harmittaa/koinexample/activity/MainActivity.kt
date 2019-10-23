package com.github.harmittaa.koinexample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.harmittaa.koinexample.R
import com.github.harmittaa.koinexample.fragment.WeatherFragment
import com.github.harmittaa.koinexample.persistence.Preferences
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val preferences: Preferences by inject()
    private val exampleFragment: WeatherFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        if (preferences.getShouldShowFragment()) {
            supportFragmentManager.beginTransaction().replace(R.id.root, exampleFragment, "weather").commit()
        }
    }
}
