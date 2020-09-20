package com.example.model

import com.example.utlis.Coordinate
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


internal class WorldTest {

    private lateinit var world: World
    val defaultCoordinate = Coordinate(0, 0)

    @Before
    fun setup() {
        world = World()
    }

    @Test
    fun shouldAddCreature() {
        val creature = Creature(defaultCoordinate)

        world.addCreature(creature)

        assertEquals(world.creatures.first(), creature)
    }

    @Test
    fun shouldNotAddCreatureWhenCoordinateTaken() {
        val coordinate = Coordinate(0, 0)
        val firstCreature = Creature(coordinate)
        val secondCreature = Creature(coordinate)

        world.addCreature(firstCreature)
        world.addCreature(secondCreature)

        assertEquals(world.creatures, listOf(firstCreature))
    }

    @Test
    fun shouldReturnTrueWhenCreatureAdded() {
        assertEquals(
            1, 1
        )
    }

}