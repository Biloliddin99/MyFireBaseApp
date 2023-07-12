package com.example.myfirebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myfirebaseapp.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    lateinit var googleSignInClient:GoogleSignInClient
    private lateinit var auth:FirebaseAuth

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//
//        googleSignInClient = GoogleSignIn.getClient(this, gso)
//
//        auth = FirebaseAuth.getInstance()
//
//        binding.btnNext.setOnClickListener {
//            signIn()
//        }

    }

//    private fun signIn() {
//        val signInIntent = googleSignInClient.signInIntent
//        startActivityForResult(signInIntent,1)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode==1){
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = task.getResult(ApiException::class.java)!!
//                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
//                firebaseAuthWithGoogle(account.idToken!!)
//            }catch (e:ApiException){
//                Log.w(TAG, "Google sig in failed",e )
//            }
//        }
//    }
//
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithCredential:success")
//                    val user = auth.currentUser
////                    updateUI(user)
//                    Toast.makeText(this, "${user?.email}", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(this,SecondActivity::class.java))
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
////                    updateUI(null)
//                    Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }
}