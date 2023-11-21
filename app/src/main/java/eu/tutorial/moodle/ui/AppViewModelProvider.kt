package eu.tutorial.moodle.ui

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
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
        // Initializer for ItemEditViewModel
        // Initializer for ItemEntryViewModel
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

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.moodleApplication(): MoodleApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MoodleApplication)
