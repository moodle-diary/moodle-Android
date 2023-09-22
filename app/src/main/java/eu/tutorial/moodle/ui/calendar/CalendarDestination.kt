package eu.tutorial.moodle.ui.calendar

import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.navigation.NavigationDestination

object CalendarDestination : NavigationDestination{
    override val route: String
        get() = "Calendar"
    override val titleRes: Int
        get() = R.string.calendar_title
}
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun CalendarTopBar(
//    currentMonth: YearMonth
//){
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(80.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "%s %d".format(currentMonth.month, currentMonth.year),
//            style = androidx.compose.ui.text.TextStyle(
//                fontSize = 24.sp,
//                fontFamily = FontFamily(Font(R.font.poppins_bold))
//            )
//        )
//    }
//}
