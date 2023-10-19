package com.example.giphy.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.giphy.ui.res.Dimens
import com.example.giphy.ui.res.GifColors
import com.example.giphy.ui.res.GifTypography
import com.example.giphy.ui.res.neutralLight40
import com.example.giphy.ui.res.primaryOriginal
import com.example.giphy.ui.res.secondaryDark70
import com.example.giphy.ui.res.white

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = GifColors.primaryOriginal(),
    textColor: Color = GifColors.white(),
    text: String,
    textStyle: TextStyle = GifTypography.titleLarge,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.height(Dimens.buttonHeight),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
            disabledContainerColor = GifColors.secondaryDark70().copy(alpha = 0.4f),
            disabledContentColor = GifColors.neutralLight40(),
        ),
        contentPadding = contentPadding,
        enabled = enabled,
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = textStyle,
        )
    }
}
