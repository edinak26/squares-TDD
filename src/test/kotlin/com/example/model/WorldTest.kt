package com.example.model

import com.example.utlis.Coordinate
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

}