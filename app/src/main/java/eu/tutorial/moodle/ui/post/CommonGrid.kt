package eu.tutorial.moodle.ui.post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.TypeDto
import eu.tutorial.moodle.data.local.allEmojis
import eu.tutorial.moodle.ui.post.component.SaveTypeDialog
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.unselectedIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CommonGrid(
    title: String,
    subtitle: String,
    data: List<TypeDto>,
    buttonStates: List<Boolean>,
    onItemClick: (Int) -> Unit,
    save: (String) -> Unit,
    dialogVisible: Boolean,
    onChange: (Boolean) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(containerGray)
            .clip(RoundedCornerShape(18.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 32.dp, bottom = 28.dp),
            text = title,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            textAlign = TextAlign.Center,
            color = contentBlack
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(4)
        ) {
            itemsIndexed(data) { index, item ->
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val isClicked = buttonStates[index]

                    val backgroundColor = if (isClicked) {
                        containerGray
                    } else {
                        unselectedIndicator
                    }

                    IconButton(
                        onClick = {
                            if (item.typeDes != "추가하기")
                                onItemClick(index)
                            else
                                onChange(true)
                        },
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(backgroundColor)
                    ) {
                        allEmojis[item.iconId]?.let { painterResource(it) }?.let {
                            Image(
                                painter = it,
                                contentDescription = null,
                            )
                        }
                    }

                    Text(
                        text = item.typeDes,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(65.dp)
                            .padding(top = 4.dp, bottom = 8.dp),
                        color = contentBlack
                    )
                }
            }
        }

        SaveTypeDialog(
            save = save,
            dialogVisible = dialogVisible
        ) {
            onChange(it)
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CauseGrid(
    causeButtonStates: SnapshotStateList<Boolean>,
    viewModel: PostViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    var dialogVisible by remember { mutableStateOf(false) }
    val data = viewModel.causeTypes.map {
        TypeDto(
            iconId = it.iconId,
            typeDes = it.causeType
        )
    } + listOf(
        TypeDto(
            iconId = "none",
            typeDes = "추가하기"
        )
    )

    LaunchedEffect(dialogVisible) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                // 데이터베이스 쿼리를 비동기적으로 수행
                viewModel.getCauseTypes()
            }
        }
    }

    for (i in data.indices) {
        causeButtonStates.add(false)
    }

    CommonGrid(
        title = "무엇이 그런 감정을 느끼게 했나요?",
        subtitle = "오늘 무엇이 나를 우울 하게 했나요?",
        data = data,
        buttonStates = causeButtonStates,
        onItemClick = { index ->
            // Handle item click here
            causeButtonStates[index] = !causeButtonStates[index]
        },
        save = {
            coroutineScope.launch {
                viewModel.saveCauseType(it)
            }
        },
        dialogVisible = dialogVisible,
    ) {
        dialogVisible = it
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlaceGrid(
    placeButtonStates: SnapshotStateList<Boolean>,
    viewModel: PostViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    var dialogVisible by remember { mutableStateOf(false) }

    val data = viewModel.placesTypes.map {
        TypeDto(
            iconId = it.iconId,
            typeDes = it.placeType
        )
    } + listOf(
        TypeDto(
            iconId = "none",
            typeDes = "추가하기"
        )
    )

    LaunchedEffect(dialogVisible) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                // 데이터베이스 쿼리를 비동기적으로 수행
                viewModel.getPlaceTypes()
            }
        }
    }

    for (i in data.indices) {
        placeButtonStates.add(false)
    }

    CommonGrid(
        title = "어디서 그런 감정을 느꼈나요?",
        subtitle = "어느 곳에서 우울 했나요?",
        data = data,
        buttonStates = placeButtonStates,
        onItemClick = { index ->
            // Handle item click here
            placeButtonStates[index] = !placeButtonStates[index]
        },
        save = {
            coroutineScope.launch {
                viewModel.savePlaceType(it)
            }
        },
        dialogVisible = dialogVisible
    ) {
        dialogVisible = it
    }
}