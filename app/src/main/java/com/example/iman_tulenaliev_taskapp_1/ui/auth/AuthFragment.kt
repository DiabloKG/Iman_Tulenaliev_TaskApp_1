package com.example.iman_tulenaliev_taskapp_1.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.iman_tulenaliev_taskapp_1.R
import com.example.iman_tulenaliev_taskapp_1.databinding.FragmentAuthBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private var auth = FirebaseAuth.getInstance()
    private var correctCode: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        initViews()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.btnSendNumber.setOnClickListener{
            sendPhone()
        }
        binding.btnConfirm.setOnClickListener{
            sendCode()
        }
    }

    private fun sendCode() {
        val credential = correctCode?.let { PhoneAuthProvider.getCredential(it, binding.etCode.text.toString()) }
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential?) {
        if (credential != null) {
            auth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.navigation_home)
                    } else {
                        Log.w("12345", "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            Log.w("12345", "signInWithCredential:failure", task.exception)
                            Toast.makeText(context, task.exception.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                }
        }
    }

    private fun initViews() {
    }

    private fun sendPhone(){
        auth.setLanguageCode("RU")
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(binding.etPhone.text.toString())
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(object : OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                }

                override fun onVerificationFailed(exception: FirebaseException) {
                    Log.e("12345", "VertificationFailed: " + exception.message.toString())
                    Toast.makeText(context, exception.message.toString(), Toast.LENGTH_LONG).show()
                }

                override fun onCodeSent(code: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(code, p1)

                    correctCode = code

                    binding.btnSendNumber.isVisible = false
                    binding.etPhone.isVisible = false

                    binding.btnConfirm.isVisible = true
                    binding.etCode.isVisible = true

                }
            } )
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}