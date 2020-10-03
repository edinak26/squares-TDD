package com.example.model

import com.example.model.CreatureAction.*
import com.example.utlis.Coordinate
import com.example.utlis.Coordinate.Companion.coordinateOf
import com.example.utlis.Direction.Companion.move
import javafx.scene.paint.Color
import kotlin.random.Random

class Creature private constructor(
    val creatureNumber: Long = 0
) {
    var chargeEffectivity: Double = 1.0
    var eatingEffectivity: Double = 0.0
    var energy: Double = 0.0
    lateinit var color: Color
    lateinit var coordinate: Coordinate
    lateinit var world: World
    lateinit var creatureFactory: CreatureFactory
    lateinit var brain: CreatureBrain

    override fun hashCode() = creatureNumber.hashCode()
    override fun equals(other: Any?) =
        other is Creature && creatureNumber == other.creatureNumber

    override fun toString() = "Creature-$creatureNumber"

    fun runCycle() = executeAction(brain.nextAction())

    fun executeAction(action: CreatureAction) = when (action) {
        is Move -> world.moveCreatureTo(this, coordinate.move(action.direction))
        is Eat -> world.eatIn(this, coordinate.move(action.direction))
        is Reproduce -> if (energy > 100) {
            energy = 0.0;world.addCreature(createChild(action))
        } else Unit
        is Charge -> world.chargeCreature(this)
    }

    fun createChild(action: Reproduce): Creature {
        val child = creatureFactory.creatureBy(coordinate.move(action.direction))
        child.brain = CreatureBrain(brain.creatureActions.copyOf())
        child.chargeEffectivity = chargeEffectivity
        if (Random.nextDouble() <= 0.1) {
            child.brain.creatureActions[Random.nextInt(child.brain.creatureActions.size)] =
                CreatureAction.values.random()
            val childBrain = child.brain.creatureActions
            val red = childBrain.filter { it is Eat }.size / childBrain.size.toDouble()
            val green = if(chargeEffectivity/5.0<0) 0.0 else if (chargeEffectivity/5.0>1.0) 1.0 else chargeEffectivity/5.0
            val blue = childBrain.filter { it is Move }.size / childBrain.size.toDouble()
            child.color = Color(0.0, green, 0.0, 1.0)
        } else child.color = color
        if (child.brain.creatureActions.filter { it is Charge }.size / brain.creatureActions.size.toDouble() > 0.5) {
            child.chargeEffectivity += 0.1
        } else child.chargeEffectivity -= 0.1

        return child
    }

    class CreatureFactory(val world: World) {
        private var lastCreatureNumber = 0L

        fun creatureBy(coordinate: Coordinate) =
            Creature(++lastCreatureNumber).also {
                it.color = Color(0.1, 0.2, 0.3, 0.4)
                it.coordinate = coordinate
                it.world = world
                it.creatureFactory = this
            }

        fun creatureIn(row: Int, column: Int) =
            creatureBy(coordinateOf(row, column))
    }
}