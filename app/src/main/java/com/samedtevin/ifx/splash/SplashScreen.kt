package com.samedtevin.ifx.splash

import android.content.res.Configuration
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Vector path data and content bounding dimensions for the "ifx" wordmark.
 * Defined in a 1024x1024 coordinate viewport.
 */
object IfxLogoPaths {
    // "i" letter stem
    const val PATH_I = "M329,447 H402 V617 H352 V483 H329 Z"

    // Rounded, slightly squarish lime dot above "i"
    const val PATH_DOT = "M360,369 H377 Q395,369 395,387 V403 Q395,421 377,421 H360 Q342,421 342,403 V387 Q342,369 360,369 Z"

    // "f" letter
    const val PATH_F = "M416,447 H436 V440 Q436,387 490,387 H523 V430 H500 Q484,430 484,447 H527 V487 H484 V617 H436 V487 H416 Z"

    // "x" letter
    const val PATH_X = "M524,446 H579 L607,497 L643,446 H690 L647,529 L700,617 H647 L607,556 L572,617 H520 L570,531 Z"

    // Content bounds within the 1024x1024 vector space
    const val CONTENT_MIN_X = 329f
    const val CONTENT_MAX_X = 700f
    const val CONTENT_MIN_Y = 369f
    const val CONTENT_MAX_Y = 617f

    // Net dimensions of the wordmark (width: 371, height: 248)
    const val CONTENT_WIDTH = CONTENT_MAX_X - CONTENT_MIN_X
    const val CONTENT_HEIGHT = CONTENT_MAX_Y - CONTENT_MIN_Y
}

/**
 * Animation specifications, timings, and initial offsets.
 * All parameters are centralized here for easy tuning.
 */
object SplashAnimationConfig {
    // Expo-style ease-out curve (smooth deceleration, zero abruptness)
    val EaseOutExpo = CubicBezierEasing(0.16f, 1.0f, 0.3f, 1.0f)

    // Initial offsets (in vector coordinate units)
    const val LETTER_INITIAL_OFFSET_Y = 60f      // "i" and "f" rise from slightly below
    const val LETTER_X_INITIAL_OFFSET_X = -85f   // "x" slides out from behind "f" (left to right)
    const val DOT_INITIAL_OFFSET_Y = -140f       // Lime dot drops from above onto "i"

    // Timings and durations (in milliseconds)
    const val LETTER_I_RISE_DURATION = 550
    const val LETTER_I_FADE_DURATION = 400

    const val LETTER_F_START_DELAY = 100L        // Starts ~100 ms after "i"
    const val LETTER_F_RISE_DURATION = 550
    const val LETTER_F_FADE_DURATION = 400

    const val LETTER_X_START_DELAY = 250L        // Starts ~250 ms after "i"
    const val LETTER_X_SLIDE_DURATION = 550
    const val LETTER_X_FADE_DURATION = 500

    const val DOT_DROP_START_DELAY = 380L        // Starts dropping as "i" and "f" settle
    const val DOT_FADE_DURATION = 150

    // Spring specification for the lime dot: medium bounciness, low stiffness
    val DotSpringSpec = spring<Float>(
        dampingRatio = Spring.DampingRatioMediumBouncy, // 0.5f -> overshoots slightly, then settles
        stiffness = Spring.StiffnessLow                 // 200f -> soft, organic gravity drop
    )

    // Hold time for the completed logo before transition
    const val LOGO_HOLD_DURATION = 450L          // 400 - 500 ms hold

    // Whole screen exit fade-out duration
    const val SCREEN_FADE_OUT_DURATION = 250
}

/**
 * Animated Splash Screen for IFX with #FFFFFF background.
 *
 * Sequence:
 * 1. "i" and "f" rise from below with smooth expo ease-out while fading in ("f" offset by 100ms).
 * 2. "x" slides out from behind "f" while fading in (offset by 250ms).
 * 3. Lime dot drops onto "i" with soft spring bounce (overshoots and settles).
 * 4. Hold finished logo for ~450ms.
 * 5. Screen fades out in ~250ms and invokes [onFinished].
 *
 * @param onFinished Invoked when the entire splash sequence and fade-out is complete.
 * @param modifier Optional modifier applied to the root container.
 * @param backgroundColor Background color, defaults to #FFFFFF.
 * @param letterColor Solid color for "i" and "f", defaults to #0A0A0A.
 * @param accentLimeColor Accent lime green (#C6FF3D) for "x" and the dot above "i".
 */
