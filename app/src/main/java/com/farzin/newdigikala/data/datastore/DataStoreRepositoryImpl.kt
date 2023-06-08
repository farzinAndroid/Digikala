package com.farzin.newdigikala.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.farzin.newdigikala.util.AES
import com.farzin.newdigikala.util.Constants.DATASTORE_NAME
import com.farzin.newdigikala.util.Constants.IV
import com.farzin.newdigikala.util.Constants.KEY
import kotlinx.coroutines.flow.first
import javax.inject.Inject


private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

class DataStoreRepositoryImpl @Inject constructor(
    private val context: Context
) : DataStoreRepository {


    override suspend fun putString(key: String, value: String) {
        val encrypted = AES.encryptAES(value , KEY , IV)
        Log.e("3636" , encrypted)
        val preferencesKey = stringPreferencesKey(key)
        context.datastore.edit { preferences ->
            preferences[preferencesKey] = encrypted
        }
    }

    override suspend fun putInt(key: String, value: Int) {
        val preferencesKey = intPreferencesKey(key)
        context.datastore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        return try {
            val preferencesKey = stringPreferencesKey(key)
            val preferences = context.datastore.data.first()
            preferences[preferencesKey]?.let { AES.decryptAES(it, KEY , IV) }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getInt(key: String): Int? {
        return try {
            val preferencesKey = intPreferencesKey(key)
            val preferences = context.datastore.data.first()
            preferences[preferencesKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}