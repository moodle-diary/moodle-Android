package eu.tutorial.moodle.data.navigation

import android.media.Image
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItemContent(
    val mailboxType: NavType,
    val icon: ImageVector,
    val text: String,
    val route: String
)