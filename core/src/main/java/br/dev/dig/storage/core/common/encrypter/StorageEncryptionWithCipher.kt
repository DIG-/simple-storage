package br.dev.dig.storage.core.common.encrypter

import br.dev.dig.storage.core.operation.encrypter.Decrypt
import br.dev.dig.storage.core.operation.encrypter.Encrypt
import java.security.Key
import java.security.SecureRandom
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

abstract class StorageEncryptionWithCipher : Encrypt, Decrypt {

    @Suppress("LeakingThis")
    private val cipher: Cipher = createCipher()

    @Suppress("PropertyName")
    protected abstract val IV_SIZE: Int

    override fun encrypt(content: String, password: String): ByteArray {
        val iv = ByteArray(IV_SIZE)
        SecureRandom().nextBytes(iv)
        val encrypted = synchronized(cipher) {
            cipher.init(Cipher.ENCRYPT_MODE, parseKey(password), createParameterSpec(iv, 0))
            cipher.doFinal(content.toByteArray(Charsets.UTF_8))
        }
        return iv.plus(encrypted)
    }

    override fun decrypt(content: ByteArray, password: String): String {
        return synchronized(cipher) {
            cipher.init(
                Cipher.DECRYPT_MODE,
                parseKey(password),
                createParameterSpec(content, 0)
            )
            cipher.doFinal(content, IV_SIZE, content.size - IV_SIZE).toString(Charsets.UTF_8)
        }
    }

    open fun parseKey(password: String): Key {
        @Suppress("SpellCheckingInspection")
        var temp = "${password}123456789ABCD"
        temp = if (temp.length > 32) {
            temp.substring(0, 32)
        } else {
            temp.substring(0, 16)
        }
        return SecretKeySpec(temp.toByteArray(Charsets.UTF_8), "AES")
    }

    abstract fun createCipher(): Cipher

    abstract fun createParameterSpec(iv: ByteArray, offset: Int): AlgorithmParameterSpec

    @Suppress("ClassName")
    class AES_GCM_NoPadding : StorageEncryptionWithCipher() {
        override val IV_SIZE: Int
            get() = 16

        override fun createCipher(): Cipher {
            return Cipher.getInstance("AES/GCM/NoPadding")
        }

        override fun createParameterSpec(iv: ByteArray, offset: Int): AlgorithmParameterSpec {
            return GCMParameterSpec(128, iv, offset, IV_SIZE)
        }
    }

}