package br.dev.dig.storage.core.builder

import br.dev.dig.storage.core.Storage
import br.dev.dig.storage.core.common.encoder.StorageEncoderHex
import br.dev.dig.storage.core.common.hash.StorageKeyHashSHA256
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
    var encoder: Encode? = null
        private set
    var decoder: Decode? = null
        private set
    var encryptor: Encrypt? = null
        private set
    var decryptor: Decrypt? = null
        private set
    var salter: Salt? = null
        private set
    var serializer: Serialize? = null
        private set
    var deserializer: Deserialize? = null
        private set
    var hash: KeyHash? = null
        private set
    var masterKey: String? = null
        private set

    init {
        val hex = StorageEncoderHex()
        encoder = hex
        decoder = hex
        hash = StorageKeyHashSHA256(hex)
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
        encoder ?: throw InvalidParameterException("Requires non null encoder"),
        decoder ?: throw InvalidParameterException("Requires non null decoder"),
        encryptor ?: throw InvalidParameterException("Requires non null encryptor"),
        decryptor ?: throw InvalidParameterException("Requires non null decryptor"),
        salter ?: throw InvalidParameterException("Requires non null salter"),
        serializer ?: throw InvalidParameterException("Requires non null serializer"),
        deserializer ?: throw InvalidParameterException("Requires non null deserializer"),
        hash ?: throw InvalidParameterException("Requires non null hash"),
        masterKey ?: throw InvalidParameterException("Requires constant master key")
    )

}