package br.dev.dig.storage.core.common.serializer

import br.dev.dig.storage.core.exception.DeserializeException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

internal class StorageSerializerBasicTest {

    private val serializer = StorageSerializerBasic()

    @Test
    fun validate_String() {
        val input = UUID.randomUUID().toString()
        val serialized = serializer.serialize(input)
        assertNotNull(serialized)
        val output = serializer.deserialize(serialized, String::class.java)
        assertNotNull(output)
        assertEquals(input, output)
    }

    @Test
    fun validate_Int() {
        val input = Random().nextInt()
        val serialized = serializer.serialize(input)
        assertNotNull(serialized)
        val output = serializer.deserialize(serialized, Int::class.java)
        assertNotNull(output)
        assertEquals(input, output)
    }

    @Test
    fun validate_Float() {
        val input = Random().nextFloat()
        val serialized = serializer.serialize(input)
        assertNotNull(serialized)
        val output = serializer.deserialize(serialized, Float::class.java)
        assertNotNull(output)
        assertEquals(input, output)
    }

    @Test
    fun validate_Double() {
        val input = Random().nextDouble()
        val serialized = serializer.serialize(input)
        assertNotNull(serialized)
        val output = serializer.deserialize(serialized, Double::class.java)
        assertNotNull(output)
        assertEquals(input, output)
    }

    @Test
    fun validate_Boolean() {
        val input = Random().nextBoolean()
        val serialized = serializer.serialize(input)
        assertNotNull(serialized)
        val output = serializer.deserialize(serialized, Boolean::class.java)
        assertNotNull(output)
        assertEquals(input, output)
    }

    @Test
    fun ensure_String() {
        val serialized = serializer.serialize(UUID.randomUUID().toString())
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Boolean::class.java
            )
        }
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Int::class.java
            )
        }
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Float::class.java
            )
        }
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Double::class.java
            )
        }
    }

    @Test
    fun ensure_Boolean() {
        val serialized = serializer.serialize(Random().nextBoolean())
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                String::class.java
            )
        }
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Int::class.java
            )
        }
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Float::class.java
            )
        }
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Double::class.java
            )
        }
    }

    @Test
    fun ensure_Int() {
        val serialized = serializer.serialize(Random().nextInt())
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                String::class.java
            )
        }
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Boolean::class.java
            )
        }
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Float::class.java
            )
        }
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Double::class.java
            )
        }
    }

    @Test
    fun ensure_Float() {
        val serialized = serializer.serialize(Random().nextFloat())
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                String::class.java
            )
        }
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Int::class.java
            )
        }
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Boolean::class.java
            )
        }
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Double::class.java
            )
        }
    }

    @Test
    fun ensure_Double() {
        val serialized = serializer.serialize(Random().nextDouble())
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                String::class.java
            )
        }
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Int::class.java
            )
        }
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Float::class.java
            )
        }
        assertThrows<DeserializeException> {
            serializer.deserialize(
                serialized,
                Boolean::class.java
            )
        }
    }

}