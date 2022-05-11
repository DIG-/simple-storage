package br.dev.dig.storage.core.operation.encryption

interface Salt {
    fun salt(password: String): String
}