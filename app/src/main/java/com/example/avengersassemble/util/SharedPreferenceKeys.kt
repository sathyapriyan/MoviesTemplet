package com.example.avengersassemble.util

import androidx.datastore.preferences.core.stringPreferencesKey

object SharedPreferenceKeys {

    // Preference File
    const val USER_PREFERENCES_FILE = "user_info"
    const val COMMON_PREFERENCE_FILE = "common_info"

    // User Preference Keys
    val USER_ID= stringPreferencesKey("access_id")

}