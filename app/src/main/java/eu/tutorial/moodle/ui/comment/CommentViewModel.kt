package eu.tutorial.moodle.ui.comment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import eu.tutorial.moodle.data.Comment
import eu.tutorial.moodle.data.DiaryRepository

class CommentViewModel (private val diaryRepository: DiaryRepository) : ViewModel(){
    // 초기값 할당해 주어야 합니다.
    var commentUiState by mutableStateOf(CommentUiState())
        private set

    // TODO have to impl valid
    fun updateCommentUiState(commentDetails: CommentDetails) {
        commentUiState = CommentUiState(
            commentDetails = commentDetails,
            valid = true
        )
    }

    suspend fun saveComment() {
        diaryRepository.insertComment(
            commentUiState.commentDetails.toComment()
        )
    }
}

data class CommentUiState(
    val commentDetails: CommentDetails = CommentDetails(),
    val valid : Boolean = false,
)

data class CommentDetails(
    val commentDate : String = "",
    val comment : String = "",
)

fun CommentDetails.toComment(): Comment = Comment(
    commentDate = commentDate,
    comment = comment,
)

fun Comment.toCommentDetails() : CommentDetails = CommentDetails(
    commentDate = commentDate,
    comment = comment
)