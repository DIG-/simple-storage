package br.dev.dig.storage.core

abstract class Storage {
    abstract fun <T : Any> get(key: String, clazz: Class<T>): T
    abstract fun <T : Any> getOrNull(key: String, clazz: Class<T>): T?
    abstract fun <T : Any> getOrDefault(key: String, clazz: Class<T>, default: () -> T): T

    inline fun <reified T : Any> get(key: String): T = get(key, T::class.java)
    inline fun <reified T : Any> getOrNull(key: String): T? = getOrNull(key, T::class.java)
    inline fun <reified T : Any> getOrDefault(key: String, noinline default: () -> T): T =
        getOrDefault(key, T::class.java, default)

    abstract fun <T : Any> put(key: String, item: T)
    abstract fun contains(key: String): Boolean
    abstract fun delete(key: String)
    abstract fun flush()
}