package eu.tutorial.moodle.ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EmotionQuadrant() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0XFF151515)
        ),
    ) {
        Canvas(
            modifier = Modifier
                .size(300.dp)
                .clip(CircleShape)
                .background(Color(0XFF212122)),
            onDraw = {
                drawArc(
                    brush = Brush.linearGradient(listOf(Color(0XFF2BECA7), Color(0XFFAEEC2B)), start = Offset.Zero, end = Offset.Infinite),
                    startAngle = 0f,
                    sweepAngle = 90f,
                    useCenter = true,
                    topLeft = Offset(75.dp.toPx(), 75.dp.toPx()),
                    size = Size(150.dp.toPx(), 150.dp.toPx())
                )
                drawArc(
                    brush = Brush.linearGradient(listOf(Color(0XFF2BE0EC), Color(0XFF2B8FEC)), start = Offset.Zero, end = Offset.Infinite),
                    startAngle = 90f,
                    sweepAngle = 90f,
                    useCenter = true,
                    topLeft = Offset(50.dp.toPx(), 50.dp.toPx()),
                    size = Size(200.dp.toPx(), 200.dp.toPx())
                )
                drawArc(
                    brush = Brush.linearGradient(listOf(Color(0XFFF96322), Color(0XFFF9227C)), start = Offset.Zero, end = Offset.Infinite),
                    startAngle = 180f,
                    sweepAngle = 90f,
                    useCenter = true,
                    topLeft = Offset(50.dp.toPx(), 50.dp.toPx()),
                    size = Size(200.dp.toPx(), 200.dp.toPx())
                )
                drawArc(
                    brush = Brush.linearGradient(listOf(Color(0XFFFFCF25), Color(0XFFF198FF)), start = Offset.Zero, end = Offset.Infinite),
                    startAngle = 270f,
                    sweepAngle = 90f,
                    useCenter = true,
                    topLeft = Offset(0.dp.toPx(), 0.dp.toPx()),
                    size = Size(300.dp.toPx(), 300.dp.toPx())
                )
            },
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun EmotionQuadrantPreview(){
    EmotionQuadrant()
}