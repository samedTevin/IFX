package com.samedtevin.ifx.onboarding.ui

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.WormIndicatorType

@Composable
fun IndicatorUI(pageCount: Int, pagerState: PagerState){

    DotsIndicator(
        dotCount = pageCount,
        type = WormIndicatorType(
            dotsGraphic = DotGraphic(
                size = 16.dp,
                borderWidth = 2.dp,
                borderColor = MaterialTheme.colorScheme.primary,
                color = Color.Transparent,
            ),
            wormDotGraphic = DotGraphic(
                size = 16.dp,
                color = MaterialTheme.colorScheme.primary,
            )
        ),
        pagerState = pagerState
    )
}

@Preview(showBackground = true)
 @Composable
fun IndicatorUIPreview1(){
    val pagerState = rememberPagerState(
        pageCount = { 3 }
    )

    IndicatorUI(
        pageCount = 3,
        pagerState = pagerState
    )
}
