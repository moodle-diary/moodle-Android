package eu.tutorial.moodle.data

// Dto 의 네이밍은 database의 colum 이름과 같아야 한다.
data class DiaryDto(
    val emotions : Int,
    val diaryText : String,
)

data class CauseDto(
    val cause : String,
)

data class PlaceDto(
    val placeDescription : String
)

data class CauseGrade(
    val cause: String,
    val cnt : Int
)

data class PlaceGrade(
    val placeDescription: String,
    val cnt : Int
)

data class CommentDto(
    val id : Int,
    val commentDate : String,
    val comment: String
)

data class DescriptionDto(
    val description : String,
    val cnt : Int
)