package eu.tutorial.moodle

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import eu.tutorial.moodle.ui.navigation.BottomNavBar
import eu.tutorial.moodle.ui.navigation.DiaryNavHost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodleApp(
    navController: NavHostController = rememberNavController()
) {
    DiaryNavHost(navController = navController)
}