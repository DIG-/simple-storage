package br.dev.dig.storage.simple.android

import android.content.SharedPreferences
import br.dev.dig.encoder.android.base64.StorageEncoderAndroidBase64
import br.dev.dig.storage.core.Storage
import br.dev.dig.storage.core.builder.StorageBuilder
import br.dev.dig.storage.core.common.encrypter.StorageEncryptionWithCipher
import br.dev.dig.storage.core.common.hash.StorageKeyHashSHA256
import br.dev.dig.storage.serializer.StorageSerializerGson
import br.dev.dig.storage.storage.android.sp.StorageAndroidSharedPreferences

class StorageSimpleAndroidBuilder {

    @Suppress("MemberVisibilityCanBePrivate")
    val builder: StorageBuilder = StorageBuilder()

    init {
        val serializer = StorageSerializerGson()
        builder.serializer(serializer).deserializer(serializer)
        val encoder = StorageEncoderAndroidBase64()
        builder.encoder(encoder).decoder(encoder).hash(StorageKeyHashSHA256(encoder))
        val crypto = StorageEncryptionWithCipher.AES_GCM_NoPadding()
        builder.encryptor(crypto).decryptor(crypto)
    }

    fun preferences(preferences: SharedPreferences) =
        apply { builder.storage(StorageAndroidSharedPreferences(preferences)) }

    fun masterKey(masterKey: String) = apply { builder.masterKey(masterKey) }

    fun build(): Storage = builder.build()

}