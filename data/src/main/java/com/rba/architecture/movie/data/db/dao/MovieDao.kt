package com.rba.architecture.movie.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rba.architecture.movie.data.db.model.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    suspend fun getMovieList(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(vararg movieEntity: MovieEntity)
}