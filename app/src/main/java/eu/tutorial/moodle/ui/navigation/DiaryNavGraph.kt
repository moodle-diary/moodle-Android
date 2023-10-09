package eu.tutorial.moodle.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.FabPosition
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
import eu.tutorial.moodle.ui.component.BottomNavBar
import eu.tutorial.moodle.ui.component.FloatingButton
import eu.tutorial.moodle.ui.home.DetailHomeScreen
import eu.tutorial.moodle.ui.home.TopAppBar
import eu.tutorial.moodle.ui.post.PostEmotionScreen

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
                bottomBar = { BottomNavBar(
                    navController = navController
                ) },
                floatingActionButtonPosition = FabPosition.Center,
                floatingActionButton = {
                    FloatingButton(
                        navController = navController,
                        isVisible = true
                    )
                }
            ) { innerPadding ->
                    DetailHomeScreen(innerPaddingValues = innerPadding)
//                EmptyHomeScreen(innerPaddingValues = innerPadding)
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
                    ) }
                },
                floatingActionButtonPosition = FabPosition.Center,
                floatingActionButton = {
                    if (showCommentScreen) {
                        FloatingButton(
                            navController = navController,
                            isVisible = !showCommentScreen
                        )
                    } else {
                        FloatingButton(
                            navController = navController,
                            isVisible = !visibleMore
                        ) }

                }
            ) { innerPadding ->

                CalendarMainCard(
                    innerPadding = innerPadding,
                    visibleMore = visibleMore,
                    changeVisibleMore = {visibleMore = !visibleMore},
                    showCommentScreen = showCommentScreen,
                    setShowCommentScreen = { showCommentScreen = it },
                    onCloseClick = {showCommentScreen = false}
                )
            }
        }

        composable(route = PostDestination.route) {
            PostEmotionScreen(
                navController = navController
            )
        }
    }
}

