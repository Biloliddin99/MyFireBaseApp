package com.example.myfirebaseapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfirebaseapp.R
import com.example.myfirebaseapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var sms1Fragment: Sms1Fragment
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        sms1Fragment = Sms1Fragment()

        binding.btnEnter.setOnClickListener {
            val number = binding.edtNumber.text.toString().trim()
            val bundle = Bundle()
            bundle.putSerializable("keyCode",number)
            sms1Fragment.arguments = bundle
            parentFragmentManager.beginTransaction()
                .addToBackStack(sms1Fragment.toString())
                .replace(R.id.my_container,sms1Fragment)
                .commit()
        }

        return binding.root
    }


}