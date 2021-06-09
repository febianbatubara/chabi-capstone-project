package com.chabi.android.chabiapp.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.chabi.android.chabiapp.R
import com.chabi.android.chabiapp.ui.starting.SignInActivity

class TwoActivity : AppCompatActivity() {

    var button2: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        button2 = findViewById(R.id.button2)

        button2?.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this@TwoActivity, SignInActivity::class.java))
        }

    }
}