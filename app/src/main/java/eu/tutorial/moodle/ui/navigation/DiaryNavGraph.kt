package eu.tutorial.moodle.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import eu.tutorial.moodle.ui.calendar.CalendarDestination
import eu.tutorial.moodle.ui.calendar.CalendarScreen
import eu.tutorial.moodle.ui.home.HomeDestination
import eu.tutorial.moodle.ui.home.HomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DiaryNavHost(
    navController : NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
    ){
        composable(route = HomeDestination.route){
            HomeScreen(
                onTabPressed = { navController.navigate(CalendarDestination.route) }

            )
        }

        composable(route = CalendarDestination.route){
            CalendarScreen(
                onTabPressed = { navController.navigate(HomeDestination.route) }
            )
        }
    }
}