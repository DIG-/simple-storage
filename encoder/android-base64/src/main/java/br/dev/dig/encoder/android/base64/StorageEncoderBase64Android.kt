package br.dev.dig.encoder.android.base64

import android.util.Base64
import br.dev.dig.storage.core.operation.encoder.Decode
import br.dev.dig.storage.core.operation.encoder.Encode

class StorageEncoderAndroidBase64 : Encode, Decode {

    override fun encode(content: ByteArray): String {
        return Base64.encodeToString(content, Base64.NO_WRAP)
    }

    override fun decode(content: String): ByteArray {
        return Base64.decode(content, Base64.NO_WRAP)
    }

}