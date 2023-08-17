package com.ahmedmhassaan.data.api

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.ahmedmhassaan.data.MockResponseFileReader
import com.ahmedmhassaan.data.enqueueResponse
import com.ahmedmhassaan.data.local.database.ArticlesDao
import com.ahmedmhassaan.data.local.database.NewsDatabase
import com.ahmedmhassaan.data.model.ArticlesResponse
import com.ahmedmhassaan.data.remote.adapters.ApiCallAdapterFactory
import com.ahmedmhassaan.data.remote.api.ArticleService
import com.ahmedmhassaan.data.remote.datasources.ArticlesPagingDataSource
import com.ahmedmhassaan.data.remote.datasources.RemoteArticlesDataSource
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class ArticlesServiceTest {

//    private val mockWebServer = MockWebServer()

    @get:Rule
    val hiltRul = HiltAndroidRule(this)

    private lateinit var context: Context

    lateinit var topHeadlinesFile: InputStream
    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()


    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ApiCallAdapterFactory())
        .build()
        .create(ArticleService::class.java)

    @Inject
    @Named("test_database")
    lateinit var database: NewsDatabase

    lateinit var dao: ArticlesDao

    private lateinit var articlesDataSource: RemoteArticlesDataSource


    @Before
    fun setup() {
        hiltRul.inject()
        MockitoAnnotations.initMocks(this)
        context = ApplicationProvider.getApplicationContext<Context?>().applicationContext
        dao = database.articlesDao()
        articlesDataSource = RemoteArticlesDataSource(api, dao)

//        topHeadlinesFilePath = context.resources.openRawResource(R.raw.top_headlines)
//        topHeadlinesFilePath = "android.resource://com.ahmedmhassaan.data/raw/top_headlines.json"
        topHeadlinesFile = context.assets.open("top_headlines.json")

//        mockWebServer.start()


    }

    @Test
    fun read_sample_success_json_file() {
        val reader = com.ahmedmhassaan.data.MockResponseFileReader(topHeadlinesFile)
        assertThat(reader.content).isNotNull()
    }

    @Test
    fun parse_response_as_Article_object() {
        val topHeadlinesJson =
            com.ahmedmhassaan.data.MockResponseFileReader(topHeadlinesFile).content
        val response: ArticlesResponse =
            Gson().fromJson(topHeadlinesJson, ArticlesResponse::class.java)

        assertThat(response).isInstanceOf(ArticlesResponse::class.java)
    }

    @Test
    fun getStatusCodeOfResposr_return_ok() {
        val topHeadlinesJson = MockResponseFileReader(topHeadlinesFile).content
        val articleResponse: ArticlesResponse =
            Gson().fromJson(topHeadlinesJson, ArticlesResponse::class.java)

        assertThat(articleResponse.status).isEqualTo("ok")
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }


    @Test
    fun showGiveOkResponseWithListOfArticles() {
        mockWebServer.enqueueResponse(topHeadlinesFile, 200)


    }

}