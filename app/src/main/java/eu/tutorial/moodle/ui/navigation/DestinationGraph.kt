package eu.tutorial.moodle.ui.navigation

import eu.tutorial.moodle.R

object HomeDestination : NavigationDestination{
    override val route: String
        get() = "Diary"
    override val titleRes: Int
        get() = R.string.home_title

}

object CalendarDestination : NavigationDestination{
    override val route: String
        get() = "Calendar"
    override val titleRes: Int
        get() = R.string.calendar_title
}

object PostDestination : NavigationDestination {
    override val route: String
        get() = "PostEmo"
    override val titleRes: Int
        get() = R.string.post_emo

}

object ChartDestination : NavigationDestination{
    override val route: String
        get() = "Chart"
    override val titleRes: Int
        get() = R.string.analyze_title
}

object SettingDestination : NavigationDestination{
    override val route: String
        get() = "Setting"
    override val titleRes: Int
        get() = R.string.analyze_title
}