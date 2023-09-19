package eu.tutorial.moodle.ui.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import eu.tutorial.moodle.data.NavType
import eu.tutorial.moodle.ui.calendar.CalendarDestination
import eu.tutorial.moodle.ui.navigation.NavigationDestination
import java.time.LocalDate

object HomeDestination : NavigationDestination{
    override val route: String
        get() = "Diary"
    override val titleRes: Int
        get() = R.string.home_title

}
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(){
    val today = LocalDate.now()

    val currentMonth = today.month
    val currentDate = today.dayOfMonth
    val currentDay = today.dayOfWeek
    Box(
        modifier = Modifier
            .height(148.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = currentMonth.toString(),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF000000),
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                )
            )

            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text="%s  %s".format(currentDate, currentDay),
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = Color(0xFF000000),
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            ),
                        ),
                        textAlign = TextAlign.Center
                    ) },
                modifier = Modifier,
            )

        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TopBarPreview(){
    TopAppBar()
}