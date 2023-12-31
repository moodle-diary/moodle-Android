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
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.contentBlack

@Composable
fun SettingAlarmScreen(
    navController: NavController
) {
    val buttonItemList = listOf(
        ButtonItem(0, "하루에 세 번"),
        ButtonItem(1, "하루에 한 번"),
        ButtonItem(2, "삼일에 세 번"),
    )
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGray)
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
                tint = contentBlack,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { navController.navigate(SettingDestination.route) }
            )
            Text(
                text = "리마인드 알림 주기",
                fontSize = 16.sp,
                color = contentBlack,
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
            Box(modifier = Modifier.size(28.dp))
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "주기 설정",
                fontSize = 16.sp,
                color = contentBlack,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                modifier = Modifier.padding(bottom = 7.dp, start = 22.dp, top = 10.dp)
            )
            Column(
                modifier = Modifier
                    .padding(12.dp, 3.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                repeat(3) { columnIndex ->
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
