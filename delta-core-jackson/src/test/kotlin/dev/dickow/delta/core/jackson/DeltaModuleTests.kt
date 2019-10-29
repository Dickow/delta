package dev.dickow.delta.core.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import dev.dickow.delta.core.jackson.objects.ComplexObject
import dev.dickow.delta.core.jackson.objects.SimpleObject
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

internal class DeltaModuleTests {
    private val objectMapper: ObjectMapper = ObjectMapper()

    init {
        objectMapper.registerModule(DeltaModule())
    }

    @Test
    internal fun `deserialize simple object`() {
        val simpleObjectJson = loadResource("simpleObject.json")
        val simpleObject = objectMapper.readValue(simpleObjectJson, SimpleObject::class.java)

        assertNotNull(simpleObject.name)
        assertTrue(simpleObject.name!!.fieldIsSet)
        assertEquals("Hey there", simpleObject.name!!.fieldValue)

        assertNotNull(simpleObject.number)
        assertTrue(simpleObject.number!!.fieldIsSet)
        assertEquals(19, simpleObject.number!!.fieldValue)
    }

    @Test
    internal fun `deserialize complex object`() {
        val complexObjectJson = loadResource("complexObject.json")
        val complexObject = objectMapper.readValue(complexObjectJson, ComplexObject::class.java)

        assertNotNull(complexObject.simpleInnerObject)
        assertTrue(complexObject.simpleInnerObject!!.fieldIsSet)
        val simpleInnerObject = complexObject.simpleInnerObject!!.fieldValue

        assertNotNull(simpleInnerObject)
        assertNotNull(simpleInnerObject.name)
        assertTrue(simpleInnerObject.name!!.fieldIsSet)
        assertEquals("Hey there", simpleInnerObject.name!!.fieldValue)
        assertNull(simpleInnerObject.number)

        assertNull(complexObject.otherField)
        assertNotNull(complexObject.deltaList)
        assertTrue(complexObject.deltaList.fieldIsSet)
        assertEquals("Hello", complexObject.deltaList.fieldValue!![0])
        assertEquals("World", complexObject.deltaList.fieldValue!![1])
    }

    fun loadResource(resource: String): String {
        return this.javaClass.getResource("/$resource").readText()
    }
}