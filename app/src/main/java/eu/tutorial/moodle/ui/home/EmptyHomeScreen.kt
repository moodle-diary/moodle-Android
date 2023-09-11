package eu.tutorial.moodle.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R

@Composable
fun EmptyHomeScreen(
    innerPaddingValues: PaddingValues = PaddingValues(0.dp)
){
    Column(
        modifier = Modifier
            .padding(innerPaddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp),
            shape = RoundedCornerShape(32.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .background(color = Color(0XEFEFEFEF)),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Emotions",
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Color(0XFF414141)
                    ),
                )
            }
        }

        Spacer(modifier = Modifier.padding(6.dp))

        Card(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp),
            shape = RoundedCornerShape(32.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(104.dp)
                    .fillMaxWidth()
                    .background(color = Color(0XD9D9D9D9)),
                contentAlignment = Alignment.Center,
            ){
                Text(
                    text = "Icons",
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Color(0XFFFFFFFF)
                    ),
                )
            }
        }

        Spacer(modifier = Modifier.padding(6.dp))

        Card(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp),
            shape = RoundedCornerShape(32.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(104.dp)
                    .fillMaxWidth()
                    .background(color = Color(0XD9D9D9D9)),
                contentAlignment = Alignment.Center,
            ){
                Text(
                    text = "Notes",
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Color(0XFFFFFFFF)
                    ),
                )
            }
        }

    }

}