package com.samedtevin.ifx.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samedtevin.ifx.R
import com.samedtevin.ifx.ui.theme.IFXTheme
import com.samedtevin.ifx.ui.theme.IfxBlack
import com.samedtevin.ifx.ui.theme.IfxLime
import com.samedtevin.ifx.ui.theme.Inter

/**
 * High-fidelity Login section matching the IFX design specifications:
 * - Back button navigation
 * - Bold title "Welcome back" and subtitle "Sign in to pick up your Sparks."
 * - Outlined Email & Password fields with floating labels and eye visibility toggle
 * - "Forgot password?" right-aligned underlined action
 * - High-contrast electric lime "Sign in" primary button
 * - "or continue with" divider
 * - 3 equal-width social/auth buttons: Google, Microsoft, Guest
 * - "New to ifx? Create account" footer
 */
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    initialEmail: String = "alex@gmail.com",
    initialPassword: String = "••••••••••",
    onBackClick: () -> Unit = {},
    onSignInClick: (String, String) -> Unit = { _, _ -> },
    onForgotPasswordClick: () -> Unit = {},
    onGoogleClick: () -> Unit = {},
    onMicrosoftClick: () -> Unit = {},
    onGuestClick: () -> Unit = {},
    onCreateAccountClick: () -> Unit = {}
) {
    var email by rememberSaveable { mutableStateOf(initialEmail) }
    var password by rememberSaveable { mutableStateOf(initialPassword) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 24.dp)
    ) {
        // 1. Top Back Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 12.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "Back",
                    tint = IfxBlack,
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        // 2. Headline & Subtitle
        Text(
            text = "Welcome back",
            fontFamily = Inter,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp,
            lineHeight = 38.sp,
            letterSpacing = (-0.5).sp,
            color = IfxBlack
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Sign in to pick up your Sparks.",
            fontFamily = Inter,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            color = Color(0xFF6B7280)
        )

        Spacer(modifier = Modifier.height(28.dp))

        // 3. Email Input Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    text = "Email",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = IfxBlack,
                unfocusedBorderColor = Color(0xFF9CA3AF),
                focusedLabelColor = IfxBlack,
                unfocusedLabelColor = Color(0xFF6B7280),
                focusedTextColor = IfxBlack,
                unfocusedTextColor = IfxBlack,
                cursorColor = IfxBlack,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 4. Password Input Field with Visibility Toggle
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    text = "Password",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation('•'),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    onSignInClick(email, password)
                }
            ),
            trailingIcon = {
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible }
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isPasswordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility
                        ),
                        contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
                        tint = Color(0xFF5F6368),
                        modifier = Modifier.size(22.dp)
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = IfxBlack,
                unfocusedBorderColor = IfxBlack,
                focusedLabelColor = IfxBlack,
                unfocusedLabelColor = IfxBlack,
                focusedTextColor = IfxBlack,
                unfocusedTextColor = IfxBlack,
                cursorColor = IfxBlack,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 5. "Forgot password?" Link
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = "Forgot password?",
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                color = IfxBlack,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = false, radius = 20.dp),
                    onClick = onForgotPasswordClick
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 6. Primary "Sign in" Action Button
        Button(
            onClick = {
                keyboardController?.hide()
                onSignInClick(email, password)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = IfxLime,
                contentColor = IfxBlack
            ),
            shape = RoundedCornerShape(28.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        ) {
            Text(
                text = "Sign in",
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                fontSize = 16.5.sp,
                color = IfxBlack
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // 7. "or continue with" Divider
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Color(0xFFE5E7EB),
                thickness = 1.dp
            )
            Text(
                text = "or continue with",
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                color = Color(0xFF6B7280),
                modifier = Modifier.padding(horizontal = 14.dp)
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Color(0xFFE5E7EB),
                thickness = 1.dp
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        // 8. Social Auth Options Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SocialAuthButton(
                text = "Google",
                iconRes = R.drawable.ic_auth_google,
                onClick = onGoogleClick,
                modifier = Modifier.weight(1f)
            )
            SocialAuthButton(
                text = "Microsoft",
                iconRes = R.drawable.ic_auth_microsoft,
                onClick = onMicrosoftClick,
                modifier = Modifier.weight(1f)
            )
            SocialAuthButton(
                text = "Guest",
                iconRes = R.drawable.ic_auth_guest,
                onClick = onGuestClick,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // 9. Footer Link: "New to ifx? Create account"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "New to ifx? ",
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color(0xFF6B7280)
            )
            Text(
                text = "Create account",
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                color = IfxBlack,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = false, radius = 20.dp),
                    onClick = onCreateAccountClick
                )
            )
        }
    }
}

/**
 * Outlined social authentication pill button with icon and title.
 */
@Composable
fun SocialAuthButton(
    text: String,
    iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(54.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF9FAFB))
            .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(16.dp))
            .clickable(
                role = Role.Button,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = IfxBlack,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = text,
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                fontSize = 13.5.sp,
                color = IfxBlack
            )
        }
    }
}

@Preview(name = "Login Screen - Default", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun LoginScreenPreview() {
    IFXTheme {
        LoginScreen()
    }
}

@Preview(name = "Login Screen - Empty Fields", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun LoginScreenEmptyPreview() {
    IFXTheme {
        LoginScreen(
            initialEmail = "",
            initialPassword = ""
        )
    }
}
