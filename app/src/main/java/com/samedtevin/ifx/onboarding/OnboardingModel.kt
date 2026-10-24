package com.samedtevin.ifx.onboarding

/**
 * Data model representing the 4 onboarding stages in the ifx automation journey.
 */
sealed class OnboardingModel(
    val title: String,
    val description: String
) {
    data object FirstPage : OnboardingModel(
        title = "Pick a trigger,\nlet ifx watch",
        description = "Choose what starts your automation."
    )

    data object SecondPage : OnboardingModel(
        title = "Create your flow,\nvisually",
        description = "Chain triggers, conditions, and actions visually on a canvas."
    )

    data object ThirdPage : OnboardingModel(
        title = "Describe what you\nwant, let Gemini help",
        description = "Type or speak your routine in plain English, and AI builds the flow."
    )

    data object FourthPage : OnboardingModel(
        title = "Test it before\nit runs",
        description = "Run a live dry-run simulation to ensure zero errors before activating."
    )
}
