package com.chabi.android.chabiapp.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.chabi.android.chabiapp.R
import com.chabi.android.chabiapp.data.source.local.entity.PersonalityEntity
import com.chabi.android.chabiapp.databinding.ActivityPersonalityDetailBinding
import com.chabi.android.chabiapp.utils.Constant
import com.chabi.android.chabiapp.utils.PersonalityDetailDataFactory

class PersonalityDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonalityDetailBinding
    private lateinit var personality: PersonalityEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE)
        val personalityType = prefs.getString(Constant.USER_PERSONALITY_TYPE_KEY, "")
        personality = PersonalityDetailDataFactory.getPersonalityDetail(personalityType.toString())

        binding.tvTitle.text =
            getString(R.string.personality_type_title, personality.characterName, personality.type)
        binding.tvSuperioriy.text = getString(R.string.your_superiority, personality.characteristic)
        binding.tvUniquness.text = personality.uniqueness
        binding.tvDescription.text = personality.description
        Glide.with(this)
            .load(personality.characterImageUrl)
            .placeholder(R.color.colorPrimary)
            .into(binding.ivCharacter)

        binding.btnDetailBack.setOnClickListener { this.finish() }
    }
}