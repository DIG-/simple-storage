package br.dev.dig.storage.core.common.hash

import br.dev.dig.storage.core.operation.KeyHash
import java.security.MessageDigest
import java.util.*

class StorageKeyHashSHA256 : KeyHash {

    private val digest = MessageDigest.getInstance("SHA-256")
    private val encoder = Base64.getEncoder()

    override fun hash(key: String): String {
        val hash = digest.digest(key.toByteArray())
        return encoder.encodeToString(hash)
    }

}