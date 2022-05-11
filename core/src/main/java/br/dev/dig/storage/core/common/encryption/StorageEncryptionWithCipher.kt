package br.dev.dig.storage.core.common.encryption

import br.dev.dig.storage.core.operation.encryption.Decrypt
import br.dev.dig.storage.core.operation.encryption.Encrypt
import java.security.Key
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

open class StorageEncryptionWithCipher(private val cipher: Cipher) : Encrypt, Decrypt {

    companion object {
        private const val IV_SIZE = 16
    }

    override fun encrypt(content: String, password: String): ByteArray {
        val iv = ByteArray(IV_SIZE)
        SecureRandom().nextBytes(iv)
        val encrypted = synchronized(cipher) {
            cipher.init(Cipher.ENCRYPT_MODE, parseKey(password), IvParameterSpec(iv))
            cipher.doFinal(content.toByteArray(Charsets.UTF_8))
        }
        return iv.plus(encrypted)
    }

    override fun decrypt(content: ByteArray, password: String): String {
        return synchronized(cipher) {
            cipher.init(
                Cipher.DECRYPT_MODE,
                parseKey(password),
                IvParameterSpec(content, 0, IV_SIZE)
            )
            cipher.doFinal(content, IV_SIZE, content.size - IV_SIZE).toString(Charsets.UTF_8)
        }
    }

    open fun parseKey(password: String): Key {
        return SecretKeySpec(password.toByteArray(Charsets.UTF_8), "AES")
    }

}