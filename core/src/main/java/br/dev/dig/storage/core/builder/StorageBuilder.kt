package br.dev.dig.storage.core.builder

import br.dev.dig.storage.core.Storage
import br.dev.dig.storage.core.common.encoder.StorageEncoderHex
import br.dev.dig.storage.core.common.encrypter.StorageEncryptionDisabled
import br.dev.dig.storage.core.common.encrypter.StorageSaltNone
import br.dev.dig.storage.core.common.hash.StorageKeyHashDisabled
import br.dev.dig.storage.core.common.serializer.StorageSerializerBasic
import br.dev.dig.storage.core.operation.InternalStorage
import br.dev.dig.storage.core.operation.KeyHash
import br.dev.dig.storage.core.operation.encoder.Decode
import br.dev.dig.storage.core.operation.encoder.Encode
import br.dev.dig.storage.core.operation.encrypter.Decrypt
import br.dev.dig.storage.core.operation.encrypter.Encrypt
import br.dev.dig.storage.core.operation.encrypter.Salt
import br.dev.dig.storage.core.operation.serializer.Deserialize
import br.dev.dig.storage.core.operation.serializer.Serialize
import java.security.InvalidParameterException

class StorageBuilder {
    var storage: InternalStorage? = null
        private set
    var encoder: Encode
        private set
    var decoder: Decode
        private set
    var encryptor: Encrypt
        private set
    var decryptor: Decrypt
        private set
    var salter: Salt
        private set
    var serializer: Serialize
        private set
    var deserializer: Deserialize
        private set
    var hash: KeyHash
        private set
    var masterKey: String? = null
        private set

    init {
        val hex = StorageEncoderHex()
        encoder = hex
        decoder = hex
        val crypto = StorageEncryptionDisabled()
        encryptor = crypto
        decryptor = crypto
        salter = StorageSaltNone()
        val serializer = StorageSerializerBasic()
        this.serializer = serializer
        this.deserializer = serializer
        hash = StorageKeyHashDisabled()
    }

    fun storage(storage: InternalStorage) = apply { this.storage = storage }
    fun encoder(encoder: Encode) = apply { this.encoder = encoder }
    fun decoder(decoder: Decode) = apply { this.decoder = decoder }
    fun encryptor(encryptor: Encrypt) = apply { this.encryptor = encryptor }
    fun decryptor(decryptor: Decrypt) = apply { this.decryptor = decryptor }
    fun salter(salter: Salt) = apply { this.salter = salter }
    fun serializer(serializer: Serialize) = apply { this.serializer = serializer }
    fun deserializer(deserializer: Deserialize) = apply { this.deserializer = deserializer }
    fun hash(hash: KeyHash) = apply { this.hash = hash }
    fun masterKey(masterKey: String) = apply { this.masterKey = masterKey }

    @Suppress("SpellCheckingInspection")
    fun build(): Storage = DefaultStorage(
        storage ?: throw InvalidParameterException("Requires non null internal storage"),
        encoder,
        decoder,
        encryptor,
        decryptor,
        salter,
        serializer,
        deserializer,
        hash,
        masterKey ?: throw InvalidParameterException("Requires constant master key")
    )

}