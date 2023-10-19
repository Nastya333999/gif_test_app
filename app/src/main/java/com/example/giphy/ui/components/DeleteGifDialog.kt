package com.example.giphy.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.giphy.R
import com.example.giphy.ui.res.Dimens

@Composable
fun DeleteGifDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(
        title = stringResource(id = R.string.delete_dialog_title),
        description = stringResource(id = R.string.delete_dialog_description),
        onDismiss = onDismiss,
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.spacingBig),
                horizontalArrangement = Arrangement.End
            ) {

                TextButton(
                    onClick = onDismiss,
                    content = {
                        Text(text = stringResource(id = R.string.cancel_btn_text))
                    }
                )

                Spacer(modifier = Modifier.width(Dimens.spacingBig))

                TextButton(
                    onClick = onConfirm,
                    content = {
                        Text(text = stringResource(id = R.string.delete_btn_text))
                    }
                )
            }
        }
    )
}

@Composable
fun Dialog(
    title: String,
    description: String,
    actions: (@Composable ColumnScope.() -> Unit)? = null,
    onDismiss: () -> Unit = {}
) {
    androidx.compose.ui.window.Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
        ) {

            Column(
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = Dimens.spacingBig,
                            end = Dimens.spacingBig,
                            top = Dimens.spacingBig
                        ),
                    text = title,
                )

                if (description.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(Dimens.spacingNormal))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Dimens.spacingBig),
                        text = description,
                    )
                }

                actions?.let { actions -> actions() }
            }
        }
    }
}