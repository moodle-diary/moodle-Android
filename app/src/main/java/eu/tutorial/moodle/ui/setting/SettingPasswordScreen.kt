package eu.tutorial.moodle.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorial.moodle.R
import eu.tutorial.moodle.ui.navigation.SettingDestination
import eu.tutorial.moodle.ui.theme.backgroundGray
import eu.tutorial.moodle.ui.theme.contentBlack
import eu.tutorial.moodle.ui.theme.mainOrange


@Composable
fun SettingPasswordScreen(
    navController: NavController
) {
    var password by remember { mutableStateOf("") }

    fun onNumberButtonClick(number: Int) {
        if (password.length < 4) {
            password += number.toString()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "left",
                tint = contentBlack,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { navController.navigate(SettingDestination.route) }
            )
            Text(
                text = "비밀번호 설정",
                fontSize = 16.sp,
                color = contentBlack,
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            )
            Box(modifier = Modifier.size(28.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "앱을 켤 때 입력할 비밀번호 입니다",
                fontSize = 14.sp,
                color = contentBlack,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                PasswordTextField(
                    value = password,
                    length = 4,
                    onValueChange = { it ->
                        password = it
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(3) { rowIndex ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(3) { columnIndex ->
                        val buttonText = 3 * (rowIndex+1) - (3-columnIndex) + 1
                        Button(
                            onClick = { onNumberButtonClick(buttonText) },
                            modifier = Modifier
                                .size(70.dp),
                            colors = ButtonDefaults.buttonColors(Color.Transparent)
                        ) {
                            Text(
                                text = "%d".format(buttonText),
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = contentBlack
                            )
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                if (password.isNotEmpty()) {
                    password = password.dropLast(1) // 마지막 문자 제거
                }
            },
                modifier = Modifier
                    .defaultMinSize(
                        minWidth = 70.dp,
                        minHeight = 70.dp
                    ),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = contentBlack
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text(
                    text = "취소",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
            }
            Button(
                onClick = { onNumberButtonClick(0) },
                modifier = Modifier
                    .size(70.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text(
                    text = "0",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = contentBlack
                )
            }
            Button(
                onClick = {
                    if (password.length == 4)
                        navController.navigate("settingPassword2")
                },
                modifier = Modifier
                    .defaultMinSize(
                        minWidth = 70.dp,
                        minHeight = 70.dp
                    ),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = if (password.length == 4) mainOrange else contentBlack
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text(
                    text = "등록",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))
    }
}


@ExperimentalMaterial3Api
@Composable
@Preview
fun SettingPasswordScreenPreview() {
    val navController = rememberNavController()
    SettingPasswordScreen(navController = navController)
}
