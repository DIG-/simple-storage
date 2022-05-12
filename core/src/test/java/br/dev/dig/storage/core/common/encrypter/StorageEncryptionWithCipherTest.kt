package br.dev.dig.storage.core.common.encrypter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.util.*
import javax.crypto.Cipher

internal class StorageEncryptionWithCipherTest {

    @Test
    fun validate_AES_CFB_NoPadding() {
        validate(Cipher.getInstance("AES/CFB/NoPadding"))
    }

    @Test
    fun validate_AES_CFB_PKCS5Padding() {
        validate(Cipher.getInstance("AES/CFB/PKCS5Padding"))
    }


    fun validate_AES_GCM_NoPadding() {
        validate(Cipher.getInstance("AES/GCM/NoPadding"))
    }

    private fun validate(cipher: Cipher) {
        val input = UUID.randomUUID().toString()
        val password = UUID.randomUUID().toString()
        val crypto = StorageEncryptionWithCipher(cipher)
        val encrypted = crypto.encrypt(input, password)
        assertNotNull(encrypted)
        val decrypted = crypto.decrypt(encrypted, password)
        assertNotNull(decrypted)
        assertEquals(input, decrypted)
    }

}