package com.example.giphy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.giphy.ui.res.Dimens
import com.example.giphy.ui.res.GifColors
import com.example.giphy.ui.res.background
import com.example.giphy.ui.res.neutralDark40
import com.example.giphy.ui.res.GifTypography

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedAppBarShape(),
    backgroundColor: Color = GifColors.background(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    leftAction: @Composable () -> Unit = {},
    title: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.appBarHeight)
            .clip(shape)
            .background(backgroundColor)
            .padding(contentPadding),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = title,
            style = GifTypography.headlineMedium,
            color = GifColors.neutralDark40(),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            leftAction.invoke()

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

private fun RoundedAppBarShape() = RoundedCornerShape(
    topStart = 0.dp,
    topEnd = 0.dp,
    bottomEnd = Dimens.cornerRadiusLarge,
    bottomStart = Dimens.cornerRadiusLarge,
)
