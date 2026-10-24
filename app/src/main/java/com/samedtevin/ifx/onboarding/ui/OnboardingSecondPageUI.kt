package com.samedtevin.ifx.onboarding.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
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
 * Second Onboarding Page: 'Create your flow, visually'
 * Demonstrates the interactive visual rule builder with connected nodes.
 * The grid background extends full-bleed across the entire screen.
 */
@Composable
fun OnboardingSecondPageContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // 1. Full-bleed Grid Background extending under all controls
        Image(
            painter = painterResource(id = R.drawable.ic_bg_grid_light),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(top = 56.dp, bottom = 110.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Display Headline with 'flow' and 'visually' highlighted in theme green
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "Create your flow,\nvisually",
                    fontFamily = Inter,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 37.sp,
                    lineHeight = 43.sp,
                    letterSpacing = (-0.5).sp,
                    color = IfxBlack
                )
            }

            // Visual Flow Builder Diagram
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. Trigger Node (Black)
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.72f)
                        .align(Alignment.Start)
                        .shadow(8.dp, RoundedCornerShape(22.dp), spotColor = Color(0x33000000))
                        .background(IfxBlack, RoundedCornerShape(22.dp))
                        .padding(horizontal = 18.dp, vertical = 14.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                        Text(
                            text = "TRIGGER · WHEN",
                            fontFamily = Inter,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 11.sp,
                            letterSpacing = 1.sp,
                            color = IfxLime
                        )
                        Text(
                            text = "New email arrives",
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }

                // Plus Add Connector between Trigger and Condition
                Box(
                    modifier = Modifier
                        .height(38.dp)
                        .width(40.dp)
                        .align(Alignment.Start)
                        .padding(start = 78.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawLine(
                            color = IfxBlack,
                            start = Offset(size.width / 2, 0f),
                            end = Offset(size.width / 2, size.height),
                            strokeWidth = 2.dp.toPx()
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(IfxLime, CircleShape)
                            .border(1.5.dp, IfxBlack, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(IfxBlack, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "+",
                                color = IfxLime,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // 2. Condition Node (Lime)
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .align(Alignment.Start)
                        .shadow(10.dp, RoundedCornerShape(22.dp), spotColor = Color(0x33C6FF3F))
                        .background(IfxLime, RoundedCornerShape(22.dp))
                        .border(1.8.dp, IfxBlack, RoundedCornerShape(22.dp))
                        .padding(horizontal = 18.dp, vertical = 14.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                        Text(
                            text = "CONDITION",
                            fontFamily = Inter,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 11.sp,
                            letterSpacing = 1.sp,
                            color = IfxBlack
                        )
                        Text(
                            text = "IF · contains 'deadline'?",
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = IfxBlack
                        )
                    }
                }

                // Two Downward Arrows branching out to Actions
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Canvas(modifier = Modifier.weight(1f).height(42.dp)) {
                        val centerX = size.width * 0.55f
                        val bottomY = size.height - 2.dp.toPx()

                        drawLine(
                            color = IfxBlack,
                            start = Offset(centerX, 0f),
                            end = Offset(centerX, bottomY),
                            strokeWidth = 2.dp.toPx()
                        )

                        val arrowPath = Path().apply {
                            moveTo(centerX, bottomY + 3.dp.toPx())
                            lineTo(centerX - 6.dp.toPx(), bottomY - 8.dp.toPx())
                            lineTo(centerX + 6.dp.toPx(), bottomY - 8.dp.toPx())
                            close()
                        }
                        drawPath(arrowPath, color = IfxLime)
                        drawPath(arrowPath, color = IfxBlack, style = androidx.compose.ui.graphics.drawscope.Stroke(1.5.dp.toPx()))
                    }

                    Canvas(modifier = Modifier.weight(1f).height(42.dp)) {
                        val centerX = size.width * 0.60f
                        val bottomY = size.height - 2.dp.toPx()

                        drawLine(
                            color = IfxBlack,
                            start = Offset(centerX, 0f),
                            end = Offset(centerX, bottomY),
                            strokeWidth = 2.dp.toPx()
                        )

                        val arrowPath = Path().apply {
                            moveTo(centerX, bottomY + 3.dp.toPx())
                            lineTo(centerX - 6.dp.toPx(), bottomY - 8.dp.toPx())
                            lineTo(centerX + 6.dp.toPx(), bottomY - 8.dp.toPx())
                            close()
                        }
                        drawPath(arrowPath, color = IfxLime)
                        drawPath(arrowPath, color = IfxBlack, style = androidx.compose.ui.graphics.drawscope.Stroke(1.5.dp.toPx()))
                    }
                }

                // 3. Action Nodes (Two cards side by side)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Action 1: Add to Calendar
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .shadow(8.dp, RoundedCornerShape(22.dp), spotColor = Color(0x33000000))
                            .background(IfxBlack, RoundedCornerShape(22.dp))
                            .padding(16.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = "ACTION",
                                fontFamily = Inter,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 11.sp,
                                letterSpacing = 1.sp,
                                color = IfxLime
                            )
                            Text(
                                text = "Add to Calendar",
                                fontFamily = Inter,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                lineHeight = 19.sp,
                                color = Color.White
                            )
                        }
                    }

                    // Action 2: Save to Notebook
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .shadow(8.dp, RoundedCornerShape(22.dp), spotColor = Color(0x33000000))
                            .background(IfxBlack, RoundedCornerShape(22.dp))
                            .padding(16.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = "ACTION",
                                fontFamily = Inter,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 11.sp,
                                letterSpacing = 1.sp,
                                color = IfxLime
                            )
                            Text(
                                text = "Save to\nNotebook",
                                fontFamily = Inter,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                lineHeight = 19.sp,
                                color = Color.White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // 4. Dashed Placeholder Step (+ Add step)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .dashedBorder(
                            color = Color(0xFFB0B0B0),
                            strokeWidth = 1.6.dp,
                            dashLength = 7.dp,
                            gapLength = 4.dp,
                            cornerRadius = 22.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "+",
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = IfxMuted
                        )
                        Text(
                            text = "Add step",
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = IfxMuted
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun OnboardingSecondPagePreview() {
    IFXTheme {
        OnboardingSecondPageContent()
    }
}
