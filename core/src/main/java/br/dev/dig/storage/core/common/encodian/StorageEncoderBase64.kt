package br.dev.dig.storage.core.common.encodian

import br.dev.dig.storage.core.operation.encodian.Decode
import br.dev.dig.storage.core.operation.encodian.Encode
import java.util.*

class StorageEncoderBase64 : Encode, Decode {

    private val encoder = Base64.getEncoder()
    private val decoder = Base64.getDecoder()

    override fun encode(content: ByteArray): String {
        return encoder.encodeToString(content)
    }

    override fun decode(content: String): ByteArray {
        return decoder.decode(content)
    }

}