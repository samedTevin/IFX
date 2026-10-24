package com.samedtevin.ifx.welcome.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samedtevin.ifx.R
import com.samedtevin.ifx.ui.theme.IFXTheme
import com.samedtevin.ifx.welcome.AuthOptionType
import com.samedtevin.ifx.welcome.SavedAccountInfo
import com.samedtevin.ifx.welcome.WelcomeMode

/**
 * Main Welcome Screen:
 * - Dynamically adapts to account presence (shows cached account or placeholder).
 * - Interactive box selection: clicking any option dynamically flips it to the
 *   highlighted black card with lime-green icon (as seen in the third photo).
 */
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    savedAccount: SavedAccountInfo? = SavedAccountInfo(),
    initialMode: WelcomeMode = if (savedAccount != null) WelcomeMode.SAVED_ACCOUNT else WelcomeMode.NEW_DEVICE,
    onEmailPasswordClick: () -> Unit = {},
    onGoogleClick: () -> Unit = {},
    onMicrosoftClick: () -> Unit = {},
    onGuestClick: () -> Unit = {},
    onContinueAsAccount: (SavedAccountInfo?) -> Unit = {},
    onSignInClick: () -> Unit = {},
    onModeChanged: ((WelcomeMode) -> Unit)? = null
) {
    var currentMode by rememberSaveable { mutableStateOf(initialMode) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Crossfade(
            targetState = currentMode,
            animationSpec = tween(durationMillis = 280),
            label = "welcomeModeCrossfade"
        ) { mode ->
            when (mode) {
                WelcomeMode.NEW_DEVICE -> {
                    WelcomeNewDeviceContent(
                        onEmailClick = onEmailPasswordClick,
                        onGoogleClick = onGoogleClick,
                        onMicrosoftClick = onMicrosoftClick,
                        onGuestClick = onGuestClick,
                        onSignInClick = onSignInClick
                    )
                }
                WelcomeMode.SAVED_ACCOUNT -> {
                    WelcomeSavedAccountContent(
                        account = savedAccount,
                        onContinueAsAccount = { onContinueAsAccount(savedAccount) },
                        onEmailClick = onEmailPasswordClick,
                        onMicrosoftClick = onMicrosoftClick,
                        onGuestClick = onGuestClick,
                        onUseDifferentAccountClick = {
                            currentMode = WelcomeMode.NEW_DEVICE
                            onModeChanged?.invoke(WelcomeMode.NEW_DEVICE)
                        }
                    )
                }
            }
        }
    }
}

/**
 * First Version: New Device layout ('Welcome to ifx').
 * Displays 'CHOOSE HOW TO START' and allows interactive option selection.
 * Selecting any card dynamically highlights it as in the third photo.
 */
@Composable
fun WelcomeNewDeviceContent(
    modifier: Modifier = Modifier,
    initialSelectedOption: AuthOptionType? = AuthOptionType.EMAIL,
    onEmailClick: () -> Unit = {},
    onGoogleClick: () -> Unit = {},
    onMicrosoftClick: () -> Unit = {},
    onGuestClick: () -> Unit = {},
    onSignInClick: () -> Unit = {}
) {
    var selectedOption by rememberSaveable { mutableStateOf(initialSelectedOption) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        // 1. Dark Grid Header with Circuit Diagram
        WelcomeHeader(
            title = "Welcome\nto ifx",
            subtitle = "IF this, do X."
        )

        // 2. Options Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            WelcomeSectionHeader(
                text = "CHOOSE HOW TO START",
                modifier = Modifier.padding(bottom = 2.dp)
            )

            // Option: Email & password
            AuthOptionCard(
                title = "Email & password",
                subtitle = "Cloud backup of your Sparks",
                iconRes = R.drawable.ic_auth_mail,
                isSelected = selectedOption == AuthOptionType.EMAIL,
                iconSize = 22.dp,
                onClick = {
                    selectedOption = AuthOptionType.EMAIL
                    onEmailClick()
                }
            )

            // Option: Continue with Google
            AuthOptionCard(
                title = "Continue with Google",
                subtitle = "Syncs Gmail and Calendar",
                iconRes = R.drawable.ic_auth_google,
                isSelected = selectedOption == AuthOptionType.GOOGLE,
                iconSize = 22.dp,
                onClick = {
                    selectedOption = AuthOptionType.GOOGLE
                    onGoogleClick()
                }
            )

            // Option: Continue with Microsoft
            AuthOptionCard(
                title = "Continue with Microsoft",
                subtitle = "Syncs Outlook and Calendar",
                iconRes = R.drawable.ic_auth_microsoft,
                isSelected = selectedOption == AuthOptionType.MICROSOFT,
                iconSize = 20.dp,
                onClick = {
                    selectedOption = AuthOptionType.MICROSOFT
                    onMicrosoftClick()
                }
            )

            // Option: Continue as guest
            AuthOptionCard(
                title = "Continue as guest",
                subtitle = "Anonymous. Stays on this device",
                iconRes = R.drawable.ic_auth_guest,
                isSelected = selectedOption == AuthOptionType.GUEST,
                iconSize = 22.dp,
                onClick = {
                    selectedOption = AuthOptionType.GUEST
                    onGuestClick()
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Footer Link
            WelcomeFooter(
                prefixText = "Have an account? ",
                linkText = "Sign in",
                onLinkClick = onSignInClick
            )
        }
    }
}

/**
 * Second Version: Saved Account layout ('Welcome back').
 * Features:
 * - Dynamic account card: displays cached user details or placeholder if account is null.
 * - Primary button adapting to the account.
 * - Interactive alternative options: clicking any box dynamically highlights it in
 *   pitch-black with vibrant electric lime icon box (matching Photo 3).
 */
@Composable
fun WelcomeSavedAccountContent(
    modifier: Modifier = Modifier,
    account: SavedAccountInfo? = SavedAccountInfo(),
    initialSelectedOption: AuthOptionType? = null,
    onContinueAsAccount: () -> Unit = {},
    onEmailClick: () -> Unit = {},
    onMicrosoftClick: () -> Unit = {},
    onGuestClick: () -> Unit = {},
    onUseDifferentAccountClick: () -> Unit = {}
) {
    var selectedOption by rememberSaveable { mutableStateOf(initialSelectedOption) }
    val firstName = account?.firstName?.ifEmpty { "Alex" } ?: "you"

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        // 1. Dark Grid Header with Circuit Diagram
        WelcomeHeader(
            title = "Welcome\nback",
            subtitle = "IF this, do X."
        )

        // 2. Saved Account & Alternative Options Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Dynamic Cached Account or Placeholder Profile Card
            SavedAccountCard(account = account)

            // Primary 'Continue as {FirstName}' CTA (or 'Continue as guest' if no account)
            ContinueAsAccountButton(
                account = account,
                onClick = onContinueAsAccount,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            WelcomeSectionHeader(
                text = "OR USE ANOTHER WAY",
                modifier = Modifier.padding(top = 2.dp, bottom = 2.dp)
            )

            // Option: Email and password (highlights on click like Photo 3)
            AuthOptionCard(
                title = "Email and password",
                subtitle = "Sign in with your password",
                iconRes = R.drawable.ic_auth_mail,
                isSelected = selectedOption == AuthOptionType.EMAIL,
                iconSize = 22.dp,
                onClick = {
                    selectedOption = AuthOptionType.EMAIL
                    onEmailClick()
                }
            )

            // Option: Continue with Microsoft (highlights on click)
            AuthOptionCard(
                title = "Continue with Microsoft",
                subtitle = "Syncs Outlook and Calendar",
                iconRes = R.drawable.ic_auth_microsoft,
                isSelected = selectedOption == AuthOptionType.MICROSOFT,
                iconSize = 20.dp,
                onClick = {
                    selectedOption = AuthOptionType.MICROSOFT
                    onMicrosoftClick()
                }
            )

            // Option: Continue as guest (highlights on click)
            AuthOptionCard(
                title = "Continue as guest",
                subtitle = "Anonymous. Stays on this device",
                iconRes = R.drawable.ic_auth_guest,
                isSelected = selectedOption == AuthOptionType.GUEST,
                iconSize = 22.dp,
                onClick = {
                    selectedOption = AuthOptionType.GUEST
                    onGuestClick()
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Footer Link
            WelcomeFooter(
                prefixText = "Not $firstName? ",
                linkText = "Use a different account",
                onLinkClick = onUseDifferentAccountClick
            )
        }
    }
}

// =========================================================================
// Previews covering all states (Dimensions 390 x 844 dp)
// =========================================================================

@Preview(name = "Saved Account - Active Account", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun WelcomeSavedAccountPreview() {
    IFXTheme {
        WelcomeSavedAccountContent(
            account = SavedAccountInfo(
                fullName = "Alex Morgan",
                email = "alex@gmail.com",
                provider = "Google",
                initials = "AM"
            )
        )
    }
}

@Preview(name = "Saved Account - Placeholder (No Account)", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun WelcomeSavedAccountPlaceholderPreview() {
    IFXTheme {
        WelcomeSavedAccountContent(
            account = null
        )
    }
}

@Preview(name = "New Device - Welcome to ifx", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun WelcomeNewDevicePreview() {
    IFXTheme {
        WelcomeNewDeviceContent()
    }
}

@Preview(name = "Interactive Welcome Screen", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun WelcomeScreenInteractivePreview() {
    IFXTheme {
        WelcomeScreen()
    }
}
