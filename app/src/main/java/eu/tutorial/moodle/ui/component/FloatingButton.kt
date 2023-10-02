package eu.tutorial.moodle.ui.component

import android.util.Log
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import eu.tutorial.moodle.ui.navigation.PostDestination

@Composable
fun FloatingButton(
    navController : NavHostController,
    isVisible: Boolean
){
    if(isVisible){
        FloatingActionButton(
            shape = CircleShape,
            onClick = {
                navController.navigate(PostDestination.route)
                Log.d("tag", PostDestination.route) },
            contentColor = Color.White,
            containerColor = Color(0XFF414141),
            modifier = Modifier
                .offset(y = 50.dp)
                .size(60.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit"
            )
        }
    }
}
