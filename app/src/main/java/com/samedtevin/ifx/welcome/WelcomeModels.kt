package com.samedtevin.ifx.welcome

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import java.io.Serializable

/**
 * Data model representing an existing account saved on the device.
 * Implements Serializable and provides a Compose Saver to ensure it can
 * always be safely preserved in Android Bundles and rememberSaveable.
 */
data class SavedAccountInfo(
    val fullName: String = "Alex Morgan",
    val email: String = "alex@gmail.com",
    val provider: String = "Google",
    val initials: String = computeInitials(fullName)
) : Serializable {

    val firstName: String
        get() {
            val parts = fullName.trim().split("\\s+".toRegex()).filter { it.isNotEmpty() }
            if (parts.isEmpty()) return ""
            return parts[0].replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }

    companion object {
        private const val serialVersionUID: Long = 1L

        /**
         * Safely computes a 1- or 2-letter uppercase monogram/initials string
         * across all edge cases (empty strings, single characters, irregular spacing).
         */
        fun computeInitials(name: String): String {
            val parts = name.trim().split("\\s+".toRegex()).filter { it.isNotEmpty() }
            return when {
                parts.isEmpty() -> "--"
                parts.size == 1 -> {
                    val single = parts[0]
                    if (single.length == 1) single.uppercase() else single.take(2).uppercase()
                }
                else -> {
                    val first = parts[0].firstOrNull()?.uppercase() ?: ""
                    val second = parts[1].firstOrNull()?.uppercase() ?: ""
                    "$first$second".ifEmpty { "--" }
                }
            }
        }

        /**
         * Dedicated Compose Saver for rememberSaveable state restoration.
         * Saves a boolean flag at index 0 so that an explicit null account is
         * faithfully distinguished from an uninitialized state and restored properly.
         */
        val Saver: Saver<SavedAccountInfo?, Any> = listSaver(
            save = { account ->
                if (account == null) {
                    listOf(false, "", "", "", "")
                } else {
                    listOf(
                        true,
                        account.fullName,
                        account.email,
                        account.provider,
                        account.initials
                    )
                }
            },
            restore = { list ->
                val hasAccount = list.getOrNull(0) as? Boolean ?: false
                if (!hasAccount) {
                    null
                } else {
                    SavedAccountInfo(
                        fullName = (list.getOrNull(1) as? String) ?: "",
                        email = (list.getOrNull(2) as? String) ?: "",
                        provider = (list.getOrNull(3) as? String) ?: "",
                        initials = (list.getOrNull(4) as? String) ?: "--"
                    )
                }
            }
        )
    }
}

/**
 * Supported authentication option types.
 */
enum class AuthOptionType : Serializable {
    EMAIL,
    GOOGLE,
    MICROSOFT,
    GUEST
}

/**
 * The two versions of the Welcome section:
 * - NEW_DEVICE: first-time device setup ("Welcome to ifx")
 * - SAVED_ACCOUNT: returning user with cached credentials ("Welcome back")
 */
enum class WelcomeMode : Serializable {
    NEW_DEVICE,
    SAVED_ACCOUNT
}
