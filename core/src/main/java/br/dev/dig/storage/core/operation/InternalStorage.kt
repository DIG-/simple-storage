package br.dev.dig.storage.core.operation

import br.dev.dig.storage.core.exception.KeyNotFound

interface InternalStorage {
    @Throws(KeyNotFound::class)
    fun get(key: String): String
    fun put(key: String, value: String)
    fun contains(key: String): Boolean
    fun delete(key: String)
    fun flush()
}