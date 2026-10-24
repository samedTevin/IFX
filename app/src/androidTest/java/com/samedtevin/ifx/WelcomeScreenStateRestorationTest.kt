package com.samedtevin.ifx

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.samedtevin.ifx.welcome.AuthOptionType
import com.samedtevin.ifx.welcome.SavedAccountInfo
import com.samedtevin.ifx.welcome.WelcomeMode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WelcomeScreenStateRestorationTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun savedAccountState_survivesRestoration() {
        val restorationTester = StateRestorationTester(rule)
        var state: MutableState<SavedAccountInfo?>? = null

        restorationTester.setContent {
            state = rememberSaveable(stateSaver = SavedAccountInfo.Saver) {
                mutableStateOf(
                    SavedAccountInfo(
                        fullName = "Alex Morgan",
                        email = "alex@gmail.com",
                        provider = "Google",
                        initials = "AM"
                    )
                )
            }
        }

        // Verify initial state
        rule.runOnIdle {
            assertEquals("Alex Morgan", state!!.value?.fullName)
        }

        // Change state to a new account
        rule.runOnIdle {
            state!!.value = SavedAccountInfo(
                fullName = "Taylor Swift",
                email = "taylor@swift.com",
                provider = "Microsoft",
                initials = "TS"
            )
        }

        // Canonical trick: null out reference before restoration
        state = null

        // Emulate save -> dispose -> restore
        restorationTester.emulateSavedInstanceStateRestore()

        // Assert that state was restored accurately
        rule.runOnIdle {
            assertNotNull(state)
            assertEquals("Taylor Swift", state!!.value?.fullName)
            assertEquals("taylor@swift.com", state!!.value?.email)
            assertEquals("Microsoft", state!!.value?.provider)
            assertEquals("TS", state!!.value?.initials)
        }
    }

    @Test
    fun nullAccountState_survivesRestoration() {
        val restorationTester = StateRestorationTester(rule)
        var state: MutableState<SavedAccountInfo?>? = null

        restorationTester.setContent {
            state = rememberSaveable(stateSaver = SavedAccountInfo.Saver) {
                mutableStateOf(
                    SavedAccountInfo(
                        fullName = "Alex Morgan",
                        email = "alex@gmail.com",
                        provider = "Google",
                        initials = "AM"
                    )
                )
            }
        }

        // Set to null
        rule.runOnIdle {
            state!!.value = null
        }

        state = null
        restorationTester.emulateSavedInstanceStateRestore()

        rule.runOnIdle {
            assertNotNull(state)
            assertNull(state!!.value)
        }
    }

    @Test
    fun welcomeModeState_survivesRestoration() {
        val restorationTester = StateRestorationTester(rule)
        var modeState: MutableState<WelcomeMode>? = null

        restorationTester.setContent {
            modeState = rememberSaveable { mutableStateOf(WelcomeMode.SAVED_ACCOUNT) }
        }

        rule.runOnIdle {
            modeState!!.value = WelcomeMode.NEW_DEVICE
        }

        modeState = null
        restorationTester.emulateSavedInstanceStateRestore()

        rule.runOnIdle {
            assertNotNull(modeState)
            assertEquals(WelcomeMode.NEW_DEVICE, modeState!!.value)
        }
    }

    @Test
    fun authOptionSelectionState_survivesRestoration() {
        val restorationTester = StateRestorationTester(rule)
        var optionState: MutableState<AuthOptionType?>? = null

        restorationTester.setContent {
            optionState = rememberSaveable { mutableStateOf<AuthOptionType?>(null) }
        }

        rule.runOnIdle {
            optionState!!.value = AuthOptionType.EMAIL
        }

        optionState = null
        restorationTester.emulateSavedInstanceStateRestore()

        rule.runOnIdle {
            assertNotNull(optionState)
            assertEquals(AuthOptionType.EMAIL, optionState!!.value)
        }
    }
}
