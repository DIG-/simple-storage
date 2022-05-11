package br.dev.dig.storage.core.operation.encryption

interface Decrypt {
    fun decrypt(content: ByteArray, password: String): String
}