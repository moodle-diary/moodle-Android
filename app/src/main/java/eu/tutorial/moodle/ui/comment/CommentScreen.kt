package eu.tutorial.moodle.ui.comment

import CommentButton
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.tutorial.moodle.ui.AppViewModelProvider
import eu.tutorial.moodle.ui.comment.component.CommentAddBox
import eu.tutorial.moodle.ui.comment.component.CommentBox
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommentScreen(
    viewModel: CommentViewModel = viewModel(factory = AppViewModelProvider.Factory),
    selectedDate: LocalDate,
) {
    var isAddingComment by remember { mutableStateOf(false) }
    val commentList = viewModel.commentList
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(isAddingComment) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                // 데이터베이스 쿼리를 비동기적으로 수행
                viewModel.getComment(selectedDate.toString())
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier.weight(1f)
        )

        commentList.map {
            CommentBox(
                comment = it.comment,
                commentId = it.id,
                selectedDate = selectedDate.toString(),
                deleteComment = viewModel::deleteComment
            ) {
                isAddingComment = !isAddingComment
            }
        }

        CommentAddBox(
            viewModel = viewModel,
            isAddingComment = isAddingComment,
            selectedDate = selectedDate.toString()
        ) {
            isAddingComment = it
        }

        CommentButton {
            isAddingComment = it
        }

        Spacer(
            modifier = Modifier.height(77.dp)
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun DetailScreenPreview() {
//    CommentScreen(onCloseClick = {}, comments = comments)
}
