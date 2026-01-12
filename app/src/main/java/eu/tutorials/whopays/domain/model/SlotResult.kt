package eu.tutorials.whopays.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SlotResult(
    val number1: Int,
    val operator: String,
    val number2: Int,
    val result: Int
)