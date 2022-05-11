package br.dev.dig.storage.core.builder

import br.dev.dig.storage.core.Storage
import br.dev.dig.storage.core.exception.DeserializeException
import br.dev.dig.storage.core.exception.KeyNotFound
import br.dev.dig.storage.core.operation.InternalStorage
import br.dev.dig.storage.core.operation.KeyHash
import br.dev.dig.storage.core.operation.encoder.Decode
import br.dev.dig.storage.core.operation.encoder.Encode
import br.dev.dig.storage.core.operation.encrypter.Decrypt
import br.dev.dig.storage.core.operation.encrypter.Encrypt
import br.dev.dig.storage.core.operation.encrypter.Salt
import br.dev.dig.storage.core.operation.serializer.Deserialize
import br.dev.dig.storage.core.operation.serializer.Serialize
import java.util.*

internal class DefaultStorage(
    private val storage: InternalStorage,
    private val encoder: Encode,
    private val decoder: Decode,
    private val encryptor: Encrypt,
    private val decryptor: Decrypt,
    private val salter: Salt,
    private val serializer: Serialize,
    private val deserializer: Deserialize,
    private val hash: KeyHash,
    private val masterKey: String
) : Storage() {

    private var masterPassword: String? = null

    override fun <T : Any> get(key: String, clazz: Class<T>): T {
        val encoded = storage.get(hash.hash(key))
        val decoded = decoder.decode(encoded)
        val decrypted = decryptor.decrypt(decoded, getPasswordForKey(key))
        return deserializer.deserialize(decrypted, clazz)
    }

    override fun <T : Any> getOrNull(key: String, clazz: Class<T>): T? {
        return try {
            get(key, clazz)
        } catch (_: DeserializeException) {
            null
        } catch (_: KeyNotFound) {
            null
        }
    }

    override fun <T : Any> getOrDefault(key: String, clazz: Class<T>, default: () -> T): T {
        return getOrNull(key, clazz) ?: default()
    }

    override fun <T : Any> put(key: String, item: T) {
        val serialized = serializer.serialize(item)
        val encrypted = encryptor.encrypt(serialized, getPasswordForKey(key))
        val encoded = encoder.encode(encrypted)
        storage.put(hash.hash(key), encoded)
    }

    override fun contains(key: String): Boolean {
        return storage.contains(hash.hash(key))
    }

    override fun delete(key: String) {
        storage.delete(hash.hash(key))
    }

    private fun getPasswordForKey(key: String): String {
        return hash.hash(salter.salt(getMasterPassword() + key))
    }

    private fun getMasterPassword(): String {
        masterPassword?.let {
            return it
        }
        val key = hash.hash(masterKey)
        if (storage.contains(key)) {
            val content = storage.get(key)
            val decoded = decoder.decode(content)
            val decrypt = decryptor.decrypt(decoded, salter.salt(masterKey))
            masterPassword = decrypt
            return decrypt
        }
        val password = UUID.randomUUID().toString()
        val encrypt = encryptor.encrypt(password, salter.salt(masterKey))
        val encoded = encoder.encode(encrypt)
        storage.put(key, encoded)
        masterPassword = password
        return password
    }

}