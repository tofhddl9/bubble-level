package com.lgtm.default_Android_Project_Template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lgtm.default_Android_Project_Template.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}