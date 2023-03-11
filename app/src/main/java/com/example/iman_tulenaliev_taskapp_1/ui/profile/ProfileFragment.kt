package com.example.iman_tulenaliev_taskapp_1.ui.profile

import android.app.Instrumentation.ActivityResult
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.iman_tulenaliev_taskapp_1.R
import com.example.iman_tulenaliev_taskapp_1.databinding.FragmentHomeBinding
import com.example.iman_tulenaliev_taskapp_1.databinding.FragmentProfileBinding
import com.example.iman_tulenaliev_taskapp_1.utils.Preferences
import com.example.iman_tulenaliev_taskapp_1.utils.loadImage

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val selectImageFromGalleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            binding.imgProfile.loadImage(uri.toString())
            Preferences(requireContext()).imageProfile = uri.toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        initViews()
        initListers()

        return binding.root
    }

    private fun initListers() {
        binding.imgProfile.setOnClickListener{
            selectImageFromGalleryResult.launch("image/*")
        }
    }

    private fun initViews() {
        binding.imgProfile.loadImage(Preferences(requireContext()).imageProfile.toString())
        binding.etProfile.setText(Preferences(requireContext()).nameProfle)
    }

    override fun onPause() {
        super.onPause()
        Preferences(requireContext()).nameProfle = binding.etProfile.text.toString()
    }
}