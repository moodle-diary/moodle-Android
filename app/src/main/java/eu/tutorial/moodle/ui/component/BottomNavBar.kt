package eu.tutorial.moodle.ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import eu.tutorial.moodle.data.navigation.navigationItemContentList
import eu.tutorial.moodle.ui.home.TopAppBar
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.navItemBackground

@Composable
fun BottomNavBar(
    navController: NavHostController,
    isVisible : Boolean = false
) {

    if(!isVisible)   {
        BottomAppBar(
            modifier = Modifier
                .padding(bottom = 12.dp, start = 8.dp, end = 8.dp)
                .height(68.dp)
                .clip(shape = RoundedCornerShape(18.dp))
                .fillMaxSize(),
            containerColor = backgroundGray,
        ) {
            for (navItem in navigationItemContentList) {
                if (navItem.text == "Post"){
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate(navItem.route) },
                        icon = {
                            Surface(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(RoundedCornerShape(6.dp)),
                                color = navItemBackground
                            ) {
                                Icon(
                                    imageVector = navItem.icon,
                                    contentDescription = navItem.text,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .padding(6.dp),
                                    tint = backgroundGray,
                                    // 여기 dp 값 임의 수정함
                                )
                            }
                        },
                    )

                } else{
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate(navItem.route) },
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = navItem.text,
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp),
                                tint = contentBlack
                                // 여기 dp 값 임의 수정함
                            )
                        },
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TopBarPreview(){
    var navController = rememberNavController()
    BottomNavBar(navController = navController)
}
