package eu.tutorial.moodle.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.tutorial.moodle.data.Diary
import eu.tutorial.moodle.ui.AppViewModelProvider
import eu.tutorial.moodle.ui.component.EmotionComponent
import eu.tutorial.moodle.ui.component.IconsComponent
import eu.tutorial.moodle.ui.component.NotesComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailHomeScreen(
    modifier: Modifier = Modifier,
    innerPaddingValues: PaddingValues = PaddingValues(0.dp),
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val diaryList = viewModel.diaryUiState

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit){

        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                // 데이터베이스 쿼리를 비동기적으로 수행
                viewModel.getDiaries(LocalDate.now().toString())
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
        // TODO 이 부분 exist 속성 값 vm 에서 받아 와서 수정 Detail HomeScreen 이랑 통합
        EmotionComponent( emotion = getDiaryEmotion(diaryList) )

        Spacer(modifier = Modifier.size(12.dp)) // Spacer 적용시 padding 보다 size 적ㅖ

        IconsComponent( emojis = emptyList() )

        Spacer(modifier = Modifier.size(12.dp))

        NotesComponent(
            text = getDiaryText(diaryList)
        )

        Spacer(modifier = Modifier.size(12.dp))

    }
}

fun getDiaryEmotion(diaryList: List<Diary>) : Int {
    var emotion = 0

    for (i in diaryList)
        emotion += i.emotions

    return emotion
}

fun getDiaryEmoji(diaryList: List<Diary>): String {
    var result = ""

    return result

}

fun getDiaryText(diaryList: List<Diary>) : String {
    var result = ""

    for ( i in diaryList)
        result += i.diaryText

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