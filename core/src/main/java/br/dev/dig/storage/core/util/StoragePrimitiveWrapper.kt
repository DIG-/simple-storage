package br.dev.dig.storage.core.util

import br.dev.dig.storage.core.Storage

class StoragePrimitiveWrapper(private val storage: Storage) : Storage() {

    override fun <T : Any> get(key: String, clazz: Class<T>): T {
        return storage.get(key, clazz)
    }

    override fun <T : Any> getOrNull(key: String, clazz: Class<T>): T? {
        return storage.getOrNull(key, clazz)
    }

    override fun <T : Any> getOrDefault(key: String, clazz: Class<T>, default: () -> T): T {
        return storage.getOrDefault(key, clazz, default)
    }

    override fun <T : Any> put(key: String, item: T) {
        return storage.put(key, item)
    }

    override fun contains(key: String): Boolean {
        return storage.contains(key)
    }

    override fun delete(key: String) {
        return storage.delete(key)
    }

    /* ================
       String methods
    */
    fun getString(key: String): String {
        return get(key, String::class.java)
    }

    fun getStringOrNull(key: String): String? {
        return getOrNull(key, String::class.java)
    }

    fun getStringOrDefault(key: String, default: () -> String): String {
        return getOrDefault(key, String::class.java, default)
    }

    fun putString(key: String, value: String) {
        put(key, value)
    }

    /* ================
       Int methods
    */
    fun getInt(key: String): Int {
        return get(key, Int::class.java)
    }

    fun getIntOrNull(key: String): Int? {
        return getOrNull(key, Int::class.java)
    }

    fun getIntOrDefault(key: String, default: () -> Int): Int {
        return getOrDefault(key, Int::class.java, default)
    }

    fun putInt(key: String, value: Int) {
        put(key, value)
    }

    /* ================
       Float methods
    */
    fun getFloat(key: String): Float {
        return get(key, Float::class.java)
    }

    fun getFloatOrNull(key: String): Float? {
        return getOrNull(key, Float::class.java)
    }

    fun getFloatOrDefault(key: String, default: () -> Float): Float {
        return getOrDefault(key, Float::class.java, default)
    }

    fun putFloat(key: String, value: Float) {
        put(key, value)
    }

    /* ================
       Boolean methods
    */
    fun getBoolean(key: String): Boolean {
        return get(key, Boolean::class.java)
    }

    fun getBooleanOrNull(key: String): Boolean? {
        return getOrNull(key, Boolean::class.java)
    }

    fun getBooleanOrDefault(key: String, default: () -> Boolean): Boolean {
        return getOrDefault(key, Boolean::class.java, default)
    }

    fun putBoolean(key: String, value: Boolean) {
        put(key, value)
    }
}