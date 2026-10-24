package com.samedtevin.ifx.onboarding.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import com.samedtevin.ifx.ui.theme.IfxMuted
import com.samedtevin.ifx.ui.theme.Inter

/**
 * Third Onboarding Page: 'Describe what you want, let Gemini help'
 * Features vibrant electric lime background, AI prompt composer card, cascade steps with connecting lines,
 * and highlighted 'Gemini' in white against the green background.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OnboardingThirdPageContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(IfxLime)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(top = 56.dp, bottom = 110.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 1. Gemini Routine Composer Card (White)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(12.dp, RoundedCornerShape(26.dp), spotColor = Color(0x33000000))
                        .background(Color.White, RoundedCornerShape(26.dp))
                        .padding(18.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        // Header Row: Rainbow Gemini Star + Gemini Label + Single-Line Badge
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f, fill = false)
                            ) {
                                GeminiSparkleIcon(modifier = Modifier.size(24.dp))

                                Column {
                                    Text(
                                        text = "Gemini",
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = IfxBlack
                                    )
                                    Text(
                                        text = "Describe your routine",
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 12.sp,
                                        color = IfxMuted
                                    )
                                }
                            }

                            // Strict single-line badge: 3 steps in 2s
                            Box(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .background(IfxLime, RoundedCornerShape(16.dp))
                                    .border(1.2.dp, IfxBlack, RoundedCornerShape(16.dp))
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            ) {
                                Text(
                                    text = "3 steps in 2s",
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.5.sp,
                                    maxLines = 1,
                                    softWrap = false,
                                    color = IfxBlack
                                )
                            }
                        }

                        // Routine description with highlighted theme green keyword chips
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "When a",
                                fontFamily = Inter,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = IfxBlack
                            )
                            HighlightChip("deadline email")
                            Text(
                                text = "arrives,",
                                fontFamily = Inter,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = IfxBlack
                            )
                            Text(
                                text = "add it to",
                                fontFamily = Inter,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = IfxBlack
                            )
                            HighlightChip("Calendar")
                            Text(
                                text = "and save a",
                                fontFamily = Inter,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = IfxBlack
                            )
                            HighlightChip("note")
                            Text(
                                text = ".|",
                                fontFamily = Inter,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = IfxBlack
                            )
                        }

                        // Bottom action row of the composer
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "🎙",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(start = 4.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .background(IfxLime, CircleShape)
                                    .border(1.5.dp, IfxBlack, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "➔",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = IfxBlack
                                )
                            }
                        }
                    }
                }

                // 2. Cascade Generated Steps with Connecting Lines
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Step 1: TRIGGER (Left aligned)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.66f)
                            .shadow(8.dp, RoundedCornerShape(20.dp), spotColor = Color(0x33000000))
                            .background(IfxBlack, RoundedCornerShape(20.dp))
                            .padding(horizontal = 14.dp, vertical = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .background(Color(0x33C6FF3F), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .background(IfxLime, CircleShape)
                                    )
                                }

                                Column {
                                    Text(
                                        text = "TRIGGER",
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 10.sp,
                                        letterSpacing = 0.8.sp,
                                        color = IfxLime
                                    )
                                    Text(
                                        text = "New email\narrives",
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        lineHeight = 17.sp,
                                        color = Color.White
                                    )
                                }
                            }

                            androidx.compose.foundation.Image(
                                painter = painterResource(id = R.drawable.ic_check_badge),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    // Connecting Line 1: Drops down from Step 1 into Step 2
                    Box(
                        modifier = Modifier
                            .padding(start = 48.dp)
                            .height(18.dp)
                            .width(2.5.dp)
                            .background(IfxBlack)
                    )

                    // Step 2: CONDITION (Shifted right by 28.dp)
                    Box(
                        modifier = Modifier
                            .padding(start = 28.dp)
                            .fillMaxWidth(0.82f)
                            .shadow(8.dp, RoundedCornerShape(20.dp), spotColor = Color(0x1A000000))
                            .background(Color.White, RoundedCornerShape(20.dp))
                            .border(1.6.dp, IfxBlack, RoundedCornerShape(20.dp))
                            .padding(horizontal = 14.dp, vertical = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "◇", fontSize = 16.sp, fontWeight = FontWeight.Bold)

                                Column {
                                    Text(
                                        text = "CONDITION",
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 10.sp,
                                        letterSpacing = 0.8.sp,
                                        color = IfxBlack
                                    )
                                    Text(
                                        text = "contains deadline",
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = IfxBlack
                                    )
                                }
                            }

                            androidx.compose.foundation.Image(
                                painter = painterResource(id = R.drawable.ic_check_badge_dark),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    // Connecting Line 2: Drops down from Step 2 into Step 3
                    Box(
                        modifier = Modifier
                            .padding(start = 76.dp)
                            .height(18.dp)
                            .width(2.5.dp)
                            .background(IfxBlack)
                    )

                    // Step 3: ACTION (Shifted right by 56.dp)
                    Box(
                        modifier = Modifier
                            .padding(start = 56.dp)
                            .fillMaxWidth()
                            .shadow(8.dp, RoundedCornerShape(20.dp), spotColor = Color(0x33000000))
                            .background(IfxBlack, RoundedCornerShape(20.dp))
                            .padding(horizontal = 14.dp, vertical = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "✕",
                                    color = IfxLime,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )

                                Column {
                                    Text(
                                        text = "ACTION",
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 10.sp,
                                        letterSpacing = 0.8.sp,
                                        color = IfxLime
                                    )
                                    Text(
                                        text = "Calendar + Note",
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = Color.White
                                    )
                                }
                            }

                            androidx.compose.foundation.Image(
                                painter = painterResource(id = R.drawable.ic_check_badge),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Display Headline (With 'Gemini' highlighted in crisp white on the green background)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Text(
                    text = "Describe what you\nwant, let Gemini help",
                    fontFamily = Inter,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 35.sp,
                    lineHeight = 41.sp,
                    letterSpacing = (-0.5).sp,
                    color = IfxBlack
                )
            }
        }
    }
}

/**
 * Reusable highlighted keyword chip.
 * Uses the signature theme lime green background with bold black text.
 */
@Composable
private fun HighlightChip(text: String) {
    Box(
        modifier = Modifier
            .background(IfxLime, RoundedCornerShape(8.dp))
            .border(1.dp, Color(0x22000000), RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text = text,
            fontFamily = Inter,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = IfxBlack
        )
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun OnboardingThirdPagePreview() {
    IFXTheme {
        OnboardingThirdPageContent()
    }
}
