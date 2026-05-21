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

fun Modifier.dashedBorder(
    color: Color,
    shape: Shape,
    strokeWidth: Dp,
    dashLength: Dp,
    gapLength: Dp,
    cap: StrokeCap = StrokeCap.Butt,
) = this.drawWithContent {
    val outline = shape.createOutline(size, layoutDirection, this)
    val dashedStroke = Stroke(
        cap = cap,
        width = strokeWidth.toPx(),
        pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashLength.toPx(), gapLength.toPx())
        )
    )
    drawContent()
    drawOutline(
        outline = outline,
        style = dashedStroke,
        color = color
    )
}