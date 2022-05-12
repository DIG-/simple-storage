package br.dev.dig.storage.core.util

import br.dev.dig.storage.core.Storage
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class StoragePrimitiveWrapperTest {

    open class MockStorage : Storage() {
        @Suppress("type_mismatch")
        override fun <T : Any> get(key: String, clazz: Class<T>): T {
            return when (clazz) {
                String::class.java -> {
                    ""
                }
                Boolean::class.java -> {
                    false
                }
                Int::class.java -> {
                    0
                }
                Float::class.java -> {
                    0f
                }
                Double::class.java -> {
                    0
                }
                else -> {
                    ""
                }
            }
        }

        override fun <T : Any> getOrNull(key: String, clazz: Class<T>): T? {
            return try {
                get(key, clazz)
            } catch (_: Throwable) {
                null
            }
        }

        override fun <T : Any> getOrDefault(key: String, clazz: Class<T>, default: () -> T): T {
            return getOrNull(key, clazz) ?: default()
        }

        override fun <T : Any> put(key: String, item: T) {
        }

        override fun contains(key: String): Boolean {
            return false
        }

        override fun delete(key: String) {
        }
    }

    lateinit var parent: Storage
    lateinit var storage: StoragePrimitiveWrapper

    @BeforeEach
    fun prepare() {
        val mock = MockStorage()
        parent = spy(mock)
        storage = StoragePrimitiveWrapper(parent)
    }

    @Test
    fun getString() {
        val key = UUID.randomUUID().toString()
        storage.getString(key)
        verify(parent, times(1)).get(key, String::class.java)
        reset(parent)
        storage.getStringOrNull(key)
        verify(parent, times(1)).getOrNull(key, String::class.java)
        reset(parent)
    }

    @Test
    fun getBoolean() {
        val key = UUID.randomUUID().toString()
        storage.getBoolean(key)
        verify(parent, times(1)).get(key, Boolean::class.java)
        reset(parent)
        storage.getBooleanOrNull(key)
        verify(parent, times(1)).getOrNull(key, Boolean::class.java)
        reset(parent)
    }

    @Test
    fun getInt() {
        val key = UUID.randomUUID().toString()
        storage.getInt(key)
        verify(parent, times(1)).get(key, Int::class.java)
        reset(parent)
        storage.getIntOrNull(key)
        verify(parent, times(1)).getOrNull(key, Int::class.java)
        reset(parent)
    }

    @Test
    fun getFloat() {
        val key = UUID.randomUUID().toString()
        storage.getFloat(key)
        verify(parent, times(1)).get(key, Float::class.java)
        reset(parent)
        storage.getFloatOrNull(key)
        verify(parent, times(1)).getOrNull(key, Float::class.java)
        reset(parent)
    }

}