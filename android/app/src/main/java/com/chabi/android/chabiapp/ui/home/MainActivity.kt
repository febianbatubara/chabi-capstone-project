package com.chabi.android.chabiapp.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chabi.android.chabiapp.R
import com.chabi.android.chabiapp.adapter.RecyclerViewArticle
import com.chabi.android.chabiapp.adapter.RecyclerViewVideo
import com.chabi.android.chabiapp.databinding.ActivityHomeBinding
import com.chabi.android.chabiapp.ui.detail.PersonalityDetailActivity
import com.chabi.android.chabiapp.utils.Constant

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var article: RecyclerViewArticle
    private lateinit var video: RecyclerViewVideo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE)
        val username = prefs.getString(Constant.USER_NAME_KEY, "username")

        binding.tvUsername.text = getString(R.string.hai_username, username)

        binding.rvArticle.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        article = RecyclerViewArticle()
        binding.rvArticle.adapter = article

        binding.rvVideo.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        video = RecyclerViewVideo()
        binding.rvVideo.adapter = video

        binding.btnDetail.setOnClickListener {
            intent = Intent(this, PersonalityDetailActivity::class.java)
            startActivity(intent)
        }
    }
}