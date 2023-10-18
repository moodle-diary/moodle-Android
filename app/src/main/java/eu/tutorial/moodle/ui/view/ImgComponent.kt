package eu.tutorial.moodle.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ImgComponent (){
    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .clip(shape = RoundedCornerShape(18.dp))
            .background(Color(0XFF2A292B))
            .fillMaxWidth()
            .height(300.dp),
    ) {

    }

}