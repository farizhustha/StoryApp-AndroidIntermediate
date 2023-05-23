package com.farizhustha.storyapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.farizhustha.storyapp.di.Injection
import com.farizhustha.storyapp.service.local.UserPreferences
import com.farizhustha.storyapp.ui.auth.signin.SignInViewModel
import com.farizhustha.storyapp.ui.main.MainViewModel
import com.farizhustha.storyapp.ui.story.StoryViewModel
import com.farizhustha.storyapp.ui.story.detail.DetailStoryViewModel
import com.farizhustha.storyapp.ui.story.home.HomeStoryViewModel
import com.farizhustha.storyapp.ui.story.maps.MapsViewModel

class ViewModelFactory(
    private val pref: UserPreferences? = null,
    private val context: Context? = null
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (pref != null) {
            if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
                return SignInViewModel(pref) as T
            } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(pref) as T
            } else if (modelClass.isAssignableFrom(StoryViewModel::class.java)) {
                return StoryViewModel(pref) as T
            } else if (modelClass.isAssignableFrom(DetailStoryViewModel::class.java)) {
                return DetailStoryViewModel(pref) as T
            } else if (modelClass.isAssignableFrom(MapsViewModel::class.java)) {
                return MapsViewModel(pref) as T
            }
        } else if (context != null) {
            if (modelClass.isAssignableFrom(HomeStoryViewModel::class.java)) {
                return HomeStoryViewModel(Injection.provideRepository(context)) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}