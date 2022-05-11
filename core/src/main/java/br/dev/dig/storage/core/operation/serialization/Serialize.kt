package br.dev.dig.storage.core.operation.serialization

interface Serialize {
    fun <T:Any> serialize(item:T):String
}