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

fun hrCropperStyle(
    backgroundColor: Color = Color.Black,
    circleColor: Color = Color.White,
    circleStrokeWidth: Dp = 2.dp,
    cornerColor: Color = Color(0xFF004AC6),
    cornerLength: Dp = 24.dp,
    cornerStrokeWidth: Dp = 2.dp,
    touchRad: Dp = 55.dp,
    overlay: Color = Color.Black.copy(alpha = 0.6f),
    autoZoom: Boolean = true,
): CropperStyle = object : CropperStyle {
    override val touchRad: Dp get() = touchRad
    override val backgroundColor: Color get() = backgroundColor
    override val overlayColor: Color get() = overlay
    override val shapes: List<CropShape> get() = listOf(CircleCropShape)
    override val aspects get() = listOf(AspectRatio(1, 1))
    override val autoZoom: Boolean get() = autoZoom
    override val handles: List<Offset> get() = AllHandles

    override fun DrawScope.drawCropRect(region: Rect) {
        val circleStrokeWidthPx = circleStrokeWidth.toPx()

        drawOval(
            color = circleColor,
            style = Stroke(circleStrokeWidthPx),
            topLeft = region.topLeft,
            size = region.size
        )

        val cornerLengthPx = cornerLength.toPx()
        val cornerStrokePx = cornerStrokeWidth.toPx()
        val cornerRegion = region.inflate(10.dp.toPx())

        val left = cornerRegion.left
        val top = cornerRegion.top
        val right = cornerRegion.right
        val bottom = cornerRegion.bottom

        drawCorner(
            horizontal = Offset(left, top + cornerLengthPx),
            corner = Offset(left, top),
            vertical = Offset(left + cornerLengthPx, top),
            color = cornerColor,
            strokeWidth = cornerStrokePx
        )
        drawCorner(
            horizontal = Offset(right - cornerLengthPx, top),
            corner = Offset(right, top),
            vertical = Offset(right, top + cornerLengthPx),
            color = cornerColor,
            strokeWidth = cornerStrokePx
        )
        drawCorner(
            horizontal = Offset(left, bottom - cornerLengthPx),
            corner = Offset(left, bottom),
            vertical = Offset(left + cornerLengthPx, bottom),
            color = cornerColor,
            strokeWidth = cornerStrokePx
        )
        drawCorner(
            horizontal = Offset(right - cornerLengthPx, bottom),
            corner = Offset(right, bottom),
            vertical = Offset(right, bottom - cornerLengthPx),
            color = cornerColor,
            strokeWidth = cornerStrokePx
        )
    }
}

fun DrawScope.drawCorner(
    horizontal: Offset,
    corner: Offset,
    vertical: Offset,
    color: Color,
    strokeWidth: Float,
) {
    drawPath(
        path = Path().apply {
            moveTo(horizontal.x, horizontal.y)
            lineTo(corner.x, corner.y)
            lineTo(vertical.x, vertical.y)
        },
        color = color,
        style = Stroke(
            width = strokeWidth
        )
    )
}
