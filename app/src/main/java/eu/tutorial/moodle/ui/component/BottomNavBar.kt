package eu.tutorial.moodle.ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.navigation.NavType
import eu.tutorial.moodle.data.navigation.NavigationItemContent
import eu.tutorial.moodle.ui.navigation.CalendarDestination
import eu.tutorial.moodle.ui.navigation.ChartDestination
import eu.tutorial.moodle.ui.navigation.HomeDestination
import eu.tutorial.moodle.ui.navigation.PostDestination
import eu.tutorial.moodle.ui.navigation.SettingDestination
import eu.tutorial.moodle.ui.theme.backgroundGray

@Composable
fun BottomNavBar(
    navController: NavHostController,
    isVisible: Boolean = false
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            mailboxType = NavType.Home,
            isSelected = navController.currentDestination?.hierarchy?.any {
                it.route == HomeDestination.route
            } == true,
            text = "Home",
            route = HomeDestination.route,
            selectedIcon = painterResource(id = R.drawable.nav_home_fill),
            unselectedIcon = painterResource(id = R.drawable.nav_home),
        ),
        NavigationItemContent(
            mailboxType = NavType.Calendar,
            isSelected = navController.currentDestination?.hierarchy?.any {
                it.route == CalendarDestination.route
            } == true,
            text = "Calendar",
            route = CalendarDestination.route,
            selectedIcon = painterResource(id = R.drawable.nav_cal_fill),
            unselectedIcon = painterResource(id = R.drawable.nav_cal),
        ),
        NavigationItemContent(
            mailboxType = NavType.Post,
            isSelected = navController.currentDestination?.hierarchy?.any {
                it.route == PostDestination.route
            } == true,
            text = "Post",
            route = PostDestination.route,
            selectedIcon = painterResource(id = R.drawable.nav_edit),
            unselectedIcon = painterResource(id = R.drawable.nav_edit),
        ),
        NavigationItemContent(
            mailboxType = NavType.Chart,
            isSelected = navController.currentDestination?.hierarchy?.any {
                it.route == ChartDestination.route
            } == true,
            text = "Chart",
            route = ChartDestination.route,
            selectedIcon = painterResource(id = R.drawable.nav_rep_fill),
            unselectedIcon = painterResource(id = R.drawable.nav_rep),
        ),
        NavigationItemContent(
            mailboxType = NavType.Setting,
            isSelected = navController.currentDestination?.hierarchy?.any {
                it.route == SettingDestination.route
            } == true,
            text = "Setting",
            route = SettingDestination.route,
            selectedIcon = painterResource(id = R.drawable.nav_set_fill),
            unselectedIcon = painterResource(id = R.drawable.nav_set),
        )
    )
    if (!isVisible) {
        BottomAppBar(
            modifier = Modifier
                .height(68.dp)
                .padding(bottom = 10.dp)
                .fillMaxSize(),
            containerColor = backgroundGray
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                for (navItem in navigationItemContentList) {
                    if (navItem.isSelected) {
                        Image(
                            painter = navItem.selectedIcon,
                            contentDescription = "image",
                            modifier = Modifier
                                .size(42.dp)
                                .clickable { navController.navigate(navItem.route) }
                        )
                    } else {
                        Image(
                            painter = navItem.unselectedIcon,
                            contentDescription = "image",
                            modifier = Modifier
                                .size(42.dp)
                                .clickable { navController.navigate(navItem.route) }
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TopBarPreview() {
    var navController = rememberNavController()
    BottomNavBar(navController = navController)
}
