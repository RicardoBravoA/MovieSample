package com.rba.architecture.movie.data.service.movie

import com.rba.architecture.movie.data.api.ApiInterface
import com.rba.architecture.movie.data.api.ApiManager
import com.rba.architecture.movie.data.db.dao.MovieDao
import com.rba.architecture.movie.domain.callback.BaseCallback
import com.rba.architecture.movie.domain.model.ErrorModel
import com.rba.architecture.movie.domain.model.MovieModel
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

@RunWith(JUnit4::class)
class MovieServiceDataStoreTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiInterface: ApiInterface
    private lateinit var movieServiceDataStore: MovieServiceDataStore
    @Mock
    private lateinit var movieDao: MovieDao
    @Mock
    private lateinit var baseCallback: BaseCallback<MovieModel, ErrorModel>

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        apiInterface = ApiManager.apiManager()

        movieServiceDataStore = MovieServiceDataStore(movieDao)
    }

    @After
    fun stopServer() {
        mockWebServer.shutdown()
    }

    @Test
    @Throws(Exception::class)
    fun getMovieListTest() {

    }

}