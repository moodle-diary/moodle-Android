package eu.tutorial.moodle.data

// Dto 의 네이밍은 database의 colum 이름과 같아야 한다.
data class DiaryDto(
    val emotions : Int,
    val diaryText : String,
)

data class ActivityDto(
    val activityDescription : String,
)


data class PlaceDto(
    val placeDescription : String
)


data class PeopleDto(
    val peopleDescription : String
)

data class FoodDto(
    val foodDescription : String
)

data class ImgDto(
    val imgUri : String
)

data class ActGrade(
    val activityDescription: String,
    val cnt : Int
)

data class PlaceGrade(
    val placeDescription: String,
    val cnt : Int
)

data class PeopleGrade(
    val peopleDescription: String,
    val cnt : Int
)

data class FoodGrade(
    val foodDescription: String,
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