@Composable
fun SplashScreen(
    onFinished: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFFFFFFF),
    letterColor: Color = Color(0xFF0A0A0A),
    accentLimeColor: Color = Color(0xFFC6FF3D)
) {
    // Pre-parse vector paths once into Compose Path instances
    val pathI = remember { PathParser().parsePathString(IfxLogoPaths.PATH_I).toPath() }
    val pathDot = remember { PathParser().parsePathString(IfxLogoPaths.PATH_DOT).toPath() }
    val pathF = remember { PathParser().parsePathString(IfxLogoPaths.PATH_F).toPath() }
    val pathX = remember { PathParser().parsePathString(IfxLogoPaths.PATH_X).toPath() }

    // Shape animation states
    val letterIOffsetY = remember { Animatable(SplashAnimationConfig.LETTER_INITIAL_OFFSET_Y) }
    val letterIAlpha = remember { Animatable(0f) }

    val letterFOffsetY = remember { Animatable(SplashAnimationConfig.LETTER_INITIAL_OFFSET_Y) }
    val letterFAlpha = remember { Animatable(0f) }

    val letterXOffsetX = remember { Animatable(SplashAnimationConfig.LETTER_X_INITIAL_OFFSET_X) }
    val letterXAlpha = remember { Animatable(0f) }

    val dotOffsetY = remember { Animatable(SplashAnimationConfig.DOT_INITIAL_OFFSET_Y) }
    val dotAlpha = remember { Animatable(0f) }

    // Screen exit fade-out alpha
    val screenAlpha = remember { Animatable(1f) }

    // Orchestrate the animation timeline
    LaunchedEffect(Unit) {
        // 1. "i" rises and fades in immediately
        launch {
            letterIOffsetY.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = SplashAnimationConfig.LETTER_I_RISE_DURATION,
                    easing = SplashAnimationConfig.EaseOutExpo
                )
            )
        }
        launch {
            letterIAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = SplashAnimationConfig.LETTER_I_FADE_DURATION,
                    easing = SplashAnimationConfig.EaseOutExpo
                )
            )
        }

        // 1b. "f" rises and fades in ~100 ms after "i"
        launch {
            delay(SplashAnimationConfig.LETTER_F_START_DELAY)
            launch {
                letterFOffsetY.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(
                        durationMillis = SplashAnimationConfig.LETTER_F_RISE_DURATION,
                        easing = SplashAnimationConfig.EaseOutExpo
                    )
                )
            }
            launch {
                letterFAlpha.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = SplashAnimationConfig.LETTER_F_FADE_DURATION,
                        easing = SplashAnimationConfig.EaseOutExpo
                    )
                )
            }
        }

        // 2. "x" slides out from behind "f" ~250 ms after "i"
        launch {
            delay(SplashAnimationConfig.LETTER_X_START_DELAY)
            launch {
                letterXOffsetX.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(
                        durationMillis = SplashAnimationConfig.LETTER_X_SLIDE_DURATION,
                        easing = SplashAnimationConfig.EaseOutExpo
                    )
                )
            }
            launch {
                letterXAlpha.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = SplashAnimationConfig.LETTER_X_FADE_DURATION,
                        easing = SplashAnimationConfig.EaseOutExpo
                    )
                )
            }
        }

        // 3. Dot drops from above onto "i" with soft spring bounce
        val dotJob = launch {
            delay(SplashAnimationConfig.DOT_DROP_START_DELAY)
            launch {
                dotAlpha.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = SplashAnimationConfig.DOT_FADE_DURATION)
                )
            }
            dotOffsetY.animateTo(
                targetValue = 0f,
                animationSpec = SplashAnimationConfig.DotSpringSpec
            )
        }

        // Wait for the spring to naturally come to rest
        dotJob.join()

        // 4. Hold the finished logo for 400 - 500 ms
        delay(SplashAnimationConfig.LOGO_HOLD_DURATION)

        // 5. Fade out the whole screen in 250 ms
        screenAlpha.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = SplashAnimationConfig.SCREEN_FADE_OUT_DURATION,
                easing = LinearEasing
            )
        )

        // Animation sequence complete -> invoke callback to switch to main app content
        onFinished()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer { alpha = screenAlpha.value }
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(IfxLogoPaths.CONTENT_WIDTH / IfxLogoPaths.CONTENT_HEIGHT)
        ) {
            // Compute scale factor to fit exactly within Canvas bounds while preserving aspect ratio
            val scale = minOf(
                size.width / IfxLogoPaths.CONTENT_WIDTH,
                size.height / IfxLogoPaths.CONTENT_HEIGHT
            )

            // Calculate base translation so the logo content bounds are centered within the Canvas
            val baseTranslateX = (size.width - IfxLogoPaths.CONTENT_WIDTH * scale) / 2f - IfxLogoPaths.CONTENT_MIN_X * scale
            val baseTranslateY = (size.height - IfxLogoPaths.CONTENT_HEIGHT * scale) / 2f - IfxLogoPaths.CONTENT_MIN_Y * scale

            // 1. "x" letter (slides out from behind "f" from left to right)
            // Drawn BEFORE "f" so that "f" occludes "x" until it emerges
            withTransform({
                translate(
                    left = baseTranslateX + (letterXOffsetX.value * scale),
                    top = baseTranslateY
                )
                scale(scaleX = scale, scaleY = scale, pivot = Offset.Zero)
            }) {
                drawPath(
                    path = pathX,
                    color = accentLimeColor,
                    alpha = letterXAlpha.value
                )
            }

            // 2. "i" letter stem (rises from below)
            withTransform({
                translate(
                    left = baseTranslateX,
                    top = baseTranslateY + (letterIOffsetY.value * scale)
                )
                scale(scaleX = scale, scaleY = scale, pivot = Offset.Zero)
            }) {
                drawPath(
                    path = pathI,
                    color = letterColor,
                    alpha = letterIAlpha.value
                )
            }

            // 3. "f" letter (rises from below, covers "x" until "x" slides out)
            withTransform({
                translate(
                    left = baseTranslateX,
                    top = baseTranslateY + (letterFOffsetY.value * scale)
                )
                scale(scaleX = scale, scaleY = scale, pivot = Offset.Zero)
            }) {
                drawPath(
                    path = pathF,
                    color = letterColor,
                    alpha = letterFAlpha.value
                )
            }

            // 4. Lime dot (drops from above onto "i" with soft spring bounce)
            withTransform({
                translate(
                    left = baseTranslateX,
                    top = baseTranslateY + (dotOffsetY.value * scale)
                )
                scale(scaleX = scale, scaleY = scale, pivot = Offset.Zero)
            }) {
                drawPath(
                    path = pathDot,
                    color = accentLimeColor,
                    alpha = dotAlpha.value
                )
            }
        }
    }
}

