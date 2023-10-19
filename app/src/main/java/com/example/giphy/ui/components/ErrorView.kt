package com.example.giphy.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.giphy.R
import com.example.giphy.ui.res.Dimens
import com.example.giphy.ui.res.GifColors
import com.example.giphy.ui.res.neutralDark40
import com.example.giphy.ui.res.GifTypography

@Composable
fun ErrorView(
    onReload: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            imageVector = Icons.Outlined.Face,
            contentDescription = stringResource(id = R.string.error_view)
        )

        Spacer(Modifier.height(Dimens.spacingNormalSpecial))

        Text(
            text = stringResource(id = R.string.something_went_wrong),
            style = GifTypography.titleMedium,
            color = GifColors.neutralDark40()
        )

        Spacer(Modifier.height(Dimens.spacingNormalSpecial))

        Text(
            text = stringResource(id = R.string.please_try_again),
            style = GifTypography.titleMedium,
            color = GifColors.neutralDark40()
        )

        Spacer(Modifier.height(Dimens.spacingBig))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.reload),
            onClick = onReload
        )
    }
}