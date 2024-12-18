package com.example.homeworkviewstp2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworkviewstp2.model.Civitai
import com.example.homeworkviewstp2.model.CivitaiResponse
import com.example.homeworkviewstp2.retrofit.common.CommonCivitai
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class CivitaiViewModel : ViewModel() {
    private val _civitaiList = MutableStateFlow<List<Civitai>>(emptyList())
    val civitaiList: StateFlow<List<Civitai>> get() = _civitaiList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    private val mService = CommonCivitai.retrofitServiecesCivitai

    var currentPage = 15

    fun fetchCivitaiList(page: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            mService.getImagesList(page).enqueue(object : Callback<CivitaiResponse> {
                override fun onFailure(call: Call<CivitaiResponse>, t: Throwable) {
                    _isLoading.value = false
                    _error.value = t.message
                }

                override fun onResponse(call: Call<CivitaiResponse>, response: Response<CivitaiResponse>) {
                    _isLoading.value = false
                    if (response.isSuccessful && response.body()?.items != null) {
                        val newList = response.body()?.items!!
                        _civitaiList.value = newList
                        _error.value = null
                    } else {
                        _error.value = "Failed to load data"
                    }
                }
            })
        }
    }
}