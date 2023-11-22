package eu.tutorial.moodle.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import eu.tutorial.moodle.MoodleApplication
import eu.tutorial.moodle.ui.chart.ChartViewModel
import eu.tutorial.moodle.ui.comment.CommentViewModel
import eu.tutorial.moodle.ui.home.HomeViewModel
import eu.tutorial.moodle.ui.post.PostViewModel
import eu.tutorial.moodle.ui.view.DetailViewModel

object AppViewModelProvider {
    @RequiresApi(Build.VERSION_CODES.O)
    val Factory = viewModelFactory {
        initializer {
            PostViewModel(moodleApplication().container.diaryRepository)
        }

        initializer {
            HomeViewModel(moodleApplication().container.diaryRepository)
        }

        initializer {
            CommentViewModel(moodleApplication().container.diaryRepository)
        }

        initializer {
            DetailViewModel(moodleApplication().container.diaryRepository)
        }

        initializer {
            ChartViewModel(moodleApplication().container.diaryRepository)
        }
    }
}

fun CreationExtras.moodleApplication(): MoodleApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MoodleApplication)
