package eu.tutorial.moodle.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tutorial.moodle.ui.component.EmotionComponent
import eu.tutorial.moodle.ui.component.IconsComponent
import eu.tutorial.moodle.ui.component.NotesComponent


@Composable
fun DetailHomeScreen(
    modifier: Modifier = Modifier,
    innerPaddingValues: PaddingValues = PaddingValues(0.dp),
){
    Column(
        modifier = modifier
            .padding(innerPaddingValues)
            .verticalScroll( rememberScrollState())
    ) {

        Spacer(
            modifier = Modifier.size(50.dp)
        )

        // TODO 이 부분 exist 속성 값 vm 에서 받아 와서 수정 Detail HomeScreen 이랑 통합
        EmotionComponent( exist = true )

        Spacer(modifier = Modifier.size(12.dp)) // Spacer 적용시 padding 보다 size 적ㅖ

        IconsComponent( exist = true )

        Spacer(modifier = Modifier.size(12.dp))

        NotesComponent( exist = true )

        Spacer(modifier = Modifier.size(12.dp))

    }
}


@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun DetailScreenPreview(){
    DetailHomeScreen()
}