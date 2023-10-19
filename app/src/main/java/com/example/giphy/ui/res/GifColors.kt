package com.example.giphy.ui.res

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.giphy.R

object GifColors

@Composable
@ReadOnlyComposable
fun GifColors.background(): Color = colorResource(id = R.color.background)

@Composable
@ReadOnlyComposable
fun GifColors.secondaryDark70(): Color = colorResource(id = R.color.secondary_dark70)

@Composable
@ReadOnlyComposable
fun GifColors.neutralDark40(): Color = colorResource(id = R.color.neutral_dark40)

@Composable
@ReadOnlyComposable
fun GifColors.neutralLight40(): Color = colorResource(id = R.color.neutral_light40)

@Composable
@ReadOnlyComposable
fun GifColors.white(): Color = colorResource(id = R.color.white)

@Composable
@ReadOnlyComposable
fun GifColors.primaryOriginal(): Color = colorResource(id = R.color.primary_original)

@Composable
@ReadOnlyComposable
fun GifColors.redCustom(): Color = colorResource(id = R.color.red)
