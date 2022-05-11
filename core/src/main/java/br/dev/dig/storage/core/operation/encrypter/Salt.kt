package br.dev.dig.storage.core.operation.encrypter

interface Salt {
    fun salt(password: String): String
}