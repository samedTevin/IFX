package com.samedtevin.ifx.onboarding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samedtevin.ifx.onboarding.OnboardingModel
import com.samedtevin.ifx.ui.theme.Inter


@Composable
fun OnboardingGraphUI(onboardingModel: OnboardingModel){

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = onboardingModel.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.Center)
            )

            ButtonUi(
                text = "Skip",
                backgroundColor = Color.Transparent,
                textColor = Color.Gray,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
            }
        }


        Image(
            painter = painterResource(id = onboardingModel.image),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().padding(30.dp,0.dp),
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.size(60.dp))

        Text(
            text = onboardingModel.title,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            fontFamily = Inter,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground

        )

        Spacer(modifier = Modifier.fillMaxWidth().size(10.dp))

        Text(
            text = onboardingModel.description,
            modifier = Modifier.fillMaxWidth().padding(15.dp,0.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontFamily = Inter,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline
        )

        Spacer(modifier = Modifier.fillMaxWidth().size(10.dp))

    }



}


@Preview(showBackground = true)
@Composable
fun OnboardingGraphUIPreview1(){
    OnboardingGraphUI(OnboardingModel.FirstPage)
}

@Preview(showBackground = true)
@Composable
fun OnboardingGraphUIPreview2(){
    OnboardingGraphUI(OnboardingModel.SecondPage)
}

@Preview(showBackground = true)
@Composable
fun OnboardingGraphUIPreview3(){
    OnboardingGraphUI(OnboardingModel.ThirdPage)
}