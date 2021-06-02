package com.chabi.android.chabiapp.starting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.chabi.android.chabiapp.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btn_start.setOnClickListener {
            if (et_name.text.toString().isEmpty()){
                Toast.makeText(this,R.string.please_enter_your_name, Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, StartingActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}