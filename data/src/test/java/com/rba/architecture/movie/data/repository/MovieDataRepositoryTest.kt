package com.rba.architecture.movie.data.repository

import com.rba.architecture.movie.data.db.movie.MovieDataStore
import com.rba.architecture.movie.data.db.movie.MovieDataStoreFactory
import com.rba.architecture.movie.domain.callback.BaseCallback
import com.rba.architecture.movie.domain.model.ErrorModel
import com.rba.architecture.movie.domain.model.MovieModel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDataRepositoryTest {

    @Mock
    private lateinit var movieDataStoreFactory: MovieDataStoreFactory
    @Mock
    private lateinit var movieDataStore: MovieDataStore
    @Mock
    private lateinit var baseCallback: BaseCallback<MovieModel, ErrorModel>
    private lateinit var movieDataRepository: MovieDataRepository

    @Before
    fun setUp() {
        movieDataRepository = MovieDataRepository(movieDataStoreFactory)
    }

    @ExperimentalCoroutinesApi
    @Test
    @Throws(Exception::class)
    fun getMovieListTest() = runBlockingTest {

        Assert.assertNotNull(movieDataStore)

        GlobalScope.launch {
            movieDataRepository.getMovieList(baseCallback)
        }

    }

}