package br.dev.dig.storage.core.operation.encoder

interface Encode {
    fun encode(content: ByteArray): String
}