package com.example.model

import com.example.controller.BASE_CHARGE_AMOUNT
import com.example.model.Creature.CreatureFactory
import com.example.utlis.Coordinate.Companion.coordinateOf
import com.example.utlis.Direction
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CreatureTest {

    lateinit var world: World
    lateinit var creatureFactory: CreatureFactory

    @BeforeEach
    fun setup() {
        world = World()
        creatureFactory = CreatureFactory(world)
    }

    @Test
    fun shouldExecuteMoveAction() {
        val creature = creatureFactory.creatureIn(0, 0)
        world.addCreature(creature)
        creature.executeAction(CreatureAction.Move(Direction.RIGHT))
        assertEquals(coordinateOf(0, 1), creature.coordinate)
    }

    @Test
    fun shouldExecuteEatAction() {
        val creature = creatureFactory.creatureIn(0, 0)
        world.addCreature(creature)
        world.addCreature(creatureFactory.creatureIn(0, 1))
        creature.executeAction(CreatureAction.Eat(Direction.RIGHT))
        assertEquals(listOf(creature), world.creatures)
    }

    @Test
    fun shouldExecuteReproduceAction() {
        val creature = creatureFactory.creatureIn(0, 0)
        world.addCreature(creature)
        creature.executeAction(CreatureAction.Reproduce(Direction.RIGHT))
        assertEquals(2, world.creatures.size)
        assertEquals(false, world.addCreature(creatureFactory.creatureIn(0, 1)))
    }

    @Test
    fun shouldExecuteChargeAction() {
        val creature = creatureFactory.creatureIn(0, 0).apply {
            energy = 17.0
            chargeEffectivity = 0.5
        }
        world.addCreature(creature)
        creature.executeAction(CreatureAction.Charge)
        assertEquals(BASE_CHARGE_AMOUNT * 0.5 + 17, creature.energy)
    }
}