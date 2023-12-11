package com.example.mdan16

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.mdan16.databinding.ActivityAddUserBinding
import com.example.mdan16.databinding.ActivityMainBinding

class AddUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddUserBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("db", MODE_PRIVATE)
        binding.imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_anim))
        binding.saveBtn.startAnimation(AnimationUtils.loadAnimation(this, R.anim.enter_bottom))
        binding.nameEt.startAnimation(AnimationUtils.loadAnimation(this, R.anim.enter_left))
        binding.surnameEt.startAnimation(AnimationUtils.loadAnimation(this, R.anim.enter_right))
        binding.ageEt.startAnimation(AnimationUtils.loadAnimation(this, R.anim.enter_left))

        binding.saveBtn.setOnClickListener {
            val name = binding.nameEt.text.toString()
            val surname = binding.surnameEt.text.toString()
            val age = binding.ageEt.text.toString()
            if (name.isNotBlank() && surname.isNotBlank() && age.isNotBlank()) {
                sharedPreferences.edit().putString("name", name).apply()
                sharedPreferences.edit().putString("surname", surname).apply()
                sharedPreferences.edit().putString("age", age).apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Enter informations fully !!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        if (sharedPreferences.getString("name", "") != "") {
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }
        super.onStart()
    }
}