package com.example.homeworkviewstp2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.homeworkviewstp2.adapter.CivitaiAdapter
import com.example.homeworkviewstp2.databinding.FragmentCivitaiBinding
import com.example.homeworkviewstp2.viewmodel.CivitaiViewModel
import kotlinx.coroutines.launch


class CivitaiFragment : Fragment() {

    private var _binding: FragmentCivitaiBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CivitaiViewModel
    private lateinit var adapter: CivitaiAdapter


    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCivitaiBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(CivitaiViewModel::class.java)
        setupRecyclerView()
        observeState()

        viewModel.fetchCivitaiList(viewModel.currentPage)

        return view
    }

    private fun setupRecyclerView() {
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.tasksListCivitai.layoutManager = layoutManager
        adapter = CivitaiAdapter(requireContext(), mutableListOf())
        binding.tasksListCivitai.adapter = adapter

        binding.tasksListCivitai.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading && isLastItemVisible(layoutManager)) {
                    isLoading = true
                    viewModel.currentPage += 15
                    viewModel.fetchCivitaiList(viewModel.currentPage)
                }
            }
        })
    }

    private fun isLastItemVisible(layoutManager: StaggeredGridLayoutManager): Boolean {
        val positions = IntArray(layoutManager.spanCount)
        layoutManager.findLastVisibleItemPositions(positions)
        val lastVisibleItemPosition = positions.maxOrNull() ?: 0
        val totalItemCount = layoutManager.itemCount
        return lastVisibleItemPosition >= totalItemCount - 1
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
                    this@CivitaiFragment.isLoading = isLoading
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