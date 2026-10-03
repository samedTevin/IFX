package com.samedtevin.ifx.onboarding.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonUi(
    text: String = "Next",
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    fontSize: Int = 14,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(
        containerColor = backgroundColor,
        contentColor = textColor,
    ), shape = RoundedCornerShape(10.dp), modifier = modifier
    ){

        Text(text = text, fontSize = fontSize.sp, style = textStyle)

    }
}

@Preview(showBackground = true)
@Composable
fun NextButton(){
        ButtonUi(text = "Next"){

        }
}

@Preview(showBackground = true)
@Composable
fun BackButton(){
    ButtonUi(text = "Back", backgroundColor = Color.Transparent,textColor = Color.Gray, textStyle = MaterialTheme.typography.bodySmall, fontSize = 13){

    }
}

@Preview(showBackground = true)
@Composable
fun SkipButton(){
    ButtonUi(text = "Skip", backgroundColor = Color.Transparent, textColor = Color.Gray, textStyle = MaterialTheme.typography.bodySmall, fontSize = 13){

    }
}