package com.example.homeworkviewstp2.fragment

import android.content.Context
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkviewstp2.adapter.CivitaiAdapter
import com.example.homeworkviewstp2.databinding.FragmentCivitaiBinding
import com.example.homeworkviewstp2.model.Civitai
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
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: CivitaiAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCivitaiBinding.inflate(inflater, container, false)

        val view = binding.root
        viewModel = ViewModelProvider(this).get(CivitaiViewModel::class.java)


        mService = CommonCivitai.retrofitServiecesCivitai

        layoutManager = LinearLayoutManager(requireContext())
        binding.tasksListCivitai.layoutManager = layoutManager

        getAllCivitaiList()
        return view
    }

    private fun getAllCivitaiList() {
        Log.d("CivitaiFragment", "Fetching data from API...")
        mService.getImagesList().enqueue(object : Callback<MutableList<Civitai>> {
            override fun onFailure(call: Call<MutableList<Civitai>>, t: Throwable) {
                Log.e("CivitaiFragment", "Error fetching data", t)
            }

            override fun onResponse(
                call: Call<MutableList<Civitai>>,
                response: Response<MutableList<Civitai>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d("CivitaiFragment", "Data received: ${response.body()}")
                    adapter = CivitaiAdapter(requireContext(), response.body()!!)
                    binding.tasksListCivitai.adapter = adapter
                    adapter.notifyDataSetChanged()
                } else {
                    Log.e("CivitaiFragment", "Response failed or empty")
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}