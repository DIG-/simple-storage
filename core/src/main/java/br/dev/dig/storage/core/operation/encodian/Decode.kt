package br.dev.dig.storage.core.operation.encodian

interface Decode {
    fun decode(content: String): ByteArray
}