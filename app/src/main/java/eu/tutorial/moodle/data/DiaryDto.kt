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

