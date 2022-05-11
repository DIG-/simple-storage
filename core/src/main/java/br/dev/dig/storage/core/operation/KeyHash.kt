package br.dev.dig.storage.core.operation

interface KeyHash {
    fun hash(key: String): String
}