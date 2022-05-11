package br.dev.dig.storage.simple.android

import android.content.SharedPreferences
import br.dev.dig.encoder.android.base64.StorageEncoderAndroidBase64
import br.dev.dig.storage.core.Storage
import br.dev.dig.storage.core.builder.StorageBuilder
import br.dev.dig.storage.core.common.encrypter.StorageEncryptionWithCipher
import br.dev.dig.storage.core.common.hash.StorageKeyHashSHA256
import br.dev.dig.storage.core.operation.encrypter.Salt
import br.dev.dig.storage.serializer.GsonSerializer
import br.dev.dig.storage.storage.android.sp.StorageAndroidSharedPreferences
import javax.crypto.Cipher

class StorageSimpleAndroidBuilder {

    private class NoSalt : Salt {
        override fun salt(password: String): String {
            return password
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    val builder: StorageBuilder = StorageBuilder()

    init {
        val serializer = GsonSerializer()
        builder.serializer(serializer).deserializer(serializer)
        val encoder = StorageEncoderAndroidBase64()
        builder.encoder(encoder).decoder(encoder).hash(StorageKeyHashSHA256(encoder))
        val crypto = StorageEncryptionWithCipher(Cipher.getInstance("AES/GCM/NoPadding"))
        builder.encryptor(crypto).decryptor(crypto)
        builder.salter(NoSalt())
    }

    fun preferences(preferences: SharedPreferences) =
        apply { builder.storage(StorageAndroidSharedPreferences(preferences)) }

    fun masterKey(masterKey: String) = apply { builder.masterKey(masterKey) }

    fun build(): Storage = builder.build()

}