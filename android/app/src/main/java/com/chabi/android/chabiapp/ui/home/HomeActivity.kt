package com.chabi.android.chabiapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chabi.android.chabiapp.R
import com.chabi.android.chabiapp.adapter.RecyclerViewArticle
import com.chabi.android.chabiapp.adapter.RecyclerViewVideo

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerarticle : RecyclerView
    private lateinit var recyclervideo : RecyclerView
    private lateinit var article: RecyclerViewArticle
    private lateinit var video: RecyclerViewVideo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerarticle = findViewById(R.id.rv_article)
        recyclervideo = findViewById(R.id.rv_video)
        article = RecyclerViewArticle()
        video = RecyclerViewVideo()

        recyclerarticle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,  false)
        recyclerarticle.adapter = article
        recyclervideo.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,  false)
        recyclervideo.adapter = video
    }
}