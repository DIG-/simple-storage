package br.dev.dig.storage.core.operation.serializer

interface Serialize {
    fun <T:Any> serialize(item:T):String
}