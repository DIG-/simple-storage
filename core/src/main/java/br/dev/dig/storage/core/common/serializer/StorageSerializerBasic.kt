package br.dev.dig.storage.core.common.serializer

import br.dev.dig.storage.core.exception.DeserializeException
import br.dev.dig.storage.core.operation.serializer.Deserialize
import br.dev.dig.storage.core.operation.serializer.Serialize

open class StorageSerializerBasic : Serialize, Deserialize {

    companion object {
        private const val SEPARATOR = ':'
    }

    override fun <T : Any> serialize(item: T): String = when (item) {
        is String -> "string${SEPARATOR}$item"
        is Boolean -> if (item) {
            "bool${SEPARATOR}true"
        } else {
            "bool${SEPARATOR}false"
        }
        is Int -> "int${SEPARATOR}$item"
        is Float -> "float${SEPARATOR}$item"
        is Double -> "double${SEPARATOR}$item"
        else -> "json${SEPARATOR}${toJson(item)}"
    }

    @Suppress("type_mismatch")
    override fun <T : Any> deserialize(item: String, clazz: Class<T>): T {
        val split = item.split(SEPARATOR, limit = 2)
        if (split.size != 2) {
            throw DeserializeException("Error unwrapping item")
        }
        val prefix = split[0]
        val value = split[1]
        return if (prefix == "string") {
            if (clazz == String::class.java) {
                value
            } else {
                throw DeserializeException("Trying to deserialize $prefix as ${clazz.simpleName}")
            }
        } else if (prefix == "bool") {
            if (clazz == Boolean::class.java) {
                value != "false"
            } else {
                throw DeserializeException("Trying to deserialize $prefix as ${clazz.simpleName}")
            }
        } else if (prefix == "int") {
            if (clazz == Int::class.java) {
                value.toInt()
            } else {
                throw DeserializeException("Trying to deserialize $prefix as ${clazz.simpleName}")
            }
        } else if (prefix == "float") {
            if (clazz == Float::class.java) {
                value.toFloat()
            } else {
                throw DeserializeException("Trying to deserialize $prefix as ${clazz.simpleName}")
            }
        } else if (prefix == "double") {
            if (clazz == Double::class.java) {
                value.toDouble()
            } else {
                throw DeserializeException("Trying to deserialize $prefix as ${clazz.simpleName}")
            }
        } else if (prefix == "json") {
            fromJson(value, clazz)
        } else {
            throw DeserializeException("Unable to decode prefix \"${prefix}\"")
        }
    }

    open fun <T : Any> toJson(item: T): String {
        throw NotImplementedError()
    }

    open fun <T : Any> fromJson(json: String, clazz: Class<T>): T {
        throw NotImplementedError()
    }

}