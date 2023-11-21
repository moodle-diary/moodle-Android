package eu.tutorial.moodle.ui.home.component

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.TypeDto
import eu.tutorial.moodle.ui.component.IconsComponent
import eu.tutorial.moodle.ui.component.NotesComponent
import eu.tutorial.moodle.ui.home.HomeViewModel
import eu.tutorial.moodle.ui.home.getDiaryText
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.contentGray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    visibility: Boolean,
    viewModel: HomeViewModel,
    onChange: (Boolean) -> Unit
) {
    if (visibility) {
        val coroutineScope = rememberCoroutineScope()
        val diaryList = viewModel.diaryUiState
        val causeList = viewModel.causeUiState
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


        LaunchedEffect(Unit) {
            coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    // 데이터베이스 쿼리를 비동기적으로 수행

                    viewModel.getDiaries(LocalDate.now().toString())
                    viewModel.getCauses(LocalDate.now().toString())
                    viewModel.getPlaces(LocalDate.now().toString())

                    viewModel.getCauseTypes()
                    viewModel.getPlaceTypes()
                }
            }
        }

        val scrollState = rememberScrollState()

        ModalBottomSheet(
            onDismissRequest = { onChange(false) },
            containerColor = backgroundGray,
            modifier = Modifier
                .fillMaxHeight(0.85f),  // 야매여서 고쳐야함
            tonalElevation = 0.dp,
            scrimColor = Color.Transparent,
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .verticalScroll(scrollState),
            ) {
                Spacer(modifier = Modifier.size(12.dp))

                Text(
                    text = "원인 아이콘",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = contentBlack
                )

                IconsComponent(
                    modifier = Modifier.height(82.dp),
                    iconList = causeList,
                    typeList = causeType,
                )

                Spacer(modifier = Modifier.size(46.dp))

                Text(
                    text = "장소 아이콘",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = contentBlack
                )

                IconsComponent(
                    modifier = Modifier.height(82.dp),
                    iconList = placeList,
                    typeList = placeType
                )

                Spacer(
                    modifier = Modifier.size(43.dp)
                )

                Text(
                    text = "생각",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = contentBlack
                )

                Log.d("testing", diaryList.toString())

                if (diaryList.isNotEmpty()) {
                    getDiaryText(diaryList).map {
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
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = contentGray
                            ),
                        )
                    }
                }

                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    }
}