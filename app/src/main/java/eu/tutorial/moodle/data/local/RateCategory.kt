package eu.tutorial.moodle.data.local

import androidx.compose.ui.graphics.Color
import eu.tutorial.moodle.ui.theme.greatGreen
import eu.tutorial.moodle.ui.theme.mainOrange
import eu.tutorial.moodle.ui.theme.nothingGray

val rateCategory = listOf<RateCategory>(
    RateCategory(
        "감정 성찰한 날",
        mainOrange
    ),
    RateCategory(
        "유쾌한 날",
        greatGreen
    ),
    RateCategory(
        "미기록",
        nothingGray
    ),
)

data class RateCategory(
    val textValue: String,
    val boxColor: Color
)