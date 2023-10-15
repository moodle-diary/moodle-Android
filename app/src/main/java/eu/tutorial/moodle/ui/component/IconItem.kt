package eu.tutorial.moodle.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally, // 가운데 정렬
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(shape = RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center,
        ){
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(0XFF363637))
            )
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop, // 동그랗게 만듬
                modifier = Modifier
                    .size(44.dp)
            )
        }
    }
}

