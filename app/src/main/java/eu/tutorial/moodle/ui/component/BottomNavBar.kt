package eu.tutorial.moodle.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import eu.tutorial.moodle.data.navigationItemContentList

@Composable
fun BottomNavBar(
    navController: NavHostController,
    isVisible : Boolean = false
) {

    if(!isVisible)   {
        BottomAppBar(
            modifier = Modifier
                .height(92.dp)
                .fillMaxSize(),
            containerColor = Color(0XEFEFEFEF),
        ) {
            for (navItem in navigationItemContentList) {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(navItem.route) },
                    icon = {
                        Icon(
                            imageVector = navItem.icon,
                            contentDescription = navItem.text,
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                            // 여기 dp 값 임의 수정함
                        )
                    },
                )
            }
        }
    }
}
