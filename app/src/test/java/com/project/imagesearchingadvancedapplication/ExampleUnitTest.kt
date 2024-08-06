package com.project.imagesearchingadvancedapplication

import com.project.imagesearchingadvancedapplication.viewmodel.model.api.RetrofitController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getVideosTest() = runTest {
        val retrofit = RetrofitController()
        val result = retrofit.getVideos("Ive")
        println("result: ${result.size}")
    }
}