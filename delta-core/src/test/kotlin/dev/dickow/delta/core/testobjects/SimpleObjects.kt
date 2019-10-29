package dev.dickow.delta.core.testobjects

open class StringObject {
    var stringField: String? = null
}

open class IntegerObject {
    var intField: Int? = null
}

open class LongObject {
    var longField: Long? = null
}

open class ListObject {
    var listField: List<String>? = null
}

open class CompositeObject {
    var listObjField: ListObject? = null
    var intObjField: IntegerObject? = null
    var longObjField: LongObject? = null
    var stringObjField: StringObject? = null
}