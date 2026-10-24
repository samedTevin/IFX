package com.samedtevin.ifx.onboarding.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samedtevin.ifx.onboarding.OnboardingModel
import com.samedtevin.ifx.ui.theme.IFXTheme
import com.samedtevin.ifx.ui.theme.IfxBlack
import com.samedtevin.ifx.ui.theme.IfxLime
import kotlinx.coroutines.launch

/**
 * Main Onboarding Screen hosting the 4-page HorizontalPager.
 * Backgrounds are 100% full-bleed from top to bottom, seamlessly extending
 * underneath the top bar and the bottom indicator + button with no cut-off bars.
 */
@Composable
fun OnboardingScreen(onFinished: () -> Unit) {
    val pages = listOf(
        OnboardingModel.FirstPage,
        OnboardingModel.SecondPage,
        OnboardingModel.ThirdPage,
        OnboardingModel.FourthPage
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages.size }
    )

    val scope = rememberCoroutineScope()

    val isDarkPage = pagerState.currentPage == 3

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 1. HorizontalPager fills the FULL screen height (0 to 100%)
        // Each page renders its own complete full-bleed background underneath everything.
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { index ->
            OnboardingGraphUI(
                onboardingModel = pages[index]
            )
        }

        // 2. Top Navigation Overlay (Floating transparently over the full-bleed page background)
        IfxTopBar(
            isDark = isDarkPage,
            showSkip = true,
            onSkip = onFinished,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding()
        )

        // 3. Bottom Controls Overlay (Floating transparently over the full-bleed page background)
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp)
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // Animated 4-dot indicator
            IfxPagerIndicator(
                pageCount = pages.size,
                currentPage = pagerState.currentPage,
                isDark = isDarkPage,
                modifier = Modifier.padding(start = 2.dp)
            )

            // Button: "Next" on pages 1-3, "Get Started" on the end page
            val buttonText = if (pagerState.currentPage == pages.size - 1) "Get Started" else "Next"
            val buttonBg = if (isDarkPage) IfxLime else IfxBlack
            val buttonTextColor = if (isDarkPage) IfxBlack else IfxLime

            IfxPrimaryButton(
                text = buttonText,
                backgroundColor = buttonBg,
                textColor = buttonTextColor,
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage < pages.size - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            onFinished()
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun OnboardingScreenPreview() {
    IFXTheme {
        OnboardingScreen {}
    }
}
