package br.dev.dig.storage.core.common.encrypter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.security.spec.AlgorithmParameterSpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec

internal class StorageEncryptionWithCipherTest {

    @Test
    fun validate_AES_CFB_NoPadding() {
        validate(object : StorageEncryptionWithCipher() {
            override val IV_SIZE: Int
                get() = 16

            override fun createCipher(): Cipher {
                return Cipher.getInstance("AES/CFB/NoPadding")
            }

            override fun createParameterSpec(iv: ByteArray, offset: Int): AlgorithmParameterSpec {
                return IvParameterSpec(iv, offset, IV_SIZE)
            }
        })
    }

    @Test
    fun validate_AES_CFB_PKCS5Padding() {
        validate(object : StorageEncryptionWithCipher() {
            override val IV_SIZE: Int
                get() = 16

            override fun createCipher(): Cipher {
                return Cipher.getInstance("AES/CFB/PKCS5Padding")
            }

            override fun createParameterSpec(iv: ByteArray, offset: Int): AlgorithmParameterSpec {
                return IvParameterSpec(iv, offset, IV_SIZE)
            }
        })
    }

    @Test
    fun validate_AES_GCM_NoPadding() {
        validate(StorageEncryptionWithCipher.AES_GCM_NoPadding())
    }

    private fun validate(crypto: StorageEncryptionWithCipher) {
        val input = UUID.randomUUID().toString()
        val password = UUID.randomUUID().toString()
        val encrypted = crypto.encrypt(input, password)
        assertNotNull(encrypted)
        val decrypted = crypto.decrypt(encrypted, password)
        assertNotNull(decrypted)
        assertEquals(input, decrypted)
    }

}