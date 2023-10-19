package eu.tutorial.moodle.ui.setting

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.chart.ChartScreen

@Composable
fun SettingScreen(
    innerPaddingValues: PaddingValues = PaddingValues(0.dp),
    navController: NavController
) {
    var checkedAlarm by remember { mutableStateOf(true) }
    var checkedPassword by remember { mutableStateOf(true) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFF151515))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "설정창",
                fontSize = 16.sp,
                color = Color(0XFFDFDFDF),
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "알람 설정",
                fontSize = 14.sp,
                color = Color(0XFFDFDFDF),
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                modifier = Modifier.padding(bottom = 7.dp, start = 22.dp, top = 10.dp)
            )
            Row(
                modifier = Modifier
                    .padding(12.dp, 6.dp)
                    .fillMaxWidth()
                    .height(67.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0XFF212122))
                    .padding(start = 22.dp, end = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (checkedAlarm) "알람 켜짐" else "알람 꺼짐",
                    fontSize = 14.sp,
                    color = Color(0XFFDFDFDF),
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
                Switch(
                    checked = checkedAlarm,
                    onCheckedChange = {
                        checkedAlarm = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0XFF212122),
                        checkedTrackColor = Color(0XFFD9D9D9),
                        uncheckedThumbColor = Color(0XFFEDEDED),
                        uncheckedTrackColor = Color(0XFF686868),
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(12.dp, 6.dp)
                    .fillMaxWidth()
                    .height(78.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0XFF212122))
                    .padding(start = 22.dp, end = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "리마인드 알림 주기 설정하기",
                        fontSize = 14.sp,
                        color = Color(0XFFDFDFDF),
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                    Text(
                        text = "이전 일기를 추천해주는 주기를 설정합니다.",
                        fontSize = 11.sp,
                        color = Color(0XFFDFDFDF),
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                }
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "right",
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
            }


            Text(
                text = "잠금 설정",
                fontSize = 14.sp,
                color = Color(0XFFDFDFDF),
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                modifier = Modifier.padding(bottom = 7.dp, start = 22.dp, top = 30.dp)
            )
            Row(
                modifier = Modifier
                    .padding(12.dp, 6.dp)
                    .fillMaxWidth()
                    .height(67.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0XFF212122))
                    .padding(start = 22.dp, end = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (checkedPassword) "잠금 켜짐" else "잠금 꺼짐",
                    fontSize = 14.sp,
                    color = Color(0XFFDFDFDF),
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
                Switch(
                    checked = checkedPassword,
                    onCheckedChange = {
                        checkedPassword = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0XFF212122),
                        checkedTrackColor = Color(0XFFD9D9D9),
                        uncheckedThumbColor = Color(0XFFEDEDED),
                        uncheckedTrackColor = Color(0XFF686868),
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(12.dp, 6.dp)
                    .fillMaxWidth()
                    .height(67.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0XFF212122))
                    .padding(start = 22.dp, end = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "잠금 비밀번호 변경",
                    fontSize = 14.sp,
                    color = Color(0XFFDFDFDF),
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "right",
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun SettingScreenPreview() {
    val navController = rememberNavController()
    SettingScreen(navController = navController)
}