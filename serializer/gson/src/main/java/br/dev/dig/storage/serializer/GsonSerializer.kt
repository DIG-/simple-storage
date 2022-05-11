package br.dev.dig.storage.serializer

import br.dev.dig.storage.core.common.serializer.StorageSerializationBasic
import com.google.gson.Gson

class GsonSerializer(private val gson: Gson) : StorageSerializationBasic() {

    constructor() : this(Gson())

    override fun <T : Any> toJson(item: T): String {
        return gson.toJson(item)
    }

    override fun <T : Any> fromJson(json: String, clazz: Class<T>): T {
        return gson.fromJson(json, clazz)
    }

}