package com.techullurgy.oaccountingapp.presentation.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalTextApi::class)
@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp,
    fontColor: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Medium,
    textAlign: TextAlign = TextAlign.Start,
    useTextMeasure: Boolean = false,
    measureText: (() -> String)? = null,
    style: TextStyle = LocalTextStyle.current.copy(
        fontSize = fontSize,
        color = fontColor,
        fontWeight = fontWeight,
        textAlign = textAlign
    )
) {

    var textWidth by remember { mutableStateOf(0.dp) }

    if(useTextMeasure) {
        requireNotNull(measureText) {
            "measureText() should not be null if useTextMeasure is true.."
        }

        val textMeasurer = rememberTextMeasurer()
        textWidth = with(LocalDensity.current) {
            textMeasurer.measure(measureText(), style = style, overflow = TextOverflow.Ellipsis, maxLines = 2)
                    .size.width.toDp()
        }
    }

    val textModifier: Modifier = if(useTextMeasure) modifier.width(textWidth) else modifier

    Text(
        modifier = textModifier,
        text = text,
        style = style,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}