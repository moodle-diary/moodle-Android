package eu.tutorial.moodle.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.DiaryDto
import eu.tutorial.moodle.data.TypeDto
import eu.tutorial.moodle.ui.AppViewModelProvider
import eu.tutorial.moodle.ui.comment.CommentScreen
import eu.tutorial.moodle.ui.component.EmptyNote
import eu.tutorial.moodle.ui.component.IconsComponent
import eu.tutorial.moodle.ui.component.NotesComponent
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.mainOrange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.format
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ViewScreen(
    showViewScreen: () -> Unit,
    selectedDate: MutableState<LocalDate>,
    viewModel: DetailViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onCloseClick: () -> Unit,
) {
    val viewDate = selectedDate.value

    var isDiaryExist by remember { mutableStateOf(false) }

    val causeType = viewModel.causeTypes.map {
        TypeDto(
            iconId = it.iconId,
            typeDes = it.causeType
        )
    }
    val placeType = viewModel.placesTypes.map {
        TypeDto(
            iconId = it.iconId,
            typeDes = it.placeType,
        )
    }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                // 데이터베이스 쿼리를 비동기적으로 수행
                viewModel.getDiaries(viewDate.toString())
                viewModel.getEmotions(viewDate.toString())
                viewModel.getCauses(viewDate.toString())
                viewModel.getPlaces(viewDate.toString())

                viewModel.getCauseTypes()
                viewModel.getPlaceTypes()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGray)
            .padding(start = 20.dp, end = 20.dp)
            .then(Modifier.verticalScroll(rememberScrollState()))
    ) {
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(80.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = format("%d월 %d년", viewDate.monthValue, viewDate.year),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = contentBlack
                )
                Text(
                    text = format(
                        "%d %s",
                        viewDate.dayOfMonth,
                        viewDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN)
                    ),
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = contentBlack
                )
            }
            IconButton(onClick = showViewScreen) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "close",
                    tint = contentBlack,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Text(
            text = "감정 아이콘",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = contentBlack,
            modifier = Modifier.padding(top = 24.dp)
        )

        IconsComponent(
            iconList = viewModel.emotionUiState,
        )

        Text(
            text = "원인",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = contentBlack,
            modifier = Modifier.padding(top = 24.dp)
        )

        IconsComponent(
            iconList = viewModel.causesUiState,
            typeList = causeType
        )

        Text(
            text = "장소",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = contentBlack,
            modifier = Modifier.padding(top = 24.dp)
        )

        IconsComponent(
            iconList = viewModel.placesUiState,
            typeList = placeType
        )

        Text(
            text = "나의 생각",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = contentBlack,
            modifier = Modifier.padding(top = 24.dp)
        )

        if (viewModel.diaryUiState.isNotEmpty()) {
            getDiaryText(viewModel.diaryUiState).map {
                NotesComponent(
                    text = it
                ) { exist ->
                    isDiaryExist = exist
                }
            }
        }

        EmptyNote(
            isDiaryExist = isDiaryExist
        )

        Row(
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.comment),
                contentDescription = "comment",
                modifier = Modifier
                    .width(30.dp)
                    .padding(end = 9.dp)
            )
            Text(
                text = "댓글",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                color = mainOrange,

                )
        }

        CommentScreen(
            selectedDate = selectedDate.value
        )

    }

}

fun getDiaryText(diaryList: List<DiaryDto>): List<String> {
    return diaryList.map { it.diaryText }
}
