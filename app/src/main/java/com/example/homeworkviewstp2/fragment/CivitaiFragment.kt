package com.example.homeworkviewstp2.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.homeworkviewstp2.adapter.CivitaiAdapter
import com.example.homeworkviewstp2.databinding.FragmentCivitaiBinding
import com.example.homeworkviewstp2.model.Civitai
import com.example.homeworkviewstp2.model.CivitaiResponse
import com.example.homeworkviewstp2.retrofit.RetrofitServiecesCivitai
import com.example.homeworkviewstp2.retrofit.common.CommonCivitai
import com.example.homeworkviewstp2.viewmodel.CivitaiViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CivitaiFragment : Fragment() {

    private var _binding: FragmentCivitaiBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CivitaiViewModel

    lateinit var mService: RetrofitServiecesCivitai
    lateinit var adapter: CivitaiAdapter
    private val civitaiList = mutableListOf<Civitai>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCivitaiBinding.inflate(inflater, container, false)

        val view = binding.root
        viewModel = ViewModelProvider(this).get(CivitaiViewModel::class.java)


        mService = CommonCivitai.retrofitServiecesCivitai

        setupRecyclerView()
        getAllCivitaiList()
        return view
    }

    private fun setupRecyclerView() {
        binding.tasksListCivitai.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        adapter = CivitaiAdapter(requireContext(), civitaiList)
        binding.tasksListCivitai.adapter = adapter
    }

    private fun getAllCivitaiList() {
        mService.getImagesList().enqueue(object : Callback<CivitaiResponse> {
            override fun onFailure(call: Call<CivitaiResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<CivitaiResponse>,
                response: Response<CivitaiResponse>
            ) {
                if (response.isSuccessful && response.body()?.items != null) {
                    civitaiList.clear()
                    civitaiList.addAll(response.body()?.items!!)
                    adapter.notifyDataSetChanged()
                } else {

                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
