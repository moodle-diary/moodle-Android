import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.poppins

@Composable
fun CommentButton(
    changeAddComment: (Boolean) -> Unit
) {
    var commentButtonState by remember { mutableStateOf("WRITE") }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp, top = 7.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val buttonContent: String = when (commentButtonState) {
            "WRITE" -> "댓글 쓰기"
            "CANCEL" -> "댓글 쓰기 취소"
            else -> "댓글 쓰기" // 기본값 설정
        }

        Button(
            onClick = {
                changeAddComment(commentButtonState == "WRITE")
                commentButtonState = if (commentButtonState == "WRITE") {
                    "CANCEL"
                } else {
                    "WRITE"
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundGray
            ),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .width(170.dp)
                .height(46.dp)
        ) {
            Text(
                text = buttonContent,
                fontSize = 16.sp,
                fontFamily = poppins,
                color = contentBlack
            )
        }
    }
}
