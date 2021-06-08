package com.chabi.android.chabiapp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chabi.android.chabiapp.databinding.ActivityPersonalityDetailBinding

class PersonalityDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonalityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDetailBack.setOnClickListener { this.finish() }
    }
}