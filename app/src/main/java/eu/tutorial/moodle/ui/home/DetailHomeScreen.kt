package eu.tutorial.moodle.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.Diary
import eu.tutorial.moodle.data.DiaryDto
import eu.tutorial.moodle.ui.AppViewModelProvider
import eu.tutorial.moodle.ui.component.EmotionComponent
import eu.tutorial.moodle.ui.component.EmotionQuadrant
import eu.tutorial.moodle.ui.component.IconsComponent
import eu.tutorial.moodle.ui.component.NotesComponent
import eu.tutorial.moodle.ui.component.PhotosComponent
import eu.tutorial.moodle.ui.theme.poppins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailHomeScreen(
    modifier: Modifier = Modifier,
    innerPaddingValues: PaddingValues = PaddingValues(0.dp),
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val diaryList = viewModel.diaryUiState

    val activityList = viewModel.activitiesUiState
    val placeList = viewModel.placesUiState
    val peopleList = viewModel.peopleUiState
    val foodList = viewModel.foodsUiState

    val imgList = viewModel.imgUiState

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit){

        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                // 데이터베이스 쿼리를 비동기적으로 수행
                viewModel.getDiaries(LocalDate.now().toString())
                viewModel.getActivities(LocalDate.now().toString())
                viewModel.getPlaces(LocalDate.now().toString())
                viewModel.getPeople(LocalDate.now().toString())
                viewModel.getFoods(LocalDate.now().toString())

                viewModel.getImg((LocalDate.now().toString()))
            }
        }

    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0XFF151515))
            .padding(innerPaddingValues)
            .verticalScroll(rememberScrollState())
    ) {

//        EmotionQuadrant()
//        Spacer(modifier = Modifier.size(12.dp))

        // TODO 이 부분 exist 속성 값 vm 에서 받아 와서 수정 Detail HomeScreen 이랑 통합
        EmotionComponent( emotion = getDiaryEmotion(diaryList) )

        Spacer(modifier = Modifier.size(12.dp))

        IconsComponent(
            activityList = activityList,
            placeList = placeList,
            peopleList = peopleList,
            foodList = foodList
        )

        Spacer(modifier = Modifier.size(32.dp))

        var isBottomSheetVisible by remember { mutableStateOf(false) }

        Button(
            onClick = { isBottomSheetVisible = true },
            modifier = Modifier
                .padding(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.journal),
                    contentDescription = "journal",
                    modifier = Modifier.padding(end = 8.dp)
                )

                Text(
                    text = "Read Journal",
                    color = Color(0XFFDFDFDF),
                    fontFamily = poppins,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }

        if (isBottomSheetVisible) {
            ModalBottomSheet(
                onDismissRequest = { isBottomSheetVisible = false },
                containerColor = Color(0XFF151515),
                modifier = Modifier
                    .fillMaxHeight(0.85f),  // 야매여서 고쳐야함
                tonalElevation = 0.dp,
                scrimColor = Color.Transparent,
            ) {
                Column {
                    Spacer(modifier = Modifier.size(12.dp))
                    
                    NotesComponent(text = getDiaryText(diaryList))
                    
                    Spacer(modifier = Modifier.size(12.dp))

                    PhotosComponent(imgList = imgList)
                }
            }
        }
        
        Spacer(modifier = Modifier.size(32.dp))

//        NotesComponent(
//            text = getDiaryText(diaryList)
//        )
//
//        Spacer(modifier = Modifier.size(12.dp))

    }
}

fun getDiaryEmotion(diaryList: List<DiaryDto>) : Int {
    var emotion = 0

    for (i in diaryList)
        emotion += i.emotions

    return emotion
}

fun getDiaryText(diaryList: List<DiaryDto>) : String {
    var result = ""

    for ( i in diaryList)
        result += i.diaryText

    return result
}
fun getDiaryEmoji(diaryList: List<Diary>): String {
    var result = ""

    return result

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun DetailScreenPreview(){
    DetailHomeScreen()
}