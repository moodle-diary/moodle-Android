package eu.tutorial.moodle.data.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItemContent(
    val mailboxType: NavType,
    val icon: ImageVector,
    val text: String,
    val route: String
)