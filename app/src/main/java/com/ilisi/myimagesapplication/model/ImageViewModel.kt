package com.ilisi.myimagesapplication.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilisi.myimagesapplication.service.RetrofitClient
import kotlinx.coroutines.launch

class ImageViewModel : ViewModel() {

    private val _images = MutableLiveData<List<String>>()
    val images: LiveData<List<String>> = _images

    init {
        viewModelScope.launch {
            getImages()
        }
    }

    private suspend fun getImages() {
        _images.value = RetrofitClient.ImageApiService.getImages()
    }
}