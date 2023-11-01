package eu.tutorial.moodle.ui.post

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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.data.local.activitiesData
import eu.tutorial.moodle.data.local.placesData
import kotlinx.coroutines.launch

@Composable
fun CommonGrid(
    title: String,
    subtitle: String,
    data: List<String>,
    buttonStates: List<Boolean>,
    onItemClick: (Int) -> Unit,
    icon: ImageVector,
    save : (String) -> Unit,
) {

    var dialogVisible by remember { mutableStateOf(false) }

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
                                if(item != "plus")
                                    onItemClick(index)
                                else
                                    dialogVisible = true
                            },
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(backgroundColor)
                        ) {
                            Icon(
                                icon,
                                contentDescription = "place"
                            )
                        }
                        Text(
                            text = item,
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

        if (dialogVisible){
            SaveTypeDialog(
                save = save
            ){
                visible -> dialogVisible = visible
            }
        }
    }
}

@Composable
fun CauseGrid(
    causeButtonStates : SnapshotStateList<Boolean>,
    viewModel : PostViewModel,
) {
    val coroutineScope = rememberCoroutineScope()

    val data = activitiesData
    for (i in data.indices) {
        causeButtonStates.add(false)
    }

    CommonGrid(
        title = "원인",
        subtitle = "오늘 무엇이 나를 우울 하게 했나요?",
        data = data,
        buttonStates = causeButtonStates,
        icon = Icons.Default.Pets,
        onItemClick = { index ->
            // Handle item click here
            causeButtonStates[index] = !causeButtonStates[index]
        },
        save = { it ->
            coroutineScope.launch {
                viewModel.saveCauseType(it)
            }
        }
    )
}

@Composable
fun PlaceGrid(
    placeButtonStates : SnapshotStateList<Boolean>,
    viewModel: PostViewModel,
) {
    val data = placesData
    val coroutineScope = rememberCoroutineScope()


    for (i in data.indices) {
        placeButtonStates.add(false)
    }

    CommonGrid(
        title = "장소",
        subtitle = "어느 곳에서 우울 했나요?",
        data = data,
        buttonStates = placeButtonStates,
        icon = Icons.Default.Place,
        onItemClick = { index ->
            // Handle item click here
            placeButtonStates[index] = !placeButtonStates[index]
        },
        save = { it ->
            coroutineScope.launch {
                viewModel.savePlaceType(it)
            }
        }

    )
}