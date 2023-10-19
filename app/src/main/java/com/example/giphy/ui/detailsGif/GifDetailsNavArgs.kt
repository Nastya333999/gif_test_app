package com.example.giphy.ui.detailsGif

import android.os.Bundle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.giphy.core.extensions.fromNavArgString
import kotlinx.serialization.Serializable

@Serializable
data class GifDetailsNavArgs(
    val args: GifDetailArgs
) {
    constructor(arguments: Bundle?) : this(args = (arguments?.getString(detailArg).orEmpty()).fromNavArgString())

    companion object {
        const val detailArg = "detailArg"

        val navArgs = listOf(
            navArgument(detailArg) {
                type = NavType.StringType
            }
        )
    }
}

@Serializable
data class GifDetailArgs(
    val searchQuery: String,
    val itemIndex: Int
)