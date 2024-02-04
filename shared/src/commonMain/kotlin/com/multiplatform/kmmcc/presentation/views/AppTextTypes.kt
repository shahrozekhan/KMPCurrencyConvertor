package com.multiplatform.kmmcc.presentation.views

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun HeadingMedium(
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    isSingleLine: Boolean = false
) {
    return Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.headlineLarge,
        color = color,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun Body1Medium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    isSingleLine: Boolean = false,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    return Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
        color = color,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign
    )
}

@Composable
fun Body1Normal(
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    isSingleLine: Boolean = false
) {
    return Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Normal,
        color = color,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun Body2Medium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    isSingleLine: Boolean = false,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    return Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        color = color,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign
    )
}

@Composable
fun TitleLargeMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    isSingleLine: Boolean = false,
    textAlign: TextAlign = TextAlign.Unspecified,
    textDecoration: TextDecoration=TextDecoration.None
) {
    return Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = color,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign,
        textDecoration = textDecoration
    )
}

@Composable
fun Body2Normal(
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    isSingleLine: Boolean = false
) {
    return Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Normal,
        color = color,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE,
        overflow = TextOverflow.Ellipsis
    )
}