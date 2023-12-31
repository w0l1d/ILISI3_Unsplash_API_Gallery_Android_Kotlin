package com.ilisi.myimagesapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilisi.myimagesapplication.model.MyImageResponse
import com.ilisi.myimagesapplication.networking.ApiConfig
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class MainViewModel() : ViewModel() {

    private val _imagesData = MutableLiveData<List<MyImageResponse>>()
    val imagesData: LiveData<List<MyImageResponse>> get() = _imagesData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError

    var errorMessage: String = ""
        private set

    fun getImagesData(count : Int = 30) {

        _isLoading.value = true
        _isError.value = false

        val client = ApiConfig.getApiService().getImages(count = count)

        // Send API request using Retrofit
        client.enqueue(object : Callback<List<MyImageResponse>> {

            override fun onResponse(
                call: Call<List<MyImageResponse>>,
                response: Response<List<MyImageResponse>>
            ) {
                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null) {
                    onError("Data Processing Error")
                    return
                }


                _isLoading.value = false
                _imagesData.postValue(responseBody)
            }

            override fun onFailure(call: Call<List<MyImageResponse>>, t: Throwable) {
                onError(t.message)
                t.printStackTrace()
            }

        })
    }

    private fun onError(inputMessage: String?) {

        val message = if (inputMessage.isNullOrBlank() or inputMessage.isNullOrEmpty()) "Unknown Error"
        else inputMessage

        errorMessage = StringBuilder("ERROR: ")
            .append("$message some data may not displayed properly").toString()

        _isError.value = true
        _isLoading.value = false
    }

}