package com.example.myfirebaseapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfirebaseapp.R
import com.example.myfirebaseapp.databinding.FragmentEnter1Binding
import com.google.firebase.auth.FirebaseAuth


class Enter1Fragment : Fragment() {

    private lateinit var binding: FragmentEnter1Binding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEnter1Binding.inflate(layoutInflater)

        val number = arguments?.get("keyNumber")
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        binding.tvPhoneNumber.text = user?.displayName

        return binding.root
    }


}