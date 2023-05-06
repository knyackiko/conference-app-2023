package io.github.droidkaigi.confsched2023

import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.droidkaigi.confsched2023.testing.RobotTestRule
import javax.inject.Inject
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@HiltAndroidTest
@Config(
    qualifiers = RobolectricDeviceQualifiers.Pixel6
)
class TimetableScreenshotTest {

    @get:Rule val robotTestRule = RobotTestRule(this)

    @Inject lateinit var timetableScreenRobot: TimetableScreenRobot

    @Test
    fun startupScreenshot() {
        timetableScreenRobot(robotTestRule) {
            capture()
        }
    }
}

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class TimetableScreenTest {

    @get:Rule val robotTestRule = RobotTestRule(this)

    @Inject lateinit var timetableScreenRobot: TimetableScreenRobot

    @Test
    fun shouldBeAbleToLaunch() {
        timetableScreenRobot(robotTestRule) {
        }
    }
}

// ④ Shared Testing Robot
class TimetableScreenRobot @Inject constructor() {

    lateinit var composeTestRule: AndroidComposeTestRule<*, *>
    operator fun invoke(
        robotTestRule: RobotTestRule,
        block: TimetableScreenRobot.() -> Unit
    ) {
        this.composeTestRule = robotTestRule.composeTestRule
        composeTestRule.setContent {
            TimetableScreen()
        }
        block()
    }

    fun filterFavorite() {
//        composeTestRule
//            .onNodeWithTag("favorite")
//            .performClick()
    }

    fun areAllSessionsFavorite() {
        composeTestRule
            .onNodeWithText("All timetableItems are favorite")
            .assertExists()
    }

    fun isSessionListNotEmpty() {
        composeTestRule
            .onNodeWithText("Session 1")
            .assertExists()
    }

    fun capture() {
        // ③ Capture Robolectric image
        composeTestRule
            .onNode(isRoot())
            .captureRoboImage()
    }
}