package eu.tutorial.moodle.ui.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tutorial.moodle.data.NavType
import eu.tutorial.moodle.data.NavigationItemContent
import eu.tutorial.moodle.ui.calendar.CalendarDestination
import eu.tutorial.moodle.ui.calendar.CalendarMainCard
import eu.tutorial.moodle.ui.calendar.CalendarTopBar
import eu.tutorial.moodle.ui.home.EmptyHomeScreen
import eu.tutorial.moodle.ui.home.HomeDestination
import eu.tutorial.moodle.ui.home.TopAppBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DiaryNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
    ) {
        composable(route = HomeDestination.route) {
            Scaffold(
                topBar = { TopAppBar() },
                bottomBar = { BottomNavBar(navController = navController) },
                floatingActionButtonPosition = FabPosition.Center,
                floatingActionButton = {
                    FloatingActionButton(
                        shape = CircleShape,
                        onClick = { },
                        contentColor = Color.White,
                        containerColor = Color(0XFF414141),
                        modifier = Modifier
                            .offset(y = 50.dp)
                            .size(60.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit, contentDescription = "Edit"
                        )
                    }
                }
            ) { innerPadding ->
//        DetailHomeScreen(innerPaddingValues = innerPadding)
                EmptyHomeScreen(innerPaddingValues = innerPadding)
            }
        }

        composable(route = CalendarDestination.route) {
            Scaffold(
                bottomBar = { BottomNavBar(navController = navController) },
                floatingActionButtonPosition = FabPosition.Center,
                floatingActionButton = {
                    FloatingActionButton(
                        shape = CircleShape,
                        onClick = { },
                        contentColor = Color.White,
                        containerColor = Color(0XFF414141),
                        modifier = Modifier
                            .offset(y = 50.dp)
                            .size(60.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit, contentDescription = "Edit"
                        )
                    }
                }
            ) { innerPadding ->
                val visible = true
                CalendarMainCard(
                    innerPadding = innerPadding
                )
            }
        }
    }
}

@Composable
fun BottomNavBar(
    navController: NavHostController,
) {

    val navigationItemContentList = listOf(
        NavigationItemContent(
            mailboxType = NavType.Home,
            icon = Icons.Default.Home,
            text = "Home",
            route = HomeDestination.route
        ),
        NavigationItemContent(
            mailboxType = NavType.Calendar,
            icon = Icons.Default.CalendarMonth,
            text = "Calendar",
            route = CalendarDestination.route
        ),
//        NavigationItemContent(
//            mailboxType = NavType.Edit,
//            icon = Icons.Rounded.Edit,
//            text = "Edit",
//            route = ""
//        ),
        NavigationItemContent(
            mailboxType = NavType.Chart,
            icon = Icons.Default.BarChart,
            text = "Chart",
            route = ""
        ),
        NavigationItemContent(
            mailboxType = NavType.Setting,
            icon = Icons.Default.Settings,
            text = "Settings",
            route = ""
        )
    )


    BottomAppBar(
        modifier = Modifier
            .height(92.dp)
            .fillMaxSize(),
        containerColor = Color(0XEFEFEFEF),
    ) {
        for (navItem in navigationItemContentList) {
            if (navItem.mailboxType != NavType.Edit) {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(navItem.route) },
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
                SmallFloatingActionButton(
                    onClick = { navController.navigate(navItem.route) },
                    shape = CircleShape,
                    containerColor = Color(0XFF414141),
                    modifier = Modifier
                        .size(60.dp) // 동그란 배경의 크기 설정
                        .background(Color(0XFF414141), CircleShape), // 동그란 배경 추가
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
            }
        }
    }
}


@Preview()
@Composable
fun BottomNavBarPreview() {
    BottomNavBar(navController = rememberNavController())
}
