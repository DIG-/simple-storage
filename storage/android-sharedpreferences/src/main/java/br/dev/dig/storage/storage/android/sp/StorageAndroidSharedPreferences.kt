package br.dev.dig.storage.storage.android.sp

import android.content.SharedPreferences
import br.dev.dig.storage.core.exception.KeyNotFound
import br.dev.dig.storage.core.operation.InternalStorage

class StorageAndroidSharedPreferences(private val sp: SharedPreferences) : InternalStorage {

    override fun get(key: String): String {
        return sp.getString(key, null) ?: throw KeyNotFound()
    }

    override fun put(key: String, value: String) {
        sp.edit().putString(key, value).apply()
    }

    override fun contains(key: String): Boolean {
        return sp.contains(key)
    }

    override fun delete(key: String) {
        sp.edit().remove(key).apply()
    }

}