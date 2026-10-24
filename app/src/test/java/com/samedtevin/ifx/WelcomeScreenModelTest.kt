package com.samedtevin.ifx

import androidx.compose.runtime.saveable.SaverScope
import com.samedtevin.ifx.welcome.AuthOptionType
import com.samedtevin.ifx.welcome.SavedAccountInfo
import com.samedtevin.ifx.welcome.WelcomeMode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

class WelcomeScreenModelTest {

    private val testSaverScope = object : SaverScope {
        override fun canBeSaved(value: Any): Boolean = true
    }

    @Test
    fun defaultSavedAccount_hasExpectedProperties() {
        val account = SavedAccountInfo()
        assertEquals("Alex Morgan", account.fullName)
        assertEquals("alex@gmail.com", account.email)
        assertEquals("Google", account.provider)
        assertEquals("AM", account.initials)
    }

    @Test
    fun firstNameExtraction_worksAcrossAllEdgeCases() {
        assertEquals("Alex", SavedAccountInfo(fullName = "Alex Morgan").firstName)
        assertEquals("Alex", SavedAccountInfo(fullName = "alex morgan").firstName)
        assertEquals("Alex", SavedAccountInfo(fullName = "Alex").firstName)
        assertEquals("Alex", SavedAccountInfo(fullName = "alex").firstName)
        assertEquals("Alex", SavedAccountInfo(fullName = "   alex    morgan   ").firstName)
        assertEquals("", SavedAccountInfo(fullName = "").firstName)
        assertEquals("", SavedAccountInfo(fullName = "   ").firstName)
        assertEquals("", SavedAccountInfo(fullName = "\t \n").firstName)
    }

    @Test
    fun computeInitials_worksAcrossAllEdgeCases() {
        // Standard two-word names
        assertEquals("AM", SavedAccountInfo.computeInitials("Alex Morgan"))
        assertEquals("SC", SavedAccountInfo.computeInitials("sarah connor"))

        // Multiple words (should take first 2 words)
        assertEquals("JD", SavedAccountInfo.computeInitials("John Doe Extra"))
        assertEquals("JD", SavedAccountInfo.computeInitials("   John    Doe   Smith  "))

        // Single word (2 or more characters -> first 2 uppercase letters)
        assertEquals("AL", SavedAccountInfo.computeInitials("Alex"))
        assertEquals("JO", SavedAccountInfo.computeInitials("john"))

        // Single character word -> 1 uppercase letter
        assertEquals("A", SavedAccountInfo.computeInitials("A"))
        assertEquals("Z", SavedAccountInfo.computeInitials("z"))

        // Empty and whitespace edge cases
        assertEquals("--", SavedAccountInfo.computeInitials(""))
        assertEquals("--", SavedAccountInfo.computeInitials("   "))
        assertEquals("--", SavedAccountInfo.computeInitials("\n\t  \r"))

        // Unicode / Accented characters
        assertEquals("ÉZ", SavedAccountInfo.computeInitials("Émile Zola"))

        // Alphanumeric / Symbols
        assertEquals("U9", SavedAccountInfo.computeInitials("User 99"))
    }

    @Test
    fun composeSaver_savesAndRestoresPopulatedAccount() {
        val original = SavedAccountInfo(
            fullName = "Alex Morgan",
            email = "alex@gmail.com",
            provider = "Google",
            initials = "AM"
        )

        val saved = with(SavedAccountInfo.Saver) { testSaverScope.save(original) }
        assertNotNull(saved)
        assertTrue(saved is List<*>)

        val restored = SavedAccountInfo.Saver.restore(saved!!)
        assertNotNull(restored)
        assertEquals(original.fullName, restored?.fullName)
        assertEquals(original.email, restored?.email)
        assertEquals(original.provider, restored?.provider)
        assertEquals(original.initials, restored?.initials)
    }

    @Test
    fun composeSaver_savesAndRestoresNullAccount() {
        val original: SavedAccountInfo? = null

        val saved = with(SavedAccountInfo.Saver) { testSaverScope.save(original) }
        assertNotNull(saved)
        assertTrue(saved is List<*>)

        val restored = SavedAccountInfo.Saver.restore(saved!!)
        assertNull("Explicit null account must be restored as null", restored)
    }

    @Test
    fun composeSaver_savesAndRestoresEmptyFields() {
        val original = SavedAccountInfo(
            fullName = "",
            email = "",
            provider = "",
            initials = "--"
        )

        val saved = with(SavedAccountInfo.Saver) { testSaverScope.save(original) }
        assertNotNull(saved)

        val restored = SavedAccountInfo.Saver.restore(saved!!)
        assertNotNull(restored)
        assertEquals("", restored?.fullName)
        assertEquals("", restored?.email)
        assertEquals("", restored?.provider)
        assertEquals("--", restored?.initials)
    }

    @Test
    fun standardJavaSerialization_savedAccountInfo() {
        val original = SavedAccountInfo(
            fullName = "Alex Morgan",
            email = "alex@gmail.com",
            provider = "Google",
            initials = "AM"
        )

        val roundTripped = serializeAndDeserialize(original)
        assertEquals(original, roundTripped)
    }

    @Test
    fun standardJavaSerialization_enums() {
        for (option in AuthOptionType.values()) {
            val restored = serializeAndDeserialize(option)
            assertEquals(option, restored)
        }

        for (mode in WelcomeMode.values()) {
            val restored = serializeAndDeserialize(mode)
            assertEquals(mode, restored)
        }

        for (dest in AppDestination.values()) {
            val restored = serializeAndDeserialize(dest)
            assertEquals(dest, restored)
        }
    }

    @Test
    fun welcomeMode_hasBothRequiredVariants() {
        val modes = WelcomeMode.values()
        assertEquals(2, modes.size)
        assertEquals(WelcomeMode.NEW_DEVICE, WelcomeMode.valueOf("NEW_DEVICE"))
        assertEquals(WelcomeMode.SAVED_ACCOUNT, WelcomeMode.valueOf("SAVED_ACCOUNT"))
    }

    @Test
    fun authOptionTypes_coversAllAuthenticationMethods() {
        val options = AuthOptionType.values()
        assertEquals(4, options.size)
        assertEquals(AuthOptionType.EMAIL, AuthOptionType.valueOf("EMAIL"))
        assertEquals(AuthOptionType.GOOGLE, AuthOptionType.valueOf("GOOGLE"))
        assertEquals(AuthOptionType.MICROSOFT, AuthOptionType.valueOf("MICROSOFT"))
        assertEquals(AuthOptionType.GUEST, AuthOptionType.valueOf("GUEST"))
    }

    private inline fun <reified T : Serializable> serializeAndDeserialize(obj: T): T {
        val byteOut = ByteArrayOutputStream()
        ObjectOutputStream(byteOut).use { it.writeObject(obj) }
        val byteIn = ByteArrayInputStream(byteOut.toByteArray())
        return ObjectInputStream(byteIn).use { it.readObject() as T }
    }
}
