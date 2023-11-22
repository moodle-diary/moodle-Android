package eu.tutorial.moodle.ui.comment.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.ui.comment.CommentViewModel
import eu.tutorial.moodle.ui.theme.poppins
import kotlinx.coroutines.launch

@Composable
fun CommentAddBox(
    viewModel: CommentViewModel,
    isAddingComment: Boolean,
    selectedDate: String,
    changeIsAddComment: (Boolean) -> Unit
) {
    if (isAddingComment) {

        val commentUiState = viewModel.commentUiState
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 12.dp, start = 10.dp, end = 10.dp)
                .height(160.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(32.dp))
                .background(Color(0XFFEDEDED)),
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 20.dp, start = 18.dp, end = 16.dp)
                    .fillMaxWidth()
                    .height(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "24년 12월 23일",
                    fontSize = 12.sp,
                    fontFamily = poppins,
                    color = Color(0XFF414141)
                )
                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.saveComment(selectedDate)
                            changeIsAddComment(false)
                            viewModel.updateCommentUiState(
                                commentUiState.commentDetails.copy(
                                    comment = ""
                                )
                            )
                        }
                    }
                ) {
                    Text(
                        text = "저장하기",
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                }
            }

            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(32.dp)),
                value = commentUiState.commentDetails.comment,
                onValueChange = {
                    viewModel.updateCommentUiState(
                        commentUiState.commentDetails.copy(
                            comment = it
                        )
                    )
                },
                singleLine = false,
                textStyle = LocalTextStyle.current.copy(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0XFFEDEDED))
                            .border(
                                width = 1.dp,
                                color = Color(0XFFEDEDED),
                                shape = RoundedCornerShape(size = 32.dp)
                            )
                            .padding(20.dp, 12.dp),
                    ) {
                        if (commentUiState.commentDetails.comment == "") {
                            Text(
                                text = "일기에 코멘트를 남겨봐요!",
                                fontSize = 16.sp,
                                color = Color.LightGray
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}