package dev.dickow.delta.core

import java.lang.reflect.Field

data class DeltaField<T>(val fieldValue: T?, val fieldIsSet: Boolean)

fun <T> unsetField(): DeltaField<T> {
    return DeltaField(null, false)
}

fun <T> nullField(): DeltaField<T> {
    return DeltaField(null, true)
}

fun <T> setField(value: T): DeltaField<T> {
    return DeltaField(value, true)
}

fun resolveField(obj: Any, fieldName: String): DeltaField<Any> {
    val sourceClass = obj.javaClass
    val field = sourceClass.getDeclaredField(fieldName)
    return resolveField(obj, field)
}

fun resolveField(obj: Any, field: Field): DeltaField<Any> {
    try {
        field.isAccessible = true
        return when(val fieldValue = field.get(obj)) {
            null -> nullField()
            else -> setField(resolveInnerField(fieldValue))
        }
    }
    finally {
        field.isAccessible = false
    }
}

fun resolveInnerField(value: Any) : Any {
    return when(value) {
        is Boolean -> value
        is String -> value
        is Number -> value
        is Char -> value
        is Float -> value
        is Double -> value
        is Collection<*> -> value
        is Array<*> -> value
        else -> resolveObjectFields(value)
    }
}

fun resolveObjectFields(obj: Any): List<DeltaField<Any>> {
    val clazz = obj.javaClass
    val declaredFields = clazz.declaredFields
    val fields = clazz.fields

    val allFields = mutableListOf<Field>()
    allFields.addAll(declaredFields)
    allFields.addAll(fields)

    return allFields.map { resolveField(obj, it) }
}