package com.binar.foodorder

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by Rahmat Hidayat on 24/09/2023.
 */
class CounterDataStoreManager(private val context: Context) {

    suspend fun setIsLinearView(isLinear: Boolean) {
        context.counterDataStore.edit { preferences ->
            preferences[IS_LINEAR_KEY] = isLinear
        }
        Log.d("DataStore", "isLinearView disimpan ke DataStore: $isLinear")
    }

    fun getIsLinearView(): Flow<Boolean> {
        return context.counterDataStore.data.map { preferences ->
            val isLinear = preferences[IS_LINEAR_KEY] ?: true
            Log.d("DataStore", "Membaca isLinearView dari DataStore: $isLinear")
            isLinear
        }

    }

    companion object {
        private const val DATASTORE_NAME = "view_preferences"

        private val IS_LINEAR_KEY = booleanPreferencesKey("is_linear_key") // Gunakan tipe data booleanPreferencesKey

        private val Context.counterDataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}


