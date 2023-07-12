package com.example.myfirebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfirebaseapp.databinding.ActivitySecondBinding
import com.google.firebase.auth.FirebaseAuth

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        binding.tv.text = user?.displayName
    }
}