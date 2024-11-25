package com.example.homeworkviewstp2.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.homeworkviewstp2.adapter.CivitaiAdapter
import com.example.homeworkviewstp2.databinding.FragmentCivitaiBinding
import com.example.homeworkviewstp2.model.Civitai
import com.example.homeworkviewstp2.model.CivitaiResponse
import com.example.homeworkviewstp2.retrofit.RetrofitServiecesCivitai
import com.example.homeworkviewstp2.retrofit.common.CommonCivitai
import com.example.homeworkviewstp2.viewmodel.CivitaiViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CivitaiFragment : Fragment() {

    private var _binding: FragmentCivitaiBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CivitaiViewModel
    private lateinit var adapter: CivitaiAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCivitaiBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(CivitaiViewModel::class.java)
        setupRecyclerView()
        observeState()

        viewModel.fetchCivitaiList()

        return view
    }

    private fun setupRecyclerView() {
        binding.tasksListCivitai.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        adapter = CivitaiAdapter(requireContext(), mutableListOf())
        binding.tasksListCivitai.adapter = adapter
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                viewModel.civitaiList.collect { list ->
                    adapter.updateData(list)
                }
            }

            launch {
                viewModel.isLoading.collect { isLoading ->
                    binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                }
            }

            launch {
                viewModel.error.collect { error ->
                    error?.let {

                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}