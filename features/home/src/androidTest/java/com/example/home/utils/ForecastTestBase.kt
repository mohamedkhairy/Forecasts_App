package com.example.home.utils

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.home.runner.AndroidTestActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
abstract class ForecastTestBase {

        @get:Rule(order = 0)
        val hiltRule = HiltAndroidRule(this)

        @get:Rule(order = 1)
        val composeTestRule = createAndroidComposeRule<AndroidTestActivity>()


        internal lateinit var fakeViewModel: FakeHomeViewModel

        @Before
        fun setUp() {
                hiltRule.inject()
                fakeViewModel = FakeHomeViewModel()
        }

}