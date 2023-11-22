import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.chart.ChartViewModel
import eu.tutorial.moodle.ui.theme.contentBlack
import org.threeten.bp.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun YearMonthPicker(
    selectedDate: YearMonth,
    onDateSelected: (YearMonth) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "분석을 원하는 달을 선택하세요 :)",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFFDFDFDF),
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.width(120.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "minus",
                    modifier = Modifier.clickable { onDateSelected(selectedDate.minusYears(1)) },
                    tint = Color(0XFFDFDFDF)
                )
                Text(
                    text = "${selectedDate.year}년",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Color(0xFFDFDFDF),
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                    )
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "plus",
                    modifier = Modifier.clickable { onDateSelected(selectedDate.plusYears(1)) },
                    tint = Color(0XFFDFDFDF)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Row(
                modifier = Modifier.width(120.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "minus",
                    modifier = Modifier.clickable { onDateSelected(selectedDate.minusMonths(1)) },
                    tint = Color(0XFFDFDFDF)
                )
                Text(
                    text = "${selectedDate.monthValue}월",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Color(0xFFDFDFDF),
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                    )
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "plus",
                    modifier = Modifier.clickable { onDateSelected(selectedDate.plusMonths(1)) },
                    tint = Color(0XFFDFDFDF)
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun YearMonthDialog(
    viewModel: ChartViewModel
) {
    var selectedDate = viewModel.selectedDate
    val dateDialogState = rememberMaterialDialogState()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${selectedDate.value.year} ${selectedDate.value.monthValue}월",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = contentBlack,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                )
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "down",
                tint = contentBlack,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(35.dp)
                    .clickable { dateDialogState.show() }
            )
        }
    }

    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(
                text = "선택하기",
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color(0xFFDFDFDF),
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                )
            )
            negativeButton(
                text = "취소",
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFFDFDFDF),
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                )
            )
        },
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color(0XFF212122),
    ) {
        YearMonthPicker(
            selectedDate = selectedDate.value,
        ) {
            viewModel.onDateSelect(it)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DatePreview() {
//    YearMonthDialog()
}
