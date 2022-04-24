package com.a5k.moviereviews.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.a5k.moviereviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var vb: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
        initFragment()
    }

    private fun initFragment() {
        vb?.fragmentContainer?.id?.let {
            supportFragmentManager.beginTransaction()
                .replace(it, MainFragment.newInstance())
                .commit()
        }
    }
}