package eu.tutorial.moodle.data.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import eu.tutorial.moodle.ui.navigation.CalendarDestination
import eu.tutorial.moodle.ui.navigation.ChartDestination
import eu.tutorial.moodle.ui.navigation.HomeDestination
import eu.tutorial.moodle.ui.navigation.PostDestination

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
    NavigationItemContent(
        mailboxType = NavType.Post,
        icon = Icons.Default.Edit,
        text = "Post",
        route = PostDestination.route
    ),
    NavigationItemContent(
        mailboxType = NavType.Chart,
        icon = Icons.Default.BarChart,
        text = "Chart",
        route = ChartDestination.route
    ),
    NavigationItemContent(
        mailboxType = NavType.Setting,
        icon = Icons.Default.Settings,
        text = "Settings",
        route = ""
    )
)
