package com.example.giphy.ui.detailsGif.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.giphy.ui.res.Dimens
import com.example.giphy.ui.searchGif.GifPresentationModel

@Composable
fun GifDetailItem(
    item: GifPresentationModel
) {
    Card(modifier = Modifier.fillMaxSize()) {
        Column {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.url)
                        .crossfade(true)
                        .decoderFactory(ImageDecoderDecoder.Factory())
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(Dimens.cornerRadiusNormalSpecial))
                        .fillMaxWidth()
                        .weight(1.0f),
                    contentScale = ContentScale.Fit
                )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(Dimens.spacingSmall),
                text = item.title
            )
        }
    }
}
