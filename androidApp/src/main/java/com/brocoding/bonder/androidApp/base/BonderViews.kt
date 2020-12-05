package com.brocoding.bonder.androidApp.base

import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.asFontFamily
import androidx.compose.ui.text.font.font
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brocoding.bonder.androidApp.R

@Composable
fun BonderText(
    text: String,
    size: TextUnit = 12.sp,
    @ColorRes textColorRes: Int = R.color.color_84898C,
    textColor: Color? = null
) {
    val resultTextColor: Color = textColor ?: Color(ContextAmbient.current.getColor(textColorRes))
    Text(
        text = text,
        color = resultTextColor,
        fontSize = size,
        fontFamily = font(R.font.igra_sans).asFontFamily(),
        maxLines = 1
    )
}

@Composable
fun BonderError() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BonderText(text = "Упс!", size = 28.sp)
        Spacer(modifier = Modifier.preferredSize(10.dp))
        BonderText(text = "Кажется, ошибочка вышла...", size = 16.sp)
        Spacer(modifier = Modifier.preferredSize(8.dp))
        Image(vectorResource(id = R.drawable.ic_error))
    }
}
