package com.example.model

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
        creatureFactory = Creature.CreatureFactory()
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

        world.eatIn(creature, Coordinate(1, 1))

        assertEquals(listOf(creature), world.creatures)
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

        world.eatIn(creature, coordinateOf(1, 1))

        assertEquals(18.0, creature.energy)
    }

}