package eu.tutorial.moodle.ui.post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.TypeDto
import eu.tutorial.moodle.data.local.allEmojis
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

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(color = 0Xff212122))
            .clip(shape = CircleShape.copy(all = CornerSize(45.dp)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = title,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                textAlign = TextAlign.Center,
                color = Color(0XFFEDEDED)
            )
            Text(
                modifier = Modifier.padding(top = 4.dp, bottom = 28.dp),
                text = subtitle,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                textAlign = TextAlign.Center,
                color = Color(0XFFEDEDED)
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
                            Color(0XFFDFDFDF)
                        } else {
                            Color(0XFF363637)
                        }

                        IconButton(
                            onClick = {
                                if (item.typeDes != "plus")
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
                            color = Color(0XFFEDEDED)
                        )
                    }
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
            typeDes = "plus"
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
        title = "원인",
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
            typeDes = "plus"
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
        title = "장소",
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