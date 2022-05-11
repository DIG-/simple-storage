package br.dev.dig.storage.sample

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.dev.dig.encoder.android.base64.StorageEncoderAndroidBase64
import br.dev.dig.storage.core.Storage
import br.dev.dig.storage.core.builder.StorageBuilder
import br.dev.dig.storage.core.common.encrypter.StorageEncryptionWithCipher
import br.dev.dig.storage.core.common.encrypter.StorageSaltAppend
import br.dev.dig.storage.core.common.hash.StorageKeyHashSHA256
import br.dev.dig.storage.serializer.GsonSerializer
import br.dev.dig.storage.storage.android.sp.StorageAndroidSharedPreferences
import javax.crypto.Cipher

class MainActivity : AppCompatActivity() {
    private val storage: Storage by lazy { createStorage() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        storage.put("batata", "Formiga")
        Log.d("Foi?", storage.get("batata"))
    }

    private fun createStorage(): Storage {
        val builder = StorageBuilder()
        builder.masterKey = "123456"
        val encoder = StorageEncoderAndroidBase64()
        builder.hash = StorageKeyHashSHA256(encoder)
        builder.encoder = encoder
        builder.decoder = encoder
        val crypto = StorageEncryptionWithCipher(Cipher.getInstance("AES/GCM/NoPadding"))
        builder.encryptor = crypto
        builder.decryptor = crypto
        val serializer = GsonSerializer()
        builder.serializer = serializer
        builder.deserializer = serializer
        builder.salter = StorageSaltAppend("android")
        builder.storage =
            StorageAndroidSharedPreferences(getSharedPreferences("local", Context.MODE_PRIVATE))
        return builder.build()
    }
}