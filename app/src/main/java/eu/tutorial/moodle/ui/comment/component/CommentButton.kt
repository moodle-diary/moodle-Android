package eu.tutorial.moodle.ui.comment.component

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
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp, top = 7.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(
            onClick = { changeAddComment(true) },
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundGray
            ),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .width(170.dp)
                .height(46.dp)
        ) {
            Text(
                text = "댓글 쓰기",
                fontSize = 16.sp,
                fontFamily = poppins,
                color = contentBlack
            )
        }
    }
}
