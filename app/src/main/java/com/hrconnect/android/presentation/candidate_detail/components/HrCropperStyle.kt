package com.hrconnect.android.presentation.candidate_detail.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.attafitamim.krop.core.crop.AllHandles
import com.attafitamim.krop.core.crop.AspectRatio
import com.attafitamim.krop.core.crop.CircleCropShape
import com.attafitamim.krop.core.crop.CropShape
import com.attafitamim.krop.core.crop.CropperStyle
import com.attafitamim.krop.core.crop.MainHandles

fun hrCropperStyle(
    backgroundColor: Color = Color.Black,
    circleColor: Color = Color.White,
    circleStrokeWidth: Dp = 2.dp,
    cornerColor: Color = Color(0xFF004AC6),
    cornerStrokeWidth: Dp = 2.dp,
    cornerLength: Dp = 24.dp,
    touchRad: Dp = 55.dp,
    secondaryHandles: Boolean = true,
    overlay: Color = Color.Black,
    shapes: List<CropShape> = listOf(CircleCropShape),
    aspects: List<AspectRatio> = listOf(AspectRatio(1, 1)),
    autoZoom: Boolean = true,
): CropperStyle = object : CropperStyle {
    override val touchRad: Dp get() = touchRad
    override val backgroundColor: Color get() = backgroundColor
    override val overlayColor: Color get() = overlay
    override val shapes: List<CropShape> get() = shapes
    override val aspects get() = aspects
    override val autoZoom: Boolean get() = autoZoom

    override fun DrawScope.drawCropRect(region: Rect) {
        drawOval(
            color = circleColor,
            topLeft = region.topLeft,
            size = region.size,
            style = Stroke(
                width = circleStrokeWidth.toPx()
            )
        )

        val circleRegion = region.inflate(10.dp.toPx())
        val cornerLengthPx = cornerLength.toPx()

        listOf(
            circleRegion.topLeft to Offset(1f, 1f),
            circleRegion.topRight to Offset(-1f, 1f),
            circleRegion.bottomLeft to Offset(1f, -1f),
            circleRegion.bottomRight to Offset(-1f, -1f)
        ).forEach { (corner, dir) ->
            drawPath(
                path = Path().apply {
                    moveTo(corner.x + dir.x * cornerLengthPx, corner.y)
                    lineTo(corner.x, corner.y)
                    lineTo(corner.x, corner.y + dir.y * cornerLengthPx)
                },
                color = cornerColor,
                style = Stroke(
                    width = cornerStrokeWidth.toPx()
                )
            )
        }
    }

    override val handles: List<Offset> = if (!secondaryHandles) MainHandles else AllHandles
}