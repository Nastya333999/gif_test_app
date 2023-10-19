package com.example.giphy.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
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
fun EmptyView(
    title: String,
    message: String,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            modifier = Modifier
                .height(Dimens.iconChooseCityHeight)
                .fillMaxWidth()
                .padding(horizontal = Dimens.toolbarPadding),
            imageVector = Icons.Outlined.Info,
            contentDescription = stringResource(id = R.string.img_no_search_results),
        )

        Text(
            text = title,
            modifier = Modifier
                .padding(
                    top = Dimens.spacingNormal,
                    start = Dimens.spacingNormal,
                    end = Dimens.spacingNormal,
                ),
            style = GifTypography.displayMedium,
            color = GifColors.neutralDark40(),
        )

        Text(
            text = message,
            modifier = Modifier
                .padding(
                    top = Dimens.spacingNormalSpecial,
                    start = Dimens.spacingNormal,
                    end = Dimens.spacingNormal,
                ),
            style = GifTypography.titleMedium,
            color = GifColors.neutralDark40(),
        )
    }
}
