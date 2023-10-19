package com.example.giphy.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import com.example.giphy.ui.res.Dimens
import com.example.giphy.ui.res.GifColors
import com.example.giphy.ui.res.GifTypography
import com.example.giphy.ui.res.icons.GifIcons
import com.example.giphy.ui.res.icons.IcClose
import com.example.giphy.ui.res.icons.IcSearch
import com.example.giphy.ui.res.neutralDark40
import com.example.giphy.ui.res.neutralLight40
import com.example.giphy.ui.res.primaryOriginal
import com.example.giphy.ui.res.redCustom
import com.example.giphy.ui.res.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    modifier: Modifier,
    placeholder: String,
    contentPadding: PaddingValues,
    searchFieldValue: String,
    setFilterText: (filterText: String) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    closeClick: (() -> Unit)? = null,
) {
    val focusState = remember { mutableStateOf(false) }

    BasicTextField(
        value = searchFieldValue,
        onValueChange = setFilterText,
        textStyle = GifTypography.titleMedium.merge(TextStyle(color = GifColors.neutralDark40())),
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged {
                focusState.value = it.isFocused
            },
        cursorBrush = SolidColor(GifColors.neutralDark40()),
        keyboardOptions = keyboardOptions.copy(
            capitalization = KeyboardCapitalization.Words,
        ),
        keyboardActions = keyboardActions,
        singleLine = true,
        decorationBox = { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = searchFieldValue,
                visualTransformation = VisualTransformation.None,
                innerTextField = innerTextField,
                singleLine = true,
                enabled = true,
                placeholder = {
                    Text(
                        text = placeholder,
                        modifier = Modifier.fillMaxWidth(),
                        style = GifTypography.titleMedium,
                        color = GifColors.neutralLight40(),
                    )
                },
                interactionSource = remember { MutableInteractionSource() },
                trailingIcon = {
                    if (searchFieldValue.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                setFilterText("")
                                if (closeClick != null) {
                                    closeClick()
                                }
                            },
                        ) {
                            GifIcons.IcClose(
                                modifier = Modifier.size(Dimens.iconNormal),
                                tint = GifColors.redCustom(),
                            )
                        }
                    } else {
                        GifIcons.IcSearch(
                            modifier = Modifier.size(Dimens.iconNormal),
                            tint = if (!focusState.value) GifColors.neutralLight40() else GifColors.primaryOriginal(),
                        )
                    }
                },
                shape = RoundedCornerShape(size = Dimens.cornerRadiusNormal),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = GifColors.white(),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                contentPadding = contentPadding,
            )
        },
    )
}