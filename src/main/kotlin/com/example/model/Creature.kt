package com.example.model

import com.example.utlis.Coordinate
import com.example.utlis.Coordinate.Companion.coordinateOf
import javafx.scene.paint.Color

class Creature private constructor(
    val creatureNumber: Long = 0
) {
    var eatingEffectivity: Double = 0.0
    var energy: Double = 0.0
    lateinit var color: Color
    lateinit var coordinate: Coordinate

    override fun hashCode() = creatureNumber.hashCode()
    override fun equals(other: Any?) =
        other is Creature && creatureNumber == other.creatureNumber

    override fun toString() = "Creature-$creatureNumber"

    class CreatureFactory {
        private var lastCreatureNumber = 0L

        fun creatureBy(coordinate: Coordinate) =
            Creature(++lastCreatureNumber).also {
                it.color = Color(0.1, 0.2, 0.3, 0.4)
                it.coordinate = coordinate
            }

        fun creatureIn(row: Int, column: Int) =
            creatureBy(coordinateOf(row, column))
    }
}