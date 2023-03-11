package com.example.iman_tulenaliev_taskapp_1.ui.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iman_tulenaliev_taskapp_1.R
import com.example.iman_tulenaliev_taskapp_1.databinding.FragmentOnBoardBinding
import com.example.iman_tulenaliev_taskapp_1.databinding.FragmentProfileBinding

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        val adapter = BoardingPageAdapter(childFragmentManager, this::onSkipClick, this::onNextClick)
        binding.vpBoard.adapter = adapter
        binding.springDotsIndicator.attachTo(binding.vpBoard)
    }
    private fun onSkipClick(){
        binding.vpBoard.currentItem = 2
    }
    private fun onNextClick(){
        binding.vpBoard.currentItem += 1
    }
}