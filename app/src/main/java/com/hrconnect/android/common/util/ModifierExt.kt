package com.hrconnect.android.common.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.dashedBorder(
    color: Color,
    shape: Shape,
    strokeWidth: Dp = 2.dp,
    dashLength: Dp = 6.dp,
    gapLength: Dp = 4.dp,
    strokeCap: StrokeCap = StrokeCap.Butt,
) = drawWithContent {
    val outline = shape.createOutline(size, layoutDirection, this)
    val dashedStroke = Stroke(
        width = strokeWidth.toPx(),
        cap = strokeCap,
        pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashLength.toPx(), gapLength.toPx())
        )
    )

    drawContent()
    drawOutline(
        outline = outline,
        color = color,
        style = dashedStroke
    )
}