package com.example.giphy.gif.domain.usecase

import com.example.giphy.gif.domain.GifRepository
import com.example.giphy.gif.domain.models.GifDomainModel
import javax.inject.Inject

class DeleteGifUseCase @Inject constructor(private val gifRepository: GifRepository) {

    suspend operator fun invoke(gifId: String) = gifRepository.deleteGif(gifId)
}