package eu.tutorial.moodle.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.navigation.PostDestination

@Composable
fun EmotionComponent(
    emotion : Int,
    navController: NavController
){
    val emotionItem = if (emotion != 0) emotion.toString() else "오늘 기분이 어때요?"

    Card(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .background(color = Color(0XFF151515)),
    ) {
        Column(
            modifier = Modifier
                .height(300.dp)
                .fillMaxSize()
                .background(color = Color(0XFF151515)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            Button(onClick = { navController.navigate(PostDestination.route) },
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .defaultMinSize(
                        minWidth = 66.dp,
                        minHeight = 66.dp
                    ),
                contentPadding = PaddingValues(10.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF212122)
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.plusbutton),
                    contentDescription = "plusbutton",
                    modifier = Modifier.size(32.dp)
                )
            }

            Text(
                text = emotionItem,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFFDFDFDF)
                ),
            )
        }
    }
}
