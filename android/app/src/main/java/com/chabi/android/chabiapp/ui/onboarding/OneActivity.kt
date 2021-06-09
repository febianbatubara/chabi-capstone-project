package com.chabi.android.chabiapp.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.chabi.android.chabiapp.R
import com.chabi.android.chabiapp.ui.starting.SignInActivity

class OneActivity : AppCompatActivity() {

    var button: Button? = null
    var button2: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)

        button2 = findViewById(R.id.button2)
        button = findViewById(R.id.button)

        button2?.setOnClickListener {
            startActivity(Intent(this@OneActivity, TwoActivity::class.java))
        }

        button?.setOnClickListener {
            finishAffinity()
            val intent = Intent(this@OneActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}