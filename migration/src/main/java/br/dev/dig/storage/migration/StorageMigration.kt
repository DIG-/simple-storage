package br.dev.dig.storage.migration

import br.dev.dig.storage.core.Storage
import br.dev.dig.storage.core.exception.DeserializeException
import br.dev.dig.storage.core.exception.KeyNotFound

class StorageMigration(private val current: Storage, private vararg val others: Storage) :
    Storage() {

    override fun <T : Any> get(key: String, clazz: Class<T>): T {
        try {
            return current.get(key, clazz)
        } catch (_: Throwable) {

        }
        throw KeyNotFound()
    }

    override fun <T : Any> getOrNull(key: String, clazz: Class<T>): T? {
        return try {
            get(key, clazz)
        } catch (_: DeserializeException) {
            null
        } catch (_: KeyNotFound) {
            null
        }
    }

    override fun <T : Any> getOrDefault(key: String, clazz: Class<T>, default: () -> T): T {
        return getOrNull(key, clazz) ?: default()
    }

    override fun <T : Any> put(key: String, item: T) {
        current.put(key, item)
    }

    override fun contains(key: String): Boolean {
        if (current.contains(key))
            return true
        return others.any { it.contains(key) }
    }

    override fun delete(key: String) {
        current.delete(key)
        others.forEach { it.delete(key) }
    }

}