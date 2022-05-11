package br.dev.dig.storage.serializer

import br.dev.dig.storage.core.common.serializer.StorageSerializerBasic
import com.google.gson.Gson

class StorageSerializerGson(private val gson: Gson) : StorageSerializerBasic() {

    constructor() : this(Gson())

    override fun <T : Any> toJson(item: T): String {
        return gson.toJson(item)
    }

    override fun <T : Any> fromJson(json: String, clazz: Class<T>): T {
        return gson.fromJson(json, clazz)
    }

}