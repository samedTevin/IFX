package com.samedtevin.ifx.welcome.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samedtevin.ifx.R
import com.samedtevin.ifx.ui.theme.IfxBlack
import com.samedtevin.ifx.ui.theme.IfxLime
import com.samedtevin.ifx.ui.theme.IfxMuted
import com.samedtevin.ifx.ui.theme.Inter
import com.samedtevin.ifx.welcome.SavedAccountInfo

/**
 * High-fidelity top header matching the IFX Welcome mockups:
 * Features pitch-black curved card with grid overlay, ifx brand logo,
 * circuit signal diagram (halo dot -> line -> diamond -> X),
 * and dynamic headline/subheading.
 */
@Composable
fun WelcomeHeader(
    title: String,
    subtitle: String = "IF this, do X.",
    modifier: Modifier = Modifier
) {
    val statusBarTop = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        // Pitch-black card container with rounded bottom corners
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp + statusBarTop)
                .clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
                .background(IfxBlack)
        ) {
            // Circuit diagram, ifx logo and grid art
            Image(
                painter = painterResource(id = R.drawable.ic_welcome_header_art),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(330.dp)
                    .align(Alignment.BottomCenter)
            )

            // Dynamic Text overlay positioned on the lower left of the card
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 24.dp, end = 72.dp, bottom = 26.dp)
            ) {
                Text(
                    text = title,
                    fontFamily = Inter,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 38.sp,
                    lineHeight = 44.sp,
                    letterSpacing = (-0.5).sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = subtitle,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = IfxLime
                )
            }
        }
    }
}

/**
 * Interactive authentication option card with dynamic selection states:
 * - Unselected: Light gray card with white icon box (matching Photo 2).
 * - Selected: Pitch-black card with electric lime icon box (matching Photo 3).
 * Transitions smoothly with animated color transitions upon click.
 */
@Composable
fun AuthOptionCard(
    title: String,
    subtitle: String,
    iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    iconSize: Dp = 22.dp
) {
    // Dynamic animated colors matching third photo on selection
    val cardBg by animateColorAsState(
        targetValue = if (isSelected) IfxBlack else Color(0xFFF7F8FA),
        animationSpec = tween(durationMillis = 220),
        label = "authCardBg"
    )

    val cardBorderColor by animateColorAsState(
        targetValue = if (isSelected) Color.Transparent else Color(0xFFE8EAED),
        animationSpec = tween(durationMillis = 220),
        label = "authCardBorder"
    )

    val iconBoxBg by animateColorAsState(
        targetValue = if (isSelected) IfxLime else Color.White,
        animationSpec = tween(durationMillis = 220),
        label = "authIconBoxBg"
    )

    val iconBoxBorderColor by animateColorAsState(
        targetValue = if (isSelected) Color.Transparent else Color(0xFFE5E7EB),
        animationSpec = tween(durationMillis = 220),
        label = "authIconBoxBorder"
    )

    val titleColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else IfxBlack,
        animationSpec = tween(durationMillis = 220),
        label = "authTitleColor"
    )

    val subtitleColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFFA0A5AA) else IfxMuted,
        animationSpec = tween(durationMillis = 220),
        label = "authSubtitleColor"
    )

    val chevronTint by animateColorAsState(
        targetValue = if (isSelected) Color.White else Color(0xFF1E2022),
        animationSpec = tween(durationMillis = 220),
        label = "authChevronTint"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(74.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(cardBg)
            .border(1.dp, cardBorderColor, RoundedCornerShape(20.dp))
            .clickable(
                role = Role.Button,
                onClick = onClick
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Left Icon Container (turns vibrant lime when selected)
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(iconBoxBg)
                    .border(1.dp, iconBoxBorderColor, RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = IfxBlack,
                    modifier = Modifier.size(iconSize)
                )
            }

            // Labels
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = title,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = titleColor
                )
                Text(
                    text = subtitle,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = subtitleColor
                )
            }

            // Trailing Chevron
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_right_gray),
                contentDescription = null,
                tint = chevronTint,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

/**
 * Saved account profile card showing cached identity (e.g. Alex Morgan)
 * or dynamic placeholder state when no saved account is present.
 */
@Composable
fun SavedAccountCard(
    account: SavedAccountInfo?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(76.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(IfxBlack)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Initial Avatar Circle (Lime with bold black text if account, subtle circle if placeholder)
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(if (account != null) IfxLime else Color(0xFF262626)),
                contentAlignment = Alignment.Center
            ) {
                if (account != null) {
                    Text(
                        text = account.initials,
                        fontFamily = Inter,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = IfxBlack
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_auth_guest),
                        contentDescription = "No account avatar",
                        tint = Color(0xFFA0A5AA),
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            // Name & Email (Dynamic or placeholder)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = account?.fullName ?: "No saved account",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.5.sp,
                    color = if (account != null) Color.White else Color(0xFFE0E0E0)
                )
                Text(
                    text = account?.email ?: "Sign in below to backup your Sparks",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = Color(0xFFA0A5AA)
                )
            }

            // Provider Pill Badge (e.g. Google, or Guest placeholder)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(percent = 50))
                    .background(if (account != null) IfxLime else Color(0xFF262626))
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = account?.provider ?: "Guest",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = if (account != null) IfxBlack else Color(0xFFA0A5AA)
                )
            }
        }
    }
}

/**
 * Primary action button: 'Continue as {Name}' in electric lime with rounded corners.
 * Adapts to account presence:
 * - 'Continue as Alex' if account is present
 * - 'Continue as guest' if no account is saved
 */
@Composable
fun ContinueAsAccountButton(
    account: SavedAccountInfo?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonText = when {
        account != null && account.firstName.isNotEmpty() -> "Continue as ${account.firstName}"
        account != null && account.fullName.isNotEmpty() -> "Continue as ${account.fullName}"
        account != null -> "Continue"
        else -> "Continue as guest"
    }

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = IfxLime,
            contentColor = IfxBlack
        ),
        shape = RoundedCornerShape(28.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(
            text = buttonText,
            fontFamily = Inter,
            fontWeight = FontWeight.Bold,
            fontSize = 16.5.sp,
            color = IfxBlack
        )
    }
}

/**
 * Section header with uppercase tracking (e.g. 'CHOOSE HOW TO START').
 */
@Composable
fun WelcomeSectionHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        letterSpacing = 0.8.sp,
        color = IfxMuted,
        modifier = modifier
    )
}

/**
 * Bottom interactive footer with underline link.
 */
@Composable
fun WelcomeFooter(
    prefixText: String,
    linkText: String,
    onLinkClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = prefixText,
            fontFamily = Inter,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = IfxMuted
        )
        Text(
            text = linkText,
            fontFamily = Inter,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = IfxBlack,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable(onClick = onLinkClick)
        )
    }
}
