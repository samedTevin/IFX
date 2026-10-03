package com.samedtevin.ifx.onboarding

import androidx.annotation.DrawableRes
import com.samedtevin.ifx.R

sealed class OnboardingModel(
    @DrawableRes val logo: Int = R.drawable.ic_logo,
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {

    data object FirstPage: OnboardingModel(
        image = R.drawable.onboarding_1,
        title = "Pick a trigger,\n let IFX watch",
        description = "Arrive home, hit 10 PM or run low on battery.\n Choose the moment that matters and\n IFX will watch for it."
    )

    data object SecondPage: OnboardingModel(
        image = R.drawable.onboarding_2,
        title = "Then it acts\n for you",
        description = "Turn on Wi-Fi, silence your phone or send a\n message. Actions run automatically,\n every single time."
    )

    data object ThirdPage: OnboardingModel(
        image = R.drawable.onboarding_3,
        title = "Build your own\n rules",
        description = "Combine triggers and actions in a few taps.\n Turn rules on or off anytime. Everything\n stays on your device."
    )
}