package dev.dickow.delta.core

import dev.dickow.delta.core.testobjects.CompositeObject
import dev.dickow.delta.core.testobjects.IntegerObject
import dev.dickow.delta.core.testobjects.ListObject
import dev.dickow.delta.core.testobjects.StringObject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DeltaFieldTest {
    @Test
    internal fun `resolve value for string field`() {
        val stringObject = StringObject()
        stringObject.stringField = "Field is set"

        val field = resolveField(stringObject, "stringField")
        assertTrue(field.fieldIsSet)
        assertNotNull(field.fieldValue)
        assertEquals(stringObject.stringField, field.fieldValue)
    }

    @Test
    internal fun `resolve value for string field that is null`() {
        val stringObject = StringObject()

        val field = resolveField(stringObject, "stringField")
        assertTrue(field.fieldIsSet)
        assertNull(field.fieldValue)
    }

    @Test
    internal fun `resolve all values for simple object`() {
        val obj = CompositeObject()
        obj.intObjField = IntegerObject()
        obj.intObjField?.intField = 5
        obj.listObjField = ListObject()
        obj.listObjField?.listField = listOf("Hello", "from", "inner", "list")

        val deltaFields = resolveObjectFields(obj)
        assertEquals(4, deltaFields.size)
    }
}

