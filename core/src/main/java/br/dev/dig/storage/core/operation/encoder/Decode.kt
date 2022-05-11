package br.dev.dig.storage.core.operation.encoder

interface Decode {
    fun decode(content: String): ByteArray
}