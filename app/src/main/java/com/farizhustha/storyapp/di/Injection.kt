package com.farizhustha.storyapp.di

import android.content.Context
import com.farizhustha.storyapp.data.StoryRepository
import com.farizhustha.storyapp.database.StoryDatabase
import com.farizhustha.storyapp.service.local.UserPreferences
import com.farizhustha.storyapp.service.remote.ApiConfig
import com.farizhustha.storyapp.ui.main.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): StoryRepository {

        val database = StoryDatabase.getDatabase(context)
        val pref = UserPreferences.getInstance(context.dataStore)
        val token = runBlocking {
            pref.getUserToken().first()
        }
        val apiService = ApiConfig.getApiService(token)
        return StoryRepository(database, apiService)

    }
}