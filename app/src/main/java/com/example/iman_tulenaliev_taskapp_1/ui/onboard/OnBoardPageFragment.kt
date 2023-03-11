package com.example.iman_tulenaliev_taskapp_1.ui.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.iman_tulenaliev_taskapp_1.R
import com.example.iman_tulenaliev_taskapp_1.databinding.FragmentOnBoardPageBinding
import com.example.iman_tulenaliev_taskapp_1.utils.Preferences
import kotlinx.coroutines.flow.combine

class OnBoardPageFragment(
    var listenerSkip: () -> Unit,
    var listenerNext: () -> Unit
) : Fragment() {

    private lateinit var binding: FragmentOnBoardPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardPageBinding.inflate(inflater, container, false)
        initViews()
        initListeners()
        return binding.root
    }

    private fun initViews() {
        val data = arguments?.getSerializable("onBoard") as BoardModel
        data.image?.let { binding.imgboard.setImageResource(it) }
        binding.tvTitleBoard.text = data.title
        binding.tvDescBoard.text = data.desc
        binding.btnSkip.isVisible = data.isLast == false
        binding.btnNext.isVisible = data.isLast == false
        binding.btnStart.isVisible = data.isLast == true
    }

    private fun initListeners() {
        binding.btnStart.setOnClickListener {
            findNavController().navigateUp()
            Preferences(requireContext()).board = true
        }
        binding.btnSkip.setOnClickListener{
            listenerSkip.invoke()
        }
        binding.btnNext.setOnClickListener{
            listenerNext.invoke()
        }
    }
}

