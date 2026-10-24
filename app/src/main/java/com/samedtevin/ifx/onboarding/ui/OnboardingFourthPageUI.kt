package com.samedtevin.ifx.onboarding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.samedtevin.ifx.ui.theme.Inter

/**
 * Fourth Onboarding Page: 'Test it before it runs'
 * Features sleek dark mode, dry-run simulation gauge, 'it runs' highlighted in electric lime, and verified checklist.
 */
@Composable
fun OnboardingFourthPageContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(IfxBlack)
    ) {
        // Dark grid background
        Image(
            painter = painterResource(id = R.drawable.ic_bg_grid_dark),
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
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // 1. Display Headline with 'it runs' in vibrant lime
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 12.dp)
            ) {
                Text(
                    text = "Test it before\nit runs",
                    fontFamily = Inter,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 37.sp,
                    lineHeight = 43.sp,
                    letterSpacing = (-0.5).sp,
                    color = Color.White
                )
            }

            // 2. Central Gauge & Status Badge
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_dry_run_gauge),
                    contentDescription = "Simulation Gauge",
                    modifier = Modifier.size(220.dp)
                )

                // Status Badge: ▶ DRY-RUN OK · 0 errors
                Box(
                    modifier = Modifier
                        .background(IfxLime, RoundedCornerShape(20.dp))
                        .padding(horizontal = 18.dp, vertical = 8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "▶",
                            fontSize = 12.sp,
                            color = IfxBlack
                        )
                        Text(
                            text = "DRY-RUN OK · 0 errors",
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = IfxBlack
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 3. Verification Checklist
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ChecklistRow(text = "Trigger matched")
                ChecklistRow(text = "Condition evaluated TRUE")
                ChecklistRow(text = "Actions verified")
            }
        }
    }
}

@Composable
private fun ChecklistRow(text: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_check_badge),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = text,
            fontFamily = Inter,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun OnboardingFourthPagePreview() {
    IFXTheme {
        OnboardingFourthPageContent()
    }
}
