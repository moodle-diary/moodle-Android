package eu.tutorial.moodle.ui.comment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.ui.theme.poppins

@Composable
fun CommentBox(
    comment: String
) {
    var isTrashIconVisible by remember { mutableStateOf(true) } // 휴지통 아이콘 가시성 상태 변수

    Card(
        modifier = Modifier
            .padding(10.dp, 12.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0XFF363637),
            contentColor = Color(0XFFDFDFDF)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp, 26.dp)
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().height(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "12.10.23",
                    fontSize = 14.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color(0XFF888888)
                )
                if (isTrashIconVisible) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete",
                        tint = Color(0XFF888888),
                        modifier = Modifier
                            .size(18.dp)
                            .clickable{isTrashIconVisible = false}
                    )
                } else{
                    Text(
                        text = "정말 삭제하시겠습니까?",
                        fontSize = 14.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable{isTrashIconVisible = true},
                        color = Color(0XFF888888)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = comment,
                    fontSize = 16.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
