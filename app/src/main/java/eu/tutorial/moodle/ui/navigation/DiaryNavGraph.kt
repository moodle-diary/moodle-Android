package eu.tutorial.moodle.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import eu.tutorial.moodle.ui.calendar.CalendarMainCard
import eu.tutorial.moodle.ui.chart.ChartScreen
import eu.tutorial.moodle.ui.chart.component.ChartTopBar
import eu.tutorial.moodle.ui.component.BottomNavBar
import eu.tutorial.moodle.ui.home.HomeScreen
import eu.tutorial.moodle.ui.home.component.TopAppBar
import eu.tutorial.moodle.ui.post.PostScreen
import eu.tutorial.moodle.ui.setting.SettingAlarmScreen
import eu.tutorial.moodle.ui.setting.SettingPasswordScreen
import eu.tutorial.moodle.ui.setting.SettingPasswordScreen2
import eu.tutorial.moodle.ui.setting.SettingScreen

@OptIn(ExperimentalMaterial3Api::class)
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
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
            ) { innerPadding ->
                HomeScreen(innerPaddingValues = innerPadding, navController = navController)
            }
        }

        composable(route = CalendarDestination.route) {

            var visibleMore by remember { mutableStateOf(false) }
            var showCommentScreen by remember { mutableStateOf(false) }

            Scaffold(
                bottomBar = {
                    if (showCommentScreen) {
                        BottomNavBar(navController = navController, isVisible = showCommentScreen)
                    } else {
                        BottomNavBar(
                            navController = navController,
                            isVisible = visibleMore
                        )
                    }
                },
            ) { innerPadding ->

                CalendarMainCard(
                    innerPadding = innerPadding,
                    visibleMore = visibleMore,
                    showViewScreen = { visibleMore = !visibleMore }
                )
            }
        }

        composable(route = PostDestination.route) {
            PostScreen(
                navController = navController
            )
        }

        composable(route = ChartDestination.route) {
            Scaffold(
                topBar = { ChartTopBar() },
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
            ) { innerPadding ->
                ChartScreen(innerPaddingValues = innerPadding, navController = navController)
            }
        }

        composable(route = SettingDestination.route) {
            Scaffold(
                bottomBar = {
                    BottomNavBar(
                        navController = navController
                    )
                },
            ) { innerPadding ->
                SettingScreen(innerPaddingValues = innerPadding, navController = navController)
            }
        }

        composable("settingAlarm") {
            SettingAlarmScreen(navController)
        }
        composable("settingPassword") {
            SettingPasswordScreen(navController)
        }
        composable("settingPassword2") {
            SettingPasswordScreen2(navController)
        }
    }
}

