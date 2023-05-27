package com.example.tmdbmovieapp.network

import com.example.tmdbmovieapp.model.MovieDetailResponse
import com.example.tmdbmovieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * Created by @Emre Özcan on 27.05.2023
 */
interface ApiService {

    @GET("popular")
    suspend fun getMovieList(@Header("Authorization") token: String): Response<MovieResponse>

    @GET("{movieId}")
    suspend fun getMovieDetail(@Path("movieId") movieId: String, @Header("Authorization") token: String) : Response<MovieDetailResponse>

}