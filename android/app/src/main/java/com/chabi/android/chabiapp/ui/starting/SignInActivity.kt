package com.chabi.android.chabiapp.ui.starting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chabi.android.chabiapp.R
import com.chabi.android.chabiapp.databinding.ActivitySignInBinding
import com.chabi.android.chabiapp.utils.Constant

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            if (binding.etName.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.please_enter_your_name, Toast.LENGTH_SHORT).show()
            } else {
                val prefs = getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putString(Constant.USER_NAME_KEY, binding.etName.text.toString())
                editor.apply()

                val intent = Intent(this, StartingActivity::class.java)
                startActivity(intent)
            }
        }
    }
}