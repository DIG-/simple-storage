package br.dev.dig.storage.core.common.encoder

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

internal class StorageEncoderBase64Test {

    @Test
    fun validate() {
        val input = UUID.randomUUID().toString()
        val byte = input.toByteArray(Charsets.UTF_8)
        val encoder = StorageEncoderBase64()
        val encoded = encoder.encode(byte)
        Assertions.assertNotNull(encoded)
        val decoded = encoder.decode(encoded)
        Assertions.assertNotNull(decoded)
        Assertions.assertArrayEquals(byte, decoded)
    }

}