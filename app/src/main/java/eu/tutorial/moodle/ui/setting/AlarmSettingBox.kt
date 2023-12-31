package eu.tutorial.moodle.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.navItemBackground

@Composable
fun AlarmSettingBox(
    checkedAlarm: Boolean,
    navController: NavController,
    onCheckedChange: (Boolean) -> Unit
) {
    Text(
        text = "알람 설정",
        fontSize = 16.sp,
        color = contentBlack,
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        modifier = Modifier.padding(bottom = 7.dp, start = 22.dp, top = 10.dp)
    )
    Row(
        modifier = Modifier
            .padding(12.dp, 6.dp)
            .fillMaxWidth()
            .height(67.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(containerGray)
            .padding(start = 22.dp, end = 22.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (checkedAlarm) "알람 켜짐" else "알람 꺼짐",
            fontSize = 14.sp,
            color = contentBlack,
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )
        Switch(
            checked = checkedAlarm,
            onCheckedChange = {
                onCheckedChange(it)
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
            .background(containerGray)
            .clickable { navController.navigate("settingAlarm") }
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
                color = contentBlack,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
            Text(
                text = "이전 일기를 추천해주는 주기를 설정합니다.",
                fontSize = 11.sp,
                color = contentBlack,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "right",
            tint = navItemBackground,
            modifier = Modifier.size(26.dp)
        )
    }

}