/**
 * Reusable static IFX logo composable on #FFFFFF background.
 */
@Composable
fun IfxLogo(
    modifier: Modifier = Modifier,
    letterColor: Color = Color(0xFF0A0A0A),
    accentColor: Color = Color(0xFFC6FF3D)
) {
    val pathI = remember { PathParser().parsePathString(IfxLogoPaths.PATH_I).toPath() }
    val pathDot = remember { PathParser().parsePathString(IfxLogoPaths.PATH_DOT).toPath() }
    val pathF = remember { PathParser().parsePathString(IfxLogoPaths.PATH_F).toPath() }
    val pathX = remember { PathParser().parsePathString(IfxLogoPaths.PATH_X).toPath() }

    Canvas(
        modifier = modifier.aspectRatio(IfxLogoPaths.CONTENT_WIDTH / IfxLogoPaths.CONTENT_HEIGHT)
    ) {
        val scale = minOf(
            size.width / IfxLogoPaths.CONTENT_WIDTH,
            size.height / IfxLogoPaths.CONTENT_HEIGHT
        )
        val baseTranslateX = (size.width - IfxLogoPaths.CONTENT_WIDTH * scale) / 2f - IfxLogoPaths.CONTENT_MIN_X * scale
        val baseTranslateY = (size.height - IfxLogoPaths.CONTENT_HEIGHT * scale) / 2f - IfxLogoPaths.CONTENT_MIN_Y * scale

        withTransform({
            translate(left = baseTranslateX, top = baseTranslateY)
            scale(scaleX = scale, scaleY = scale, pivot = Offset.Zero)
        }) {
            drawPath(path = pathX, color = accentColor)
            drawPath(path = pathI, color = letterColor)
            drawPath(path = pathF, color = letterColor)
            drawPath(path = pathDot, color = accentColor)
        }
    }
}

@Preview(name = "Splash - White Background (#FFFFFF)", showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun SplashScreenWhitePreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        IfxLogo(
            modifier = Modifier.fillMaxWidth(0.5f),
            letterColor = Color(0xFF0A0A0A),
            accentColor = Color(0xFFC6FF3D)
        )
    }
}
