package eu.tutorial.moodle.data

// Dto 의 네이밍은 database의 colum 이름과 같아야 한다.
data class DiaryDto(
    val time: String,
    val diaryText: String,
)

data class IconDto(
    val iconDescription: String,
)

data class EmotionGrade(
    val emotion: String,
    val cnt: Int
)

data class CauseGrade(
    val cause: String,
    val cnt: Int
)

data class TypeDto(
    val iconId: String,
    val typeDes: String
)

data class CauseTypeDto(
    val causeType: String,
    val iconId: String = "default"
)

data class PlaceGrade(
    val placeDescription: String,
    val cnt: Int
)

data class PlaceTypeDto(
    val placeType: String,
    val iconId: String = "default",
)

data class CommentDto(
    val id: Int,
    val commentDate: String,
    val comment: String
)

data class DescriptionDto(
    val description: String,
    val cnt: Int
)

data class ButtonItem(
    val index: Int,
    val label: String,
)