package eu.tutorial.moodle

import android.app.Application
import eu.tutorial.moodle.data.AppContainer
import eu.tutorial.moodle.data.AppDataContainer

class MoodleApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }


}