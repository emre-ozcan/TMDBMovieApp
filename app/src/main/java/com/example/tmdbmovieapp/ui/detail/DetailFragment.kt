package com.example.tmdbmovieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.tmdbmovieapp.MainActivity
import com.example.tmdbmovieapp.R
import com.example.tmdbmovieapp.databinding.FragmentDetailBinding
import com.example.tmdbmovieapp.util.loadImage


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        viewModel.getMovieDetail(movieId = args.movieId)
        observeEvents()

        return binding.root
    }

    private fun observeEvents() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBarDetail.isVisible = it
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.textViewErrorDetail.text = it
            binding.textViewErrorDetail.isVisible = true
        }
        viewModel.movieResponse.observe(viewLifecycleOwner) { movie ->
            binding.imageViewDetail.loadImage(movie.backdropPath)

            binding.textViewDetailVote.text = movie.voteAverage.toString()
            binding.textViewDetailStudio.text = movie.productionCompanies?.first()?.name
            binding.textViewDetailLanguage.text = movie.spokenLanguages?.first()?.englishName

            binding.textViewDetailOverview.text = movie.overview

            binding.movieDetailGroup.isVisible = true

            (requireActivity() as MainActivity).supportActionBar?.title = movie.title
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}