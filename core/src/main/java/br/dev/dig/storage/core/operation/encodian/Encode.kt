package br.dev.dig.storage.core.operation.encodian

interface Encode {
    fun encode(content: ByteArray): String
}