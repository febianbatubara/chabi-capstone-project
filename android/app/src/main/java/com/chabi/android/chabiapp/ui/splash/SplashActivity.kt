package com.chabi.android.chabiapp.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.chabi.android.chabiapp.R
import com.chabi.android.chabiapp.ui.home.MainActivity
import com.chabi.android.chabiapp.ui.starting.SignInActivity
import com.chabi.android.chabiapp.utils.Constant

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val prefs = getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE)
        val personalityType = prefs.getString(Constant.USER_PERSONALITY_TYPE_KEY, "")

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = if (personalityType.isNullOrEmpty()) {
                Intent(this@SplashActivity, SignInActivity::class.java)
            } else {
                Intent(this@SplashActivity, MainActivity::class.java)
            }

            startActivity(intent)
            finish()
        }, 2000)
    }
}