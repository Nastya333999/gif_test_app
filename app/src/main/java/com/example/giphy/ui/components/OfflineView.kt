package com.example.giphy.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.giphy.R
import com.example.giphy.ui.res.Dimens
import com.example.giphy.ui.res.GifColors
import com.example.giphy.ui.res.neutralDark40
import com.example.giphy.ui.res.GifTypography
import kotlinx.coroutines.delay

@Composable
fun OfflineView(
    modifier: Modifier = Modifier,
    subTitleText: String? = null
) {
    var isTryingToConnect by remember { mutableStateOf(false) }
    val text = stringResource(
        id = if (isTryingToConnect) R.string.trying_to_connect else R.string.reload
    )
    LaunchedEffect(isTryingToConnect) {
        if (isTryingToConnect) {
            delay(DELAY_OFFLINE_RECONNECT)
            isTryingToConnect = false
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            imageVector = Icons.Outlined.Warning,
            contentDescription = stringResource(id = R.string.no_internet)
        )

        Spacer(Modifier.height(Dimens.spacingNormalSpecial))

        Text(
            text = stringResource(id = R.string.you_are_offline_text),
            style = GifTypography.titleMedium,
            color = GifColors.neutralDark40()
        )

        subTitleText?.let {
            Spacer(Modifier.height(Dimens.spacingNormalSpecial))

            Text(
                text = subTitleText,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = GifTypography.titleMedium,
                color = GifColors.neutralDark40()
            )
        }

        Spacer(Modifier.height(Dimens.spacingBig))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            onClick = { isTryingToConnect = true }
        )
    }
}

private const val DELAY_OFFLINE_RECONNECT = 1000L
