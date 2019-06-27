package com.rba.architecture.movie.data.db.movie

import android.content.Context
import com.rba.architecture.movie.data.db.AppDatabase
import com.rba.architecture.movie.data.util.isInternet

class MovieDataStoreFactory(private val context: Context) {

    fun create(): MovieDataStore {
        val movieDataStore: MovieDataStore
        val database: AppDatabase = AppDatabase.getDatabase(context)

        val value = if (context.isInternet()) Preference.CLOUD else Preference.DB

        if (Preference.CLOUD == value) {
            movieDataStore = MovieServiceDataStore(database.movieDao())
        } else {
            movieDataStore = MovieDbDataStore(database.movieDao())
        }

        return movieDataStore
    }

    enum class Preference {
        CLOUD, DB
    }

}