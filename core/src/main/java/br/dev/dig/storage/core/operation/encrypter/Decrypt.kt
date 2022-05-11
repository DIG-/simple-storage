package br.dev.dig.storage.core.operation.encrypter

interface Decrypt {
    fun decrypt(content: ByteArray, password: String): String
}