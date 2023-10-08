package eu.tutorial.moodle.ui.post

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.data.Activity
import eu.tutorial.moodle.data.People
import eu.tutorial.moodle.data.Place
import eu.tutorial.moodle.data.local.activitiesData
import eu.tutorial.moodle.data.local.peopleData
import eu.tutorial.moodle.data.local.placesData

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommonGrid(
    title: String,
    subtitle: String,
    data: List<String>,
    buttonStates: List<Boolean>,
    onItemClick: (Int) -> Unit,
    icon: ImageVector,

    // view model
    onClick: Unit,
) {
    Box(
        modifier = Modifier
            .height(520.dp)
            .background(Color(color = 0XffEFEFEF))
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
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(top = 4.dp, bottom = 28.dp),
                text = subtitle,
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                textAlign = TextAlign.Center
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
                            Color(0XFF414141)
                        } else {
                            Color(0XFFC5C1C1)
                        }

                        IconButton(
                            onClick = {
                                onItemClick(index)
                                onClick
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
                            fontWeight = FontWeight(400),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .width(65.dp)
                                .padding(top = 4.dp, bottom = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ActGrid(
    diaryUiState : DiaryUiState,
    onClick : (DiaryDetails) -> Unit,
) {
    val data = activitiesData

    val buttonStates = remember {
        mutableStateListOf<Boolean>()
    }

    for (i in data.indices) {
        buttonStates.add(false)
    }

    CommonGrid(
        title = "Activities",
        subtitle = "What did you do to spend\n" +
                "your time today?",
        data = data,
        buttonStates = buttonStates,
        icon = Icons.Default.Pets,
        onItemClick = { index ->
            // Handle item click here
            buttonStates[index] = !buttonStates[index]
        },

        // view model
        onClick = onClick(
            diaryUiState.diaryDetails.copy(
                // TODO : activityId icon descriptor id로 바꾸기
                activities = data.map { Activity(activityId = 1, activityDescription = it) }
            )
        ),
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlaceGrid(
    diaryUiState : DiaryUiState,
    onClick : (DiaryDetails) -> Unit,
) {
    val data = placesData
    val buttonStates = remember {
        mutableStateListOf<Boolean>()
    }
    for (i in data.indices) {
        buttonStates.add(false)
    }

    CommonGrid(
        title = "Places",
        subtitle = "Where did you spend your time?",
        data = data,
        buttonStates = buttonStates,
        icon = Icons.Default.Place,
        onItemClick = { index ->
            // Handle item click here
            buttonStates[index] = !buttonStates[index]
        },

        // view model
        onClick = onClick(
            diaryUiState.diaryDetails.copy(
                // TODO : placeId icon descriptor id로 바꾸기
                places = data.map { Place(placeId = 1, placeDescription = it) }
            )
        ),
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PeopleGrid(
    diaryUiState : DiaryUiState,
    onClick : (DiaryDetails) -> Unit,
) {
    val data = peopleData
    val buttonStates = remember {
        mutableStateListOf<Boolean>()
    }
    for (i in data.indices) {
        buttonStates.add(false)
    }

    CommonGrid(
        title = "People",
        subtitle = "Who did you spend time with?",
        data = data,
        buttonStates = buttonStates,
        icon = Icons.Default.People,
        onItemClick = { index ->
            // Handle item click here
            buttonStates[index] = !buttonStates[index]
        },

        // view model
        onClick = onClick(
            diaryUiState.diaryDetails.copy(
                // TODO : peopleId icon descriptor id로 바꾸기
                people = data.map { People(peopleId = 1, peopleDescription = it) }
            )
        ),
    )
}