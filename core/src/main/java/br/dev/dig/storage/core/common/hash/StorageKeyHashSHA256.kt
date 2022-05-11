package br.dev.dig.storage.core.common.hash

import br.dev.dig.storage.core.operation.KeyHash
import br.dev.dig.storage.core.operation.encodian.Encode
import java.security.MessageDigest

class StorageKeyHashSHA256(private val encoder: Encode) : KeyHash {

    private val digest = MessageDigest.getInstance("SHA-256")

    override fun hash(key: String): String {
        val hash = digest.digest(key.toByteArray())
        return encoder.encode(hash)
    }

}