package fr.xania.legendspanel.ui

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val JetBrainsMono = FontFamily(
    Font("fonts/JetBrainsMono-Regular.ttf", FontWeight.Normal),
    Font("fonts/JetBrainsMono-Bold.ttf", FontWeight.Bold),
    Font("fonts/JetBrainsMono-Italic.ttf", FontWeight.Normal, FontStyle.Italic),
    Font("fonts/JetBrainsMono-BoldItalic.ttf", FontWeight.Bold, FontStyle.Italic)
)

val LegendsTypography = Typography(
    h1 = TextStyle(
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    body1 = TextStyle(
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)