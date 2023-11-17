package eu.tutorial.moodle.data.navigation

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItemContent(
    val mailboxType: NavType,
    val isSelected: Boolean,
    val text: String,
    val route: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
)