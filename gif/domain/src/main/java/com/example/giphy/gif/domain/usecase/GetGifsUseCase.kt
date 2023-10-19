package com.example.giphy.gif.domain.usecase

import com.example.giphy.gif.domain.GifRepository
import javax.inject.Inject

class GetGifsUseCase @Inject constructor(private val gifRepository: GifRepository) {

    suspend operator fun invoke(searchQuery: String) = gifRepository.getGifs(searchQuery = searchQuery)
}