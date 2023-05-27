package com.example.tmdbmovieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbmovieapp.databinding.ItemHomeRecyclerViewBinding
import com.example.tmdbmovieapp.model.MovieItem
import com.example.tmdbmovieapp.util.loadCircleImage

/**
 * Created by @Emre Özcan on 27.05.2023
 */

interface MovieClickListener {
    fun onMovieClicked(movieId: Int?)
}

class MovieAdapter(private val movieList: List<MovieItem?>, private val movieClickListener: MovieClickListener): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemHomeRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]

        holder.binding.textViewMovieTitle.text = movie?.title
        holder.binding.textViewMovieOverview.text = movie?.overview
        holder.binding.textViewMovieVote.text = movie?.voteAverage.toString()

        holder.binding.imageViewMovie.loadCircleImage(movie?.posterPath)

        holder.binding.root.setOnClickListener {
            movieClickListener.onMovieClicked(movieId = movie?.id)
        }
    }
}