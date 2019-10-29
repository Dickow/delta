package dev.dickow.delta.core.jackson.objects

import dev.dickow.delta.core.DeltaField

open class SimpleObject {
    var name: DeltaField<String>? = null
    var number: DeltaField<Int>? = null
}