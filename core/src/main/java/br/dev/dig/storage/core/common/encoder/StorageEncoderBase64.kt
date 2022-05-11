package br.dev.dig.storage.core.common.encoder

import br.dev.dig.storage.core.operation.encoder.Decode
import br.dev.dig.storage.core.operation.encoder.Encode
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