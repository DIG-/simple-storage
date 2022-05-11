package br.dev.dig.storage.core.builder

import br.dev.dig.storage.core.Storage
import br.dev.dig.storage.core.common.encodian.StorageEncoderHex
import br.dev.dig.storage.core.common.hash.StorageKeyHashSHA256
import br.dev.dig.storage.core.operation.InternalStorage
import br.dev.dig.storage.core.operation.KeyHash
import br.dev.dig.storage.core.operation.encodian.Decode
import br.dev.dig.storage.core.operation.encodian.Encode
import br.dev.dig.storage.core.operation.encryption.Decrypt
import br.dev.dig.storage.core.operation.encryption.Encrypt
import br.dev.dig.storage.core.operation.encryption.Salt
import br.dev.dig.storage.core.operation.serialization.Deserialize
import br.dev.dig.storage.core.operation.serialization.Serialize
import java.security.InvalidParameterException

class StorageBuilder {
    var storage: InternalStorage? = null
    var encoder: Encode? = null
    var decoder: Decode? = null
    var encryptor: Encrypt? = null
    var decryptor: Decrypt? = null
    var salter: Salt? = null
    var serializer: Serialize? = null
    var deserializer: Deserialize? = null
    var hash: KeyHash? = null
    var masterKey: String? = null

    init {
        val hex = StorageEncoderHex()
        encoder = hex
        decoder = hex
        hash = StorageKeyHashSHA256(hex)
    }

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