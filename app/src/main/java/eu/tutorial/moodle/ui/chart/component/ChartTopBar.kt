package eu.tutorial.moodle.ui.chart.component

import YearMonthDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.AppViewModelProvider
import eu.tutorial.moodle.ui.chart.ChartViewModel
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.contentBlack

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChartTopBar(
    viewModel: ChartViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    Box(
        modifier = Modifier
            .height(108.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundGray)
                .padding(start = 50.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "월간 분석",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = contentBlack,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                )
            )
            YearMonthDialog(
                viewModel = viewModel
            )
        }

    }
}


@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TopBarPreview() {
    ChartTopBar()
}