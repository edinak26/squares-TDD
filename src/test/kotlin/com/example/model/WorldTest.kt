package com.example.model

import com.example.controller.BASE_CHARGE_AMOUNT
import com.example.utlis.Coordinate
import com.example.utlis.Coordinate.Companion.coordinateOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class WorldTest {

    private lateinit var world: World
    val defaultCoordinate = Coordinate(0, 0)
    lateinit var creatureFactory: Creature.CreatureFactory

    @BeforeEach
    fun setup() {
        world = World()
        creatureFactory = Creature.CreatureFactory(world)
    }

    @Test
    fun shouldAddCreature() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)

        world.addCreature(creature)

        assertEquals(creature, world.creatures.first())
    }

    @Test
    fun shouldNotAddCreatureWhenCoordinateTaken() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)

        world.addCreature(creature)
        world.addCreature(creature)

        assertEquals(listOf(creature), world.creatures)
    }

    @Test
    fun shouldReturnTrueWhenCreatureAdded() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)

        assertEquals(true, world.addCreature(creature))
    }

    @Test
    fun shouldReturnFalseWhenCreatureNotAdded() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)
        world.addCreature(creature)

        assertEquals(false, world.addCreature(creature))
    }

    @Test
    fun shouldMoveCreature() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)
        world.addCreature(creature)

        world.moveCreatureTo(creature, Coordinate(1, 1))

        assertEquals(Coordinate(1, 1), creature.coordinate)
    }

    @Test
    fun shouldNotMoveCreatureWhenCoordinateTaken() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)
        world.addCreature(creatureFactory.creatureBy(Coordinate(1, 1)))

        world.moveCreatureTo(creature, Coordinate(1, 1))

        assertEquals(defaultCoordinate, creature.coordinate)
    }

    @Test
    fun shouldReturnTrueWhenCreatureMoved() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)
        world.addCreature(creature)

        assertEquals(true, world.moveCreatureTo(creature, Coordinate(1, 1)))
    }

    @Test
    fun shouldReturnFalseWhenCreatureNotMoved() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)
        world.addCreature(creatureFactory.creatureBy(Coordinate(1, 1)))

        assertEquals(false, world.moveCreatureTo(creature, Coordinate(1, 1)))
    }

    @Test
    fun shouldNotMoveUnknownCreature() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)

        world.moveCreatureTo(creature, Coordinate(1, 1))

        assertEquals(defaultCoordinate, creature.coordinate)
    }


    @Test
    fun shouldReturnFalseWhenUnknownCreatureNotMoved() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)

        assertEquals(false, world.moveCreatureTo(creature, Coordinate(1, 1)))
    }

    @Test
    fun shouldClearCellAfterMoving() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)
        world.addCreature(creature)
        world.moveCreatureTo(creature, Coordinate(1, 1))

        assertEquals(true, world.addCreature(creatureFactory.creatureBy(defaultCoordinate)))
    }

    @Test
    fun shouldShouldNotAddCreatureWhenCellTakenByMoving() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)
        world.addCreature(creature)
        world.moveCreatureTo(creature, Coordinate(1, 1))

        assertEquals(false, world.addCreature(creatureFactory.creatureBy(Coordinate(1, 1))))
    }

    @Test
    fun shouldEatCreature() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)
        world.addCreature(creature)
        world.addCreature(creatureFactory.creatureBy(Coordinate(1, 1)))

        val returnValue = world.eatIn(creature, Coordinate(1, 1))

        assertEquals(listOf(creature), world.creatures)
        assertEquals(true, returnValue)
    }

    @Test
    fun shouldAddCreatureOnEatenCellCoordinate() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)
        world.addCreature(creature)
        world.addCreature(creatureFactory.creatureBy(Coordinate(1, 1)))

        world.eatIn(creature, Coordinate(1, 1))

        assertEquals(true, world.addCreature(creatureFactory.creatureBy(Coordinate(1, 1))))
    }

    @Test
    fun shouldGiveEnergyFromEatenCreature() {
        val creature = creatureFactory.creatureBy(defaultCoordinate).apply {
            energy = 13.0
            eatingEffectivity = 0.2
        }
        world.addCreature(creature)
        world.addCreature(creatureFactory.creatureIn(1, 1).apply {
            energy = 25.0
        })

        val returnValue = world.eatIn(creature, coordinateOf(1, 1))

        assertEquals(18.0, creature.energy)
        assertEquals(true, returnValue)
    }


    @Test
    fun shouldNotEatWhenCreatureIsUnknown() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)
        val eatedCreature = creatureFactory.creatureIn(1, 1)
        world.addCreature(eatedCreature)

        val returnValue = world.eatIn(creature, Coordinate(1, 1))

        assertEquals(listOf(eatedCreature), world.creatures)
        assertEquals(false, returnValue)
    }

    @Test
    fun shouldChargeCreature() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)
        creature.energy = 8.0
        creature.chargeEffectivity = 0.3

        world.addCreature(creature)
        world.chargeCreature(creature)

        assertEquals(8.0 + BASE_CHARGE_AMOUNT * 0.3, creature.energy)
    }

    @Test
    fun shouldNotChargeUnknownCreature() {
        val creature = creatureFactory.creatureBy(defaultCoordinate)
        creature.energy = 8.0
        creature.chargeEffectivity = 0.3

        world.chargeCreature(creature)

        assertEquals(8.0, creature.energy)
    }
}