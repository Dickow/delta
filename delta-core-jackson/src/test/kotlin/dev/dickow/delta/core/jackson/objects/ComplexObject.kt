package dev.dickow.delta.core.jackson.objects

import dev.dickow.delta.core.DeltaField
import dev.dickow.delta.core.unsetField

class ComplexObject {
    var simpleInnerObject: DeltaField<SimpleObject>? = null
    var otherField: Int? = null
    var deltaList: DeltaField<List<String>> = unsetField()
}