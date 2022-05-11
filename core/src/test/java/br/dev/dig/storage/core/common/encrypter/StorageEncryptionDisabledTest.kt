package br.dev.dig.storage.core.common.encrypter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.util.*

internal class StorageEncryptionDisabledTest {

    @Test
    fun validate() {
        val input = UUID.randomUUID().toString()
        val password = UUID.randomUUID().toString()
        val crypto = StorageEncryptionDisabled()
        val encrypted = crypto.encrypt(input, password)
        assertNotNull(encrypted)
        val decrypted = crypto.decrypt(encrypted, password)
        assertNotNull(decrypted)
        assertEquals(input, decrypted)
    }

}