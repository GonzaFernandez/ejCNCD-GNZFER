package com.example.ejercicio_peliculas.common

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.ejercicio_peliculas.common.Constants.APP_FIRST_OPEN
import kotlinx.coroutines.flow.first
import javax.inject.Inject
private const val LOCAL_USER_PREFERENCES = "LOCAL_USER_PREFERENCES"
private val Context.dataStore by preferencesDataStore(name = LOCAL_USER_PREFERENCES)
class PreferencesRepository @Inject constructor(
    private val context: Context
){
    suspend fun saveFirstTimeAppIsOpened(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(APP_FIRST_OPEN)] = value
        }
    }

    suspend fun getFirstTimeAppIsOpened(): Boolean {
        return context.dataStore.data.first()[booleanPreferencesKey(APP_FIRST_OPEN)]?: true

    }
}