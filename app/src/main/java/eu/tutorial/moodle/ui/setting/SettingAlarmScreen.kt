package eu.tutorial.moodle.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.ButtonItem
import eu.tutorial.moodle.ui.navigation.SettingDestination

@Composable
fun SettingAlarmScreen(
    navController: NavController
) {
    val buttonItemList = listOf(
        ButtonItem(0, "일주일에 한 번"),
        ButtonItem(1, "격주에 한 번"),
        ButtonItem(2, "한 달에 한 번"),
        ButtonItem(3, "3 달에 한 번")
    )
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFF151515))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "left",
                tint = Color.White,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { navController.navigate(SettingDestination.route) }
            )
            Text(
                text = "리마인드 알림 주기",
                fontSize = 16.sp,
                color = Color(0XFFDFDFDF),
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
            Box(modifier = Modifier.size(28.dp))
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "주기 설정",
                fontSize = 14.sp,
                color = Color(0XFFDFDFDF),
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                modifier = Modifier.padding(bottom = 7.dp, start = 22.dp, top = 10.dp)
            )
            Column(
                modifier = Modifier
                    .padding(12.dp, 3.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                repeat(4) { columnIndex ->
                    val itemIndex = columnIndex
                    val item = buttonItemList.get(itemIndex)
                    AlarmButton(
                        item = item,
                        isSelected = selectedIndex == itemIndex,
                        onTap = { selectedIndex = itemIndex }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun SettingAlarmScreenPreview() {
    val navController = rememberNavController()
    SettingAlarmScreen(navController = navController)
}
