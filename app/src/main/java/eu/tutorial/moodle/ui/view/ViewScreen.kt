package eu.tutorial.moodle.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import eu.tutorial.moodle.ui.component.IconsComponent
import eu.tutorial.moodle.ui.component.NotesComponent
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.contentGray
import eu.tutorial.moodle.ui.theme.poppins
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
    showCommentScreen: Boolean,
    setShowCommentScreen: (Boolean) -> Unit,
    showViewScreen: () -> Unit,
    selectedDate: MutableState<LocalDate>,
    viewModel: DetailViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onCloseClick: () -> Unit,
) {
    val viewDate = selectedDate.value
    val diaryList = viewModel.diaryUiState

    val emotionList = viewModel.emotionUiState
    val causeList = viewModel.causesUiState
    val placeList = viewModel.placesUiState

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
            modifier = Modifier.height(82.dp),
            iconList = emotionList,
        )

        Text(
            text = "원인 아이콘",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = contentBlack,
            modifier = Modifier.padding(top = 24.dp)
        )

        IconsComponent(
            modifier = Modifier.height(82.dp),
            iconList = causeList,
            typeList = causeType
        )

        Text(
            text = "장소 아이콘",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = contentBlack,
            modifier = Modifier.padding(top = 24.dp)
        )

        IconsComponent(
            modifier = Modifier.height(82.dp),
            iconList = placeList,
            typeList = placeType
        )

        Text(
            text = "글",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = contentBlack,
            modifier = Modifier.padding(top = 24.dp)
        )

        if (diaryList.isNotEmpty()) {
            eu.tutorial.moodle.ui.home.getDiaryText(diaryList).map {
                NotesComponent(
                    text = it
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(0.dp, 4.dp)
                    .fillMaxWidth()
                    .height(82.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(containerGray),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "아직 기록이 없어요",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = contentGray
                    ),
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { setShowCommentScreen(true) },
                modifier = Modifier
                    .defaultMinSize(
                        minWidth = 1.dp,
                        minHeight = 31.dp
                    )
                    .padding(top = 24.dp, bottom = 57.dp),
                contentPadding = PaddingValues(
                    top = 5.dp,
                    bottom = 5.dp,
                    start = 17.dp,
                    end = 17.dp
                ),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.dp, Color(0XFFDFDFDF)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(
                    text = "%d Comments".format(1),
                    color = Color(0XFFDFDFDF),
                    fontFamily = poppins
                )
            }
        }

    }
    Box(
        modifier = Modifier
            .background(color = Color(0X00000000))
    ) {
        AnimatedVisibility(
            visible = showCommentScreen,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
        ) {
            CommentScreen(
                onCloseClick = {
                    setShowCommentScreen(false)
                },
                selectedDate = selectedDate.value
            )
        }
    }
}

fun getDiaryText(diaryList: List<DiaryDto>): List<String> {
    return diaryList.map { it.diaryText }
}
