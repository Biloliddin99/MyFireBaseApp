package com.example.myfirebaseapp.fragments

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myfirebaseapp.databinding.ActivityPhonBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhonActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityPhonBinding
    private lateinit var homeFragment: HomeFragment
    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeFragment = HomeFragment()

        auth = FirebaseAuth.getInstance()
        val fragmentManager =supportFragmentManager
        fragmentManager.beginTransaction()
            .add(binding.myContainer.id,homeFragment)
            .commit()

//        binding.btnEnter.setOnClickListener {
//            val number = binding.edtNumber.text.toString().trim()
//            val bundle =Bundle()
//            bundle.putSerializable("keyCode",number)
//            sms1Fragment.arguments = bundle
//
//
//            sendVerificationCode(binding.edtNumber.text.toString().trim())
//        }
    }

//    private fun sendVerificationCode(phoneNumber:String){
//        val options = PhoneAuthOptions.newBuilder(auth)
//            .setPhoneNumber(phoneNumber)       // Phone number to verify
//            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//            .setActivity(this)                 // Activity (for callback binding)
//            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
//            .build()
//        PhoneAuthProvider.verifyPhoneNumber(options)
//    }

//    private val callbacks = object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
//        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
//            // This callback will be invoked in two situations:
//            // 1 - Instant verification. In some cases the phone number can be instantly
//            //     verified without needing to send or enter a verification code.
//            // 2 - Auto-retrieval. On some devices Google Play services can automatically
//            //     detect the incoming verification SMS and perform verification without
//            //     user action.
//            Log.d(TAG, "onVerificationCompleted:$p0")
//            signInWithPhoneAuthCredential(p0)
//        }

//        override fun onVerificationFailed(p0: FirebaseException) {
//            // This callback is invoked in an invalid request for verification is made,
//            // for instance if the the phone number format is not valid.
//            Log.w(TAG, "onVerificationFailed", p0)
//
//            if (p0 is FirebaseAuthInvalidCredentialsException) {
//                // Invalid request
//            } else if (p0 is FirebaseTooManyRequestsException) {
//                // The SMS quota for the project has been exceeded
//            }
//
//            // Show a message and update the UI
//        }
//    }

}