package fr.xania.legendspanel.ui.animation

import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import kotlinx.coroutines.delay
import androidx.compose.material.Text

@Composable
fun TypewriterText(
    text: String,
    speed: Long = 50,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current
) {
    var visibleText by remember { mutableStateOf("") }
    var index by remember { mutableStateOf(0) }

    LaunchedEffect(text) {
        visibleText = ""
        index = 0
        while (index <= text.length) {
            visibleText = text.take(index)
            index++
            delay(speed)
        }
    }

    Text(
        text = visibleText,
        modifier = modifier,
        style = style,
        overflow = TextOverflow.Clip
    )
}

