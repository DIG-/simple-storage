package br.dev.dig.storage.core.common.encoder

import br.dev.dig.storage.core.operation.encoder.Decode
import br.dev.dig.storage.core.operation.encoder.Encode
import kotlin.experimental.or

class StorageEncoderHex : Encode, Decode {

    companion object {
        @Suppress("SpellCheckingInspection")
        private const val HEX_TABLE = "0123456789ABCDEF"
    }

    override fun encode(content: ByteArray): String {
        val builder = StringBuilder(2 * content.size)
        for (byte in content) {
            val asInt = byte.toInt()
            builder.append(HEX_TABLE[asInt.shr(4) and 0x0F])
            builder.append(HEX_TABLE[asInt and 0x0F])
        }
        return builder.toString()
    }

    override fun decode(content: String): ByteArray {
        val output = ByteArray(content.length / 2)
        var left = true
        var i = 0
        for (char in content) {
            output[i] = if (left) {
                (HEX_TABLE.indexOf(char, ignoreCase = true).shl(4)).toByte()
            } else {
                output[i] or (HEX_TABLE.indexOf(char, ignoreCase = true) and 0x0F).toByte()
            }
            if (left) {
                left = false
            } else {
                left = true
                i += 1
            }
        }
        return output
    }

}