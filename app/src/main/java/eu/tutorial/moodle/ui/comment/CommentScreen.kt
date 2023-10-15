package eu.tutorial.moodle.ui.comment

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.tutorial.moodle.data.local.comments
import eu.tutorial.moodle.ui.AppViewModelProvider
import eu.tutorial.moodle.ui.theme.poppins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommentScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    innerPaddingValues: PaddingValues = PaddingValues(0.dp),
    onCloseClick: () -> Unit,
    comments: List<String>,
    viewModel: CommentViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var isAddingComment by remember { mutableStateOf(false) }

    val commentUiState = viewModel.commentUiState
    val commentList = viewModel.commentList

    val coroutineScope = rememberCoroutineScope()

    // TODO 달력 클릭시 해당 날짜로 변경
    LaunchedEffect(isAddingComment){
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                // 데이터베이스 쿼리를 비동기적으로 수행
                viewModel.getComment(LocalDate.now().toString())
            }
        }
    }

    Scaffold(
        containerColor = Color.Black.copy(alpha = 0.9f),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        bottomBar = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .padding(top = 16.dp, bottom = 24.dp, start = 12.dp, end = 12.dp),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0XFF363637)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 12.dp, end = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { onCloseClick() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )

                    ) {
                        Text(
                            text = "Close",
                            fontSize = 16.sp,
                            fontFamily = poppins,
                            color = Color(0XFFDFDFDF)
                        )
                    }
                    Button(
                        onClick = { isAddingComment = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0XFF151515)
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "Add Comments",
                            fontSize = 16.sp,
                            fontFamily = poppins,
                            color = Color(0XFFDFDFDF)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(bottom = innerPadding.calculateBottomPadding(),)
                .fillMaxSize()
                .then(Modifier.padding(innerPaddingValues))
                .then(Modifier.verticalScroll(rememberScrollState()))
        ) {
            Spacer(
                modifier = Modifier.weight(1f)
            )

            for (comment in commentList) {
                CommentBox(comment = comment.comment)
            }

            if (isAddingComment) {
                Button(
                    // TODO : 오늘 날짜가 아닌 선택된 날짜로 수정
                    onClick = {
                        coroutineScope.launch {
                            viewModel.saveComment( LocalDate.now().toString())
                            isAddingComment = false
                            viewModel.updateCommentUiState(
                                commentUiState.commentDetails.copy(
                                    comment = ""
                                )
                            )
                        }
                    }
                ){
                    Text( text = "저장하기")
                }

                BasicTextField(
                    modifier = Modifier
                        .padding(10.dp, 12.dp)
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(32.dp)),
                    value = commentUiState.commentDetails.comment,
                    onValueChange = {viewModel.updateCommentUiState(
                        commentUiState.commentDetails.copy(
                            comment = it
                        )
                    )},
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
                                .padding(20.dp, 26.dp),
                        ) {
                            if (commentUiState.commentDetails.comment == "") {
                                Text(
                                    text = "Enter comment..",
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
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun DetailScreenPreview(){
//    CommentScreen(onCloseClick = {}, comments = comments)
}