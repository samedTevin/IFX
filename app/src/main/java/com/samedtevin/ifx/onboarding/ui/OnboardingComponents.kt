package com.samedtevin.ifx.onboarding.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samedtevin.ifx.R
import com.samedtevin.ifx.ui.theme.IfxBlack
import com.samedtevin.ifx.ui.theme.IfxLime
import com.samedtevin.ifx.ui.theme.IfxMuted
import com.samedtevin.ifx.ui.theme.Inter

/**
 * Native Jetpack Compose ambient glow background for Page 1.
 * Provides a seamless full-bleed gradient from top to bottom with no dark cut-offs.
 */
@Composable
fun IfxAmbientBackground(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        // 1. Clean soft lime-to-white vertical gradient matching design mockup
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFE8FDCE),
                    Color(0xFFF3FEE4),
                    Color(0xFFFAFEF5),
                    Color(0xFFF4FBEB)
                )
            )
        )

        // 2. Vibrant lime glow in upper-left
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0x55C6FF3F), Color(0x00C6FF3F)),
                center = Offset(w * 0.15f, h * 0.28f),
                radius = w * 0.85f
            ),
            center = Offset(w * 0.15f, h * 0.28f),
            radius = w * 0.85f
        )

        // 3. Vibrant lime glow around radar pulse rings at top-right
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0x38C6FF3F), Color(0x00C6FF3F)),
                center = Offset(w * 0.85f, h * 0.45f),
                radius = w * 0.75f
            ),
            center = Offset(w * 0.85f, h * 0.45f),
            radius = w * 0.75f
        )
    }
}

/**
 * Official Google Gemini 4-pointed rainbow sparkle icon.
 * Drawn with quadratic curves and a multi-color gradient (Blue, Purple, Red, Yellow).
 */
@Composable
fun GeminiSparkleIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val cx = w / 2f
        val cy = h / 2f

        val path = Path().apply {
            moveTo(cx, 0f)
            quadraticTo(cx, cy, w, cy)
            quadraticTo(cx, cy, cx, h)
            quadraticTo(cx, cy, 0f, cy)
            quadraticTo(cx, cy, cx, 0f)
            close()
        }

        val rainbowBrush = Brush.sweepGradient(
            colors = listOf(
                Color(0xFF3186FF), // Google Blue
                Color(0xFF9B72CB), // Gemini Purple
                Color(0xFFEA4335), // Red
                Color(0xFFFBBC04), // Yellow
                Color(0xFF34A853), // Green
                Color(0xFF3186FF)  // Blue closure
            ),
            center = Offset(cx, cy)
        )

        drawPath(path = path, brush = rainbowBrush)
    }
}

/**
 * Top branding bar: ifx Squircle icon on the start side, Skip action on the end side.
 */
@Composable
fun IfxTopBar(
    modifier: Modifier = Modifier,
    isDark: Boolean = false,
    showSkip: Boolean = true,
    onSkip: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_ifx_squircle),
            contentDescription = "ifx App Icon",
            modifier = Modifier.size(42.dp)
        )

        if (showSkip) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(
                        role = Role.Button,
                        onClick = onSkip
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .semantics { contentDescription = "Skip onboarding" },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Skip",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    color = if (isDark) Color(0xFFA0A0A0) else IfxMuted
                )
            }
        }
    }
}

/**
 * Reusable tilted trigger card for the IFX onboarding showcase.
 */
@Composable
fun TriggerCard(
    rotationDegrees: Float,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    borderColor: Color? = null,
    iconRes: Int,
    iconBackgroundColor: Color,
    iconTintColor: Color,
    tagText: String,
    tagColor: Color,
    titleText: String,
    titleColor: Color,
    titleLineHeight: TextUnit = 24.sp,
    elevation: Dp = 10.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                rotationZ = rotationDegrees
            }
            .shadow(
                elevation = elevation,
                shape = RoundedCornerShape(26.dp),
                spotColor = Color(0x33000000),
                ambientColor = Color(0x1A000000)
            )
            .background(color = backgroundColor, shape = RoundedCornerShape(26.dp))
            .then(
                if (borderColor != null) {
                    Modifier.border(
                        width = 1.8.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(26.dp)
                    )
                } else Modifier
            )
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .background(
                        color = iconBackgroundColor,
                        shape = RoundedCornerShape(18.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = iconTintColor,
                    modifier = Modifier.size(26.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = tagText,
                    fontFamily = Inter,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 11.5.sp,
                    letterSpacing = 1.sp,
                    color = tagColor
                )

                Text(
                    text = titleText,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp,
                    lineHeight = titleLineHeight,
                    color = titleColor
                )
            }
        }
    }
}

/**
 * Custom ifx Pagination Indicator:
 * Active page is an elongated pill (30dp width x 8dp height, lime green with black border).
 * Inactive pages are small circular dots (black in light mode, white in dark mode).
 */
@Composable
fun IfxPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    isDark: Boolean = false
) {
    val inactiveColor = if (isDark) Color.White else IfxBlack

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until pageCount) {
            val isSelected = i == currentPage

            val dotWidth by animateDpAsState(
                targetValue = if (isSelected) 30.dp else 8.dp,
                animationSpec = spring(dampingRatio = 0.8f, stiffness = 400f),
                label = "indicatorWidth"
            )

            val dotColor by animateColorAsState(
                targetValue = if (isSelected) IfxLime else inactiveColor,
                label = "indicatorColor"
            )

            Box(
                modifier = Modifier
                    .height(8.dp)
                    .width(dotWidth)
                    .background(
                        color = dotColor,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .then(
                        if (isSelected) {
                            Modifier.border(
                                width = 1.2.dp,
                                color = if (isDark) Color.Transparent else IfxBlack,
                                shape = RoundedCornerShape(4.dp)
                            )
                        } else Modifier
                    )
            )
        }
    }
}

/**
 * Primary ifx Call To Action Button:
 * Meets WCAG 2.2 touch target requirements (58dp height >= 48dp).
 */
@Composable
fun IfxPrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = IfxBlack,
    textColor: Color = IfxLime,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        shape = RoundedCornerShape(percent = 50),
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp)
    ) {
        Text(
            text = text,
            fontFamily = Inter,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = textColor
        )
    }
}

/**
 * Modifier helper to draw a dashed border around rounded rectangles.
 */
fun Modifier.dashedBorder(
    color: Color,
    strokeWidth: Dp = 1.5.dp,
    dashLength: Dp = 6.dp,
    gapLength: Dp = 4.dp,
    cornerRadius: Dp = 20.dp
): Modifier = this.drawBehind {
    val strokePx = strokeWidth.toPx()
    val dashPx = dashLength.toPx()
    val gapPx = gapLength.toPx()
    val radiusPx = cornerRadius.toPx()

    drawRoundRect(
        color = color,
        topLeft = Offset(strokePx / 2, strokePx / 2),
        size = androidx.compose.ui.geometry.Size(
            size.width - strokePx,
            size.height - strokePx
        ),
        cornerRadius = CornerRadius(radiusPx, radiusPx),
        style = Stroke(
            width = strokePx,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashPx, gapPx), 0f)
        )
    )
}
