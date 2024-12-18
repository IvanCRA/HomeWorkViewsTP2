package com.example.homeworkviewstp2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.homeworkviewstp2.R
import com.example.homeworkviewstp2.databinding.FragmentCivitaiBinding
import com.example.homeworkviewstp2.databinding.FragmentImageBinding


class ImageFragment : Fragment() {

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentImageBinding.inflate(inflater, container, false)
        val view = binding.root

        val civitaiImg = ImageFragmentArgs.fromBundle(requireArguments()).message

        Glide.with(requireContext())
            .load(civitaiImg)
            .into(binding.oneImage)


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}