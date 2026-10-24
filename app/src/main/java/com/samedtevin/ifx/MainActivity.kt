package com.samedtevin.ifx

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.samedtevin.ifx.login.ui.ForgotPasswordScreen
import com.samedtevin.ifx.login.ui.LoginScreen
import com.samedtevin.ifx.onboarding.ui.OnboardingScreen
import com.samedtevin.ifx.splash.SplashScreen
import com.samedtevin.ifx.ui.theme.IFXTheme
import com.samedtevin.ifx.welcome.SavedAccountInfo
import com.samedtevin.ifx.welcome.WelcomeMode
import com.samedtevin.ifx.welcome.ui.WelcomeScreen
import java.io.Serializable

/**
 * Top-level navigation destinations.
 */
enum class AppDestination : Serializable {
    ONBOARDING,
    WELCOME,
    LOGIN,
    FORGOT_PASSWORD
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. Initialize AndroidX core-splashscreen BEFORE super.onCreate()
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            IFXTheme {
                // 2. rememberSaveable persists the splash state across configuration changes (rotation)
                var showSplash by rememberSaveable { mutableStateOf(true) }

                Box(modifier = Modifier.fillMaxSize()) {
                    // Main app content: mounted underneath so that when the splash screen
                    // completes its 250ms fade-out, the main screen is seamlessly revealed.
                    AppContent()

                    // Splash screen overlay
                    if (showSplash) {
                        SplashScreen(
                            onFinished = {
                                showSplash = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AppContent() {
    val context = LocalContext.current
    var currentDestination by rememberSaveable { mutableStateOf(AppDestination.ONBOARDING) }

    // Dynamic saved account state backed by a custom Compose Saver for safe Bundle preservation
    var savedAccount by rememberSaveable(stateSaver = SavedAccountInfo.Saver) {
        mutableStateOf<SavedAccountInfo?>(
            SavedAccountInfo(
                fullName = "Alex Morgan",
                email = "alex@gmail.com",
                provider = "Google",
                initials = "AM"
            )
        )
    }

    Crossfade(
        targetState = currentDestination,
        animationSpec = tween(350),
        label = "appNavigationCrossfade"
    ) { destination ->
        when (destination) {
            AppDestination.ONBOARDING -> {
                OnboardingScreen(
                    onFinished = {
                        currentDestination = AppDestination.WELCOME
                    }
                )
            }
            AppDestination.WELCOME -> {
                WelcomeScreen(
                    savedAccount = savedAccount,
                    initialMode = if (savedAccount != null) WelcomeMode.SAVED_ACCOUNT else WelcomeMode.NEW_DEVICE,
                    onEmailPasswordClick = {
                        currentDestination = AppDestination.LOGIN
                    },
                    onGoogleClick = {
                        Toast.makeText(context, "Google selected", Toast.LENGTH_SHORT).show()
                    },
                    onMicrosoftClick = {
                        Toast.makeText(context, "Microsoft selected", Toast.LENGTH_SHORT).show()
                    },
                    onGuestClick = {
                        Toast.makeText(context, "Guest selected", Toast.LENGTH_SHORT).show()
                    },
                    onSignInClick = {
                        currentDestination = AppDestination.LOGIN
                    },
                    onContinueAsAccount = { account ->
                        if (account != null) {
                            Toast.makeText(context, "Continuing as ${account.fullName}", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Continuing as guest", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
            AppDestination.LOGIN -> {
                LoginScreen(
                    initialEmail = savedAccount?.email ?: "alex@gmail.com",
                    onBackClick = {
                        currentDestination = AppDestination.WELCOME
                    },
                    onSignInClick = { email, _ ->
                        Toast.makeText(context, "Signing in with $email", Toast.LENGTH_SHORT).show()
                    },
                    onForgotPasswordClick = {
                        currentDestination = AppDestination.FORGOT_PASSWORD
                    },
                    onGoogleClick = {
                        Toast.makeText(context, "Google selected", Toast.LENGTH_SHORT).show()
                    },
                    onMicrosoftClick = {
                        Toast.makeText(context, "Microsoft selected", Toast.LENGTH_SHORT).show()
                    },
                    onGuestClick = {
                        Toast.makeText(context, "Continuing as guest", Toast.LENGTH_SHORT).show()
                    },
                    onCreateAccountClick = {
                        currentDestination = AppDestination.WELCOME
                    }
                )
            }
            AppDestination.FORGOT_PASSWORD -> {
                ForgotPasswordScreen(
                    initialEmail = savedAccount?.email ?: "alex@gmail.com",
                    onBackClick = {
                        currentDestination = AppDestination.LOGIN
                    },
                    onSendResetLinkClick = { email ->
                        Toast.makeText(context, "Reset link sent to $email", Toast.LENGTH_SHORT).show()
                    },
                    onBackToSignInClick = {
                        currentDestination = AppDestination.LOGIN
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IFXTheme {
        AppContent()
    }
}
