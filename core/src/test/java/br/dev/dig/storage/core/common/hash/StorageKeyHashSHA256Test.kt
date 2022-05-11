package br.dev.dig.storage.core.common.hash

import br.dev.dig.storage.core.common.encoder.StorageEncoderHex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class StorageKeyHashSHA256Test {

    @Test
    fun validate() {
        val hash = StorageKeyHashSHA256(StorageEncoderHex())
        assertEquals("F60ED56A9C8275894022FE5A7A1625C33BDB55B729BB4E38962AF4D1613EDA25", hash.hash("android"))
        assertEquals("88FA0D759F845B47C044C2CD44E29082CF6FEA665C30C146374EC7C8F3D699E3", hash.hash("developer"))
        assertEquals("7C8764942C78299BEEC45125484D63FDB0F44D6D1C66E3DDBB78F65415EF933A", hash.hash("L<2Yy\"?V.jQkewQ_"))
    }

}