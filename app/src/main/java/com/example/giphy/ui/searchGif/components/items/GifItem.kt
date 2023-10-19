package com.example.giphy.ui.searchGif.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.giphy.ui.res.Dimens
import com.example.giphy.ui.res.icons.GifIcons
import com.example.giphy.ui.res.icons.IcDelete
import com.example.giphy.ui.searchGif.GifPresentationModel

@Composable
fun GifItem(
    item: GifPresentationModel,
    onDeleteItem: (GifPresentationModel) -> Unit,
    onItemClick: (GifPresentationModel) -> Unit
) {
    Card(
        modifier = Modifier.clickable { onItemClick(item) }
    ) {
        Column {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.url)
                        .decoderFactory(ImageDecoderDecoder.Factory())
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(Dimens.cornerRadiusNormalSpecial))
                        .fillMaxWidth()
                        .height(Dimens.gifHeight),
                    contentScale = ContentScale.Crop
                )

                Box(modifier = Modifier
                    .padding(Dimens.spacingNormal)
                    .align(Alignment.TopEnd)
                    .clickable { onDeleteItem(item) }
                ) {

                    IconButton(
                        modifier = Modifier
                            .size(Dimens.iconSize)
                            .clip(RoundedCornerShape(Dimens.cornerRadiusNormalSpecial))
                            .background(Color.Cyan),
                        onClick = { onDeleteItem(item) }
                    ) {
                        GifIcons.IcDelete()
                    }
                }
            }
        }
    }
}
