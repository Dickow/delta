package dev.dickow.delta.core.jackson

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.deser.ContextualDeserializer
import dev.dickow.delta.core.DeltaField
import dev.dickow.delta.core.setField
import dev.dickow.delta.core.unsetField

class DeltaDeserializer : JsonDeserializer<DeltaField<*>>(), ContextualDeserializer {
    private var valueType: JavaType? = null

    override fun createContextual(ctxt: DeserializationContext, property: BeanProperty): JsonDeserializer<*> {
        val wrapperType = property.type
        val valueType = wrapperType.containedType(0)
        val deserializer = DeltaDeserializer()
        deserializer.valueType = valueType
        return deserializer
    }

    override fun deserialize(parser: JsonParser, context: DeserializationContext): DeltaField<*> {
        val value = context.readValue<Any>(parser, valueType)
        return if(value != null) {
            setField(value)
        } else {
            unsetField<Any>()
        }
    }
}