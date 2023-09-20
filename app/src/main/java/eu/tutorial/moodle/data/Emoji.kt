package eu.tutorial.moodle.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Emoji (
    @DrawableRes val emojiPaintId : Int,
    @StringRes val emojiDescriptionId : Int

    )