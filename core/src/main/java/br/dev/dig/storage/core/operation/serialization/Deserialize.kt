package br.dev.dig.storage.core.operation.serialization

import br.dev.dig.storage.core.exception.DeserializeException

interface Deserialize {
    @Throws(DeserializeException::class)
    fun <T : Any> deserialize(item: String, clazz: Class<T>): T
}