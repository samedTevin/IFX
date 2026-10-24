package com.samedtevin.ifx.onboarding.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.samedtevin.ifx.onboarding.OnboardingModel
import com.samedtevin.ifx.ui.theme.IFXTheme

/**
 * Routes each onboarding page model to its dedicated visual composition.
 */
@Composable
fun OnboardingGraphUI(
    onboardingModel: OnboardingModel,
    modifier: Modifier = Modifier
) {
    when (onboardingModel) {
        is OnboardingModel.FirstPage -> OnboardingFirstPageContent(modifier = modifier)
        is OnboardingModel.SecondPage -> OnboardingSecondPageContent(modifier = modifier)
        is OnboardingModel.ThirdPage -> OnboardingThirdPageContent(modifier = modifier)
        is OnboardingModel.FourthPage -> OnboardingFourthPageContent(modifier = modifier)
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun OnboardingGraphUIPreview1() {
    IFXTheme {
        OnboardingGraphUI(OnboardingModel.FirstPage)
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun OnboardingGraphUIPreview2() {
    IFXTheme {
        OnboardingGraphUI(OnboardingModel.SecondPage)
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun OnboardingGraphUIPreview3() {
    IFXTheme {
        OnboardingGraphUI(OnboardingModel.ThirdPage)
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun OnboardingGraphUIPreview4() {
    IFXTheme {
        OnboardingGraphUI(OnboardingModel.FourthPage)
    }
}
