package br.dev.dig.storage.migration.hawk

import br.dev.dig.storage.core.Storage
import br.dev.dig.storage.core.exception.DeserializeException
import com.orhanobut.hawk.Hawk

class HawkStorage : Storage() {

    override fun <T : Any> get(key: String, clazz: Class<T>): T {
        val value = Hawk.get<T>(key)
        if (!clazz.isInstance(value)) {
            throw DeserializeException("Save type and expected type does not match")
        }
        return value!!
    }

    override fun <T : Any> getOrNull(key: String, clazz: Class<T>): T? {
        return try {
            get(key, clazz)
        } catch (_: Throwable) {
            null
        }
    }

    override fun <T : Any> getOrDefault(key: String, clazz: Class<T>, default: () -> T): T {
        return getOrNull(key, clazz) ?: default()
    }

    override fun <T : Any> put(key: String, item: T) {
        Hawk.put(key, item)
    }

    override fun contains(key: String): Boolean {
        return Hawk.contains(key)
    }

    override fun delete(key: String) {
        Hawk.delete(key)
    }

}