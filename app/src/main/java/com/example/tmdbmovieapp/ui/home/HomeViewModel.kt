package com.example.tmdbmovieapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovieapp.model.MovieItem
import com.example.tmdbmovieapp.network.ApiClient
import com.example.tmdbmovieapp.util.Constants
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by @Emre Özcan on 27.05.2023
 */
class HomeViewModel : ViewModel() {

    val movieList: MutableLiveData<List<MovieItem?>?> = MutableLiveData()
    val isLoading = MutableLiveData(false)
    val errorMessage: MutableLiveData<String?> = MutableLiveData()

    init {
        getMovieList()
    }

    fun getMovieList() {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val response = ApiClient.getClient().getMovieList(token = Constants.BEARER_TOKEN)

                if (response.isSuccessful) {
                    movieList.postValue(response.body()?.movieItems)
                } else {
                    if (response.message().isNullOrEmpty()) {
                        errorMessage.value = "An unknown error occured"
                    } else {
                        errorMessage.value = response.message()
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
            finally {
                isLoading.value = false
            }
        }
    }

}