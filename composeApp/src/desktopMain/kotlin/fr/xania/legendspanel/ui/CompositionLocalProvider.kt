package fr.xania.legendspanel.ui

import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.window.application
import fr.xania.legendspanel.App

fun main() = application {
    CompositionLocalProvider(
        LocalTextStyle provides TextStyle(color = Color.White),
        LocalContentColor provides Color.White,
        LocalContentAlpha provides ContentAlpha.high,
    ) {
        App()
    }
}
