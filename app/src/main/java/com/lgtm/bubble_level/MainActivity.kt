package com.lgtm.bubble_level

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lgtm.bubble_level.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        setContentView(binding.root)
    }
}