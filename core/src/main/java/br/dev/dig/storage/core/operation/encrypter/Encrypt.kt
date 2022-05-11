package br.dev.dig.storage.core.operation.encrypter

interface Encrypt {
    fun encrypt(content: String, password: String): ByteArray
}