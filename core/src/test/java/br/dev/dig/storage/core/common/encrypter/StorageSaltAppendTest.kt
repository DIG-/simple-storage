package br.dev.dig.storage.core.common.encrypter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.util.*

internal class StorageSaltAppendTest {

    @Test
    fun validate() {
        val prefix = UUID.randomUUID().toString()
        val password = UUID.randomUUID().toString()
        val salter = StorageSaltAppend(prefix)
        val salted = salter.salt(password)
        assertNotNull(salted)
        assertEquals("$prefix$password", salted)
    }

}