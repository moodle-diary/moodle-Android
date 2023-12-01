package eu.tutorial.moodle.ui.post.component

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.containerGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.contentGray
import eu.tutorial.moodle.ui.theme.mainOrange

@Composable
fun EmojiTypeDialog(
    categoryList: Map<String, Int>,
    save: (String, String) -> Unit,
    dialogVisible: Boolean,
    visibleChange: (Boolean) -> Unit,
) {
    if (dialogVisible) {
        var text by remember { mutableStateOf("") }
        var iconId by remember { mutableStateOf("") }

        Dialog(
            onDismissRequest = { visibleChange(false) }
        ) {
            Column(
                modifier = Modifier
                    .width(336.dp)
                    .height(625.dp)
                    .background(color = backgroundGray, shape = RoundedCornerShape(size = 18.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.size(30.dp))
                // TODO : 여기 아무것도 없는 걸로
                Image(
                    painter = painterResource(
                        id = categoryList[iconId] ?: R.drawable.question
                    ),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(44.dp)
                )

                Spacer(modifier = Modifier.size(17.dp))

                BasicTextField(
                    value = text,
                    onValueChange = { newText ->
                        if (newText.length < 6) text = newText
                    },
                    singleLine = true,
                    modifier = Modifier
                        .width(100.dp)
                        .height(29.dp)
                        .border(
                            width = 1.dp,
                            color = mainOrange,
                            shape = RoundedCornerShape(size = 14.5.dp)
                        )
                        .background(
                            color = containerGray,
                            shape = RoundedCornerShape(size = 14.5.dp)
                        )
                        .padding(15.dp, 5.dp),
                    decorationBox = { innerTextField ->
                        if (text.isEmpty()) {
                            Text(
                                text = "이름 입력",
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = contentBlack,
                            )
                        }
                        innerTextField()
                    }
                )

                Spacer(modifier = Modifier.size(40.dp))

                Text(
                    text = "이모티콘 선택",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = contentGray,

                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.size(8.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .width(281.dp)
                        .height(355.dp)
                        .background(
                            color = containerGray,
                            shape = RoundedCornerShape(size = 18.dp)
                        )
                        .padding(start = 27.dp, end = 27.dp),
                    contentPadding = PaddingValues(top = 20.dp)
                ) {
                    items(categoryList.values.toList()) { item ->
                        IconButton(
                            onClick = {
                                iconId = categoryList.entries
                                    .find {
                                        it.value == item
                                    }?.key ?: ""
                            },
                            modifier = Modifier
                                .size(50.dp)
                        ) {
                            Image(
                                painter = painterResource(id = item),
                                modifier = Modifier.size(44.dp),
                                contentDescription = "cause"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

                Row {
                    Button(
                        onClick = { visibleChange(false) },
                        colors = ButtonDefaults.buttonColors(backgroundGray)
                    ) {
                        Text(
                            text = "취소",
                            color = contentBlack
                        )
                    }
                    Button(
                        onClick = {
                            if (text != "") {
                                save(text, iconId)
                                visibleChange(false)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerGray),
                    ) {
                        Text(
                            text = "저장하기",
                            color = contentBlack
                        )
                    }
                }
            }
        }
    }
}
