package br.dev.dig.storage.core.common.encryption

import br.dev.dig.storage.core.operation.encryption.Decrypt
import br.dev.dig.storage.core.operation.encryption.Encrypt

class StorageEncryptionDisabled : Encrypt, Decrypt {

    override fun decrypt(content: ByteArray, password: String): String {
        return content.toString(Charsets.UTF_8)
    }

    override fun encrypt(content: String, password: String): ByteArray {
        return content.toByteArray(Charsets.UTF_8)
    }

}