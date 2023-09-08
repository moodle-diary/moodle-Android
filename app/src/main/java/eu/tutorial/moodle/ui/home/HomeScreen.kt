package eu.tutorial.moodle.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorial.moodle.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import eu.tutorial.moodle.data.NavType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    val navigationItemContentList = listOf(
        NavigationItemContent(
            mailboxType = NavType.Home,
            icon = Icons.Default.Home,
            text = "Home"
        ),
        NavigationItemContent(
            mailboxType = NavType.Calender,
            icon = Icons.Default.CalendarMonth,
            text = "Calender"
        ),
        NavigationItemContent(
            mailboxType = NavType.Edit,
            icon = Icons.Rounded.Edit,
            text = "Edit"
        ),
        NavigationItemContent(
            mailboxType = NavType.Chart,
            icon = Icons.Default.BarChart,
            text = "Chart"
        ),
        NavigationItemContent(
            mailboxType = NavType.Setting,
            icon = Icons.Default.Settings,
            text = "Settings"
        )
    )

    Scaffold(
        topBar = { TopAppBar() },
        bottomBar = { BottomNavBar(navigationItemContentList = navigationItemContentList) }
    ) {innerPadding ->
        DetailHomeScreen(innerPaddingValues = innerPadding)
//        EmptyHomeScreen(innerPaddingValues = innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(){
    Box(
        modifier = Modifier
            .height(148.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "August",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    color = Color(0xFF000000),
                )
            )

            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text="31  Thursday",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = Color(0xFF000000),
                        ),
                        textAlign = TextAlign.Center
                    ) },
                modifier = Modifier,
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = "go back",
                        )
                    } },
            )


        }
    }

}

@Composable
private fun BottomNavBar(
    navigationItemContentList : List<NavigationItemContent>,
){

    NavigationBar(
        modifier = Modifier
            .height(92.dp),
        containerColor = Color(0XEFEFEFEF),
    ) {
        for(navItem in navigationItemContentList){
            if(navItem.mailboxType != NavType.Edit){
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
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
            }else{
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(60.dp) // 동그란 배경의 크기 설정
                                .background(Color(0XFF414141), CircleShape), // 동그란 배경 추가
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = navItem.text,
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp),
                                tint = Color(0XEFEFEFEF)
                                // 여기 dp 값 임의 수정함
                            )
                        }

                    },

                )
            }
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}

private data class NavigationItemContent(
    val mailboxType: NavType,
    val icon: ImageVector,
    val text: String
)