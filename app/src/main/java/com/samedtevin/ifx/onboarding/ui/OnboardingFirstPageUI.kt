package com.samedtevin.ifx.onboarding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samedtevin.ifx.R
import com.samedtevin.ifx.ui.theme.IFXTheme
import com.samedtevin.ifx.ui.theme.IfxBlack
import com.samedtevin.ifx.ui.theme.IfxLime
import com.samedtevin.ifx.ui.theme.Inter

/**
 * First Onboarding Page of the ifx App:
 * Features the signature lime radial ambient glow, concentric radar pulse circles,
 * display headline with 'ifx' highlighted in green, 3 floating tilted trigger cards,
 * custom pill page indicator, and primary action.
 */
@Composable
fun OnboardingFirstPageScreen(
    onNext: () -> Unit = {},
    onSkip: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        IfxAmbientBackground()

        Image(
            painter = painterResource(id = R.drawable.ic_pulse_rings),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 42.dp, y = 145.dp)
                .size(190.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            IfxTopBar(
                showSkip = true,
                onSkip = onSkip
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(top = 12.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = "Pick a trigger,\nlet ifx watch",
                        fontFamily = Inter,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 37.sp,
                        lineHeight = 43.sp,
                        letterSpacing = (-0.5).sp,
                        color = IfxBlack
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TriggerCard(
                        rotationDegrees = -5.5f,
                        backgroundColor = IfxBlack,
                        iconRes = R.drawable.ic_card_mail,
                        iconBackgroundColor = IfxLime,
                        iconTintColor = IfxBlack,
                        tagText = "WATCHING",
                        tagColor = IfxLime,
                        titleText = "New email arrives",
                        titleColor = Color.White
                    )

                    TriggerCard(
                        rotationDegrees = 3.2f,
                        backgroundColor = IfxLime,
                        iconRes = R.drawable.ic_card_clock,
                        iconBackgroundColor = IfxBlack,
                        iconTintColor = IfxLime,
                        tagText = "SCHEDULE",
                        tagColor = IfxBlack,
                        titleText = "Every day at 8:00",
                        titleColor = IfxBlack
                    )

                    TriggerCard(
                        rotationDegrees = -1.5f,
                        backgroundColor = Color.White,
                        borderColor = IfxBlack,
                        iconRes = R.drawable.ic_card_chat,
                        iconBackgroundColor = IfxBlack,
                        iconTintColor = IfxLime,
                        tagText = "TERM 2",
                        tagColor = IfxBlack,
                        titleText = "WhatsApp\nmessage",
                        titleColor = IfxBlack,
                        titleLineHeight = 23.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                IfxPagerIndicator(
                    pageCount = 4,
                    currentPage = 0,
                    modifier = Modifier.padding(start = 2.dp)
                )

                IfxPrimaryButton(
                    text = "Next",
                    onClick = onNext
                )
            }
        }
    }
}

/**
 * Embedded version of the First Page content when hosted inside a HorizontalPager.
 * The ambient background extends full-bleed across the entire screen.
 */
@Composable
fun OnboardingFirstPageContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Full-bleed ambient background covering entire height
        IfxAmbientBackground()

        // Radar pulse rings background accent
        Image(
            painter = painterResource(id = R.drawable.ic_pulse_rings),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 42.dp, y = 110.dp)
                .size(190.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(top = 56.dp, bottom = 110.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 8.dp, bottom = 12.dp)
            ) {
                Text(
                    text = "Pick a trigger,\nlet ifx watch",
                    fontFamily = Inter,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 37.sp,
                    lineHeight = 43.sp,
                    letterSpacing = (-0.5).sp,
                    color = IfxBlack
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TriggerCard(
                    rotationDegrees = -5.5f,
                    backgroundColor = IfxBlack,
                    iconRes = R.drawable.ic_card_mail,
                    iconBackgroundColor = IfxLime,
                    iconTintColor = IfxBlack,
                    tagText = "WATCHING",
                    tagColor = IfxLime,
                    titleText = "New email arrives",
                    titleColor = Color.White
                )

                TriggerCard(
                    rotationDegrees = 3.2f,
                    backgroundColor = IfxLime,
                    iconRes = R.drawable.ic_card_clock,
                    iconBackgroundColor = IfxBlack,
                    iconTintColor = IfxLime,
                    tagText = "SCHEDULE",
                    tagColor = IfxBlack,
                    titleText = "Every day at 8:00",
                    titleColor = IfxBlack
                )

                TriggerCard(
                    rotationDegrees = -1.5f,
                    backgroundColor = Color.White,
                    borderColor = IfxBlack,
                    iconRes = R.drawable.ic_card_chat,
                    iconBackgroundColor = IfxBlack,
                    iconTintColor = IfxLime,
                    tagText = "TERM 2",
                    tagColor = IfxBlack,
                    titleText = "WhatsApp\nmessage",
                    titleColor = IfxBlack,
                    titleLineHeight = 23.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun OnboardingFirstPagePreview() {
    IFXTheme {
        OnboardingFirstPageScreen()
    }
}
