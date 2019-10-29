package dev.dickow.delta.core.jackson

import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import dev.dickow.delta.core.DeltaField

class DeltaModule : SimpleModule(
    "Delta module",
    Version(1, 0, 0, "1.0", "dickow.dev", "delta-core-jackson"),
    mutableMapOf<Class<*>, JsonDeserializer<*>>(Pair(DeltaField::class.java, DeltaDeserializer())),
    listOf()
)