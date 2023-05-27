package com.example.tmdbmovieapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovieapp.model.MovieDetailResponse
import com.example.tmdbmovieapp.network.ApiClient
import com.example.tmdbmovieapp.util.Constants
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by @Emre Özcan on 27.05.2023
 */
class DetailViewModel : ViewModel() {

    val movieResponse: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val isLoading = MutableLiveData(false)
    val errorMessage: MutableLiveData<String?> = MutableLiveData()

    fun getMovieDetail(movieId: Int) {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val response = ApiClient.getClient().getMovieDetail(movieId = movieId.toString(), token = Constants.BEARER_TOKEN)

                if (response.isSuccessful) {
                    movieResponse.postValue(response.body())
                } else {
                    if (response.message().isNullOrEmpty()) {
                        errorMessage.value = "An unknown error occured"
                    } else {
                        errorMessage.value = response.message()
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }
}