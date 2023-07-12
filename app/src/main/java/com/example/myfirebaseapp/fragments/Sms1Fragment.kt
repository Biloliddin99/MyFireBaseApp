package com.example.myfirebaseapp.fragments

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import com.example.myfirebaseapp.R
import com.example.myfirebaseapp.databinding.FragmentSms1Binding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class Sms1Fragment : Fragment() {

    private lateinit var binding: FragmentSms1Binding
    private lateinit var enter1Fragment: Enter1Fragment
    private lateinit var auth: FirebaseAuth
    lateinit var storedVerificationId: String
    private var countDownTimer: CountDownTimer? = null
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSms1Binding.inflate(layoutInflater)


        enter1Fragment = Enter1Fragment()
        auth = FirebaseAuth.getInstance()
        val number = arguments?.get("keyCode")
        binding.tvSms.text = "Bir martalik kod ${number.toString()} raqamiga yuborildi"
        sendVerificationCode(number as String)

//        binding.edtCode.setOnEditorActionListener { v, actionId, event ->
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                verifyCode()
//                val imm: InputMethodManager =
//                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                imm.hideSoftInputFromWindow(view?.windowToken, 0)
//            }
//            true
//
//        }

        binding.tvTime.text = "60"
        countDownTimer = object : CountDownTimer(60000, 1000) { // 60 seconds countdown with 1 second interval
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                binding.tvTime.text = seconds.toString()
            }

            override fun onFinish() {
                Toast.makeText(requireContext(), "Your time is up", Toast.LENGTH_SHORT).show()
            }
        }
        countDownTimer?.start()

        binding.edtCode.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                verifyCode()
                val view = activity?.currentFocus
                if (view != null) {
                    val imm: InputMethodManager =
                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
            true
        }

        binding.edtCode.addTextChangedListener {
            if (it.toString().length==6){
                verifyCode()
            }
        }

        binding.reCode.setOnClickListener {
            resentCode(number.toString())
        }


        return binding.root
    }

    private fun verifyCode() {
        val code = binding.edtCode.text.toString()
        if (code.length == 6) {
            val credential = PhoneAuthProvider.getCredential(storedVerificationId, code)
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun sendVerificationCode(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun resentCode(phoneNimber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNimber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .setForceResendingToken(resendToken)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Log.d(TAG, "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w(TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(TAG, "onCodeSent:$verificationId")

            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
            resendToken = token
        }
    }


    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    val bundle = Bundle()
                    bundle.putSerializable("keyNumber", auth.currentUser.toString())
                    enter1Fragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .addToBackStack(enter1Fragment.toString())
                        .replace(R.id.my_container, enter1Fragment)
                        .commit()
                } else {
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                    if (it.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(requireContext(), "Code is wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }


}