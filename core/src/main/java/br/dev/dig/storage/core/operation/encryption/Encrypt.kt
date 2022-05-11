package br.dev.dig.storage.core.operation.encryption

interface Encrypt {
    fun encrypt(content: String, password: String): ByteArray
}