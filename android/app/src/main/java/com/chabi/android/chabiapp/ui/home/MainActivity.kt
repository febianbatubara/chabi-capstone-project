package com.chabi.android.chabiapp.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chabi.android.chabiapp.R
import com.chabi.android.chabiapp.adapter.ListVideoAdapter
import com.chabi.android.chabiapp.data.source.local.entity.PersonalityEntity
import com.chabi.android.chabiapp.databinding.ActivityHomeBinding
import com.chabi.android.chabiapp.ui.detail.PersonalityDetailActivity
import com.chabi.android.chabiapp.ui.detail.VideoDetailActivity
import com.chabi.android.chabiapp.utils.Constant
import com.chabi.android.chabiapp.utils.PersonalityDetailDataFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val listVideoAdapter by lazy { ListVideoAdapter(this) }
    private lateinit var personality: PersonalityEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE)
        val username = prefs.getString(Constant.USER_NAME_KEY, "username")
        val personalityType = prefs.getString(Constant.USER_PERSONALITY_TYPE_KEY, "")
        personality = PersonalityDetailDataFactory.getPersonalityDetail(personalityType.toString())

        showDetail(username, personalityType)
        setupRecyclerView()

        val videoUrlList = personality.videoUrl?.split(";")
        videoUrlList?.let { listVideoAdapter.setData(it) }

        binding.btnDetail.setOnClickListener {
            intent = Intent(this, PersonalityDetailActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showDetail(username: String?, personalityType: String?) {
        binding.tvUsername.text = getString(R.string.hai_username, username)
        binding.tvMbtiPersonality.text = getString(R.string.mbti_test_result, personalityType)
        binding.tvPersonality.text = personality.characterName
        binding.tvPersonalitySuperiority.text =
            getString(R.string.your_superiority, personality.characteristic)
        binding.tvPersonalityUniqueness.text = personality.uniqueness
    }

    private fun setupRecyclerView() {
        binding.rvVideo.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvVideo.adapter = listVideoAdapter
        binding.rvVideo.setHasFixedSize(true)

        listVideoAdapter.setOnItemClickCallback(object : ListVideoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: String) {
                val intent = Intent(this@MainActivity, VideoDetailActivity::class.java)
                intent.putExtra(VIDEO_URL_KEY, data)
                startActivity(intent)
            }
        })
    }

    companion object {
        const val VIDEO_URL_KEY = "video_url_key"
    }
}