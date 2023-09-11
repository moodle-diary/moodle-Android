package eu.tutorial.moodle.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.NavType
import eu.tutorial.moodle.ui.home.DetailHomeScreen
import eu.tutorial.moodle.ui.navigation.NavigationDestination

object CalendarDestination : NavigationDestination{
    override val route: String
        get() = "Calendar"
    override val titleRes: Int
        get() = R.string.calendar_title
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(
    onTabPressed: () -> Unit = {}
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            mailboxType = NavType.Home,
            icon = Icons.Default.Home,
            text = "Home"
        ),
        NavigationItemContent(
            mailboxType = NavType.Calendar,
            icon = Icons.Default.CalendarMonth,
            text = "Calendar"
        ),
        NavigationItemContent(
            mailboxType = NavType.Edit,
            icon = Icons.Rounded.Edit,
            text = "Edit"
        ),
        NavigationItemContent(
            mailboxType = NavType.Chart,
            icon = Icons.Default.BarChart,
            text = "Chart"
        ),
        NavigationItemContent(
            mailboxType = NavType.Setting,
            icon = Icons.Default.Settings,
            text = "Settings"
        )
    )

    Scaffold(
        topBar = { CalendarTopBar() },
        bottomBar = { BottomNavBar(
            navigationItemContentList = navigationItemContentList,
            onTabPressed = onTabPressed
        ) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Card(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp),
                shape = RoundedCornerShape(32.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(359.dp)
                        .background(Color(0XFFD9D9D9)),
                    contentAlignment = Alignment.Center
                ) {
                    HorizontalCalendar()
                }
            }

            Spacer(modifier = Modifier.size(11.dp))

            Card(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                shape = RoundedCornerShape(32.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(247.dp)
                        .background(Color(0XFFEFEFEF)),
                    contentAlignment = Alignment.Center
                ) {
                    EmotionChart()
                }
            }
        }

        val isVisible by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
        ){
            AnimatedVisibility(
                visible = isVisible,
                modifier = Modifier
                    .align(Alignment.BottomCenter) // 이 align 은 box scope 이기 때문에 안에서 써야 한다.
            ) {
                DetailHomeScreen(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(32.dp)) // 이게 먼저 와야함
                        .background(color = Color(0XFF9D9D9D))
                )
            }
        }
    }

}


@Composable
private fun BottomNavBar(
    navigationItemContentList: List<NavigationItemContent>,
    onTabPressed: (() -> Unit) = {},
) {
    NavigationBar(
        modifier = Modifier
            .height(92.dp),
        containerColor = Color(0XEFEFEFEF),
    ) {
        for (navItem in navigationItemContentList) {
            if (navItem.mailboxType != NavType.Edit) {
                NavigationBarItem(
                    selected = false,
                    onClick = onTabPressed ,
                    icon = {
                        Icon(
                            imageVector = navItem.icon,
                            contentDescription = navItem.text,
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                            // 여기 dp 값 임의 수정함
                        )
                    },
                )
            } else {
                NavigationBarItem(
                    selected = false,
                    onClick = onTabPressed ,
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(60.dp) // 동그란 배경의 크기 설정
                                .background(Color(0XFF414141), CircleShape), // 동그란 배경 추가
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = navItem.text,
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp),
                                tint = Color(0XEFEFEFEF)
                                // 여기 dp 값 임의 수정함
                            )
                        }
                    },
                )
            }
        }
    }

}

@Composable
fun CalendarTopBar(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "August 2023",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun CalendarScreenPreview() {
    CalendarScreen()
}

private data class NavigationItemContent(
    val mailboxType: NavType,
    val icon: ImageVector,
    val text: String
)