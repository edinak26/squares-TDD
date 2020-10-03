package com.example.model

import com.example.model.Creature.CreatureFactory
import com.example.utlis.Coordinate
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CreatureFactoryTest {
    val defaultCoordinate = Coordinate(0, 0)
    lateinit var creatureFactory : CreatureFactory

    @BeforeEach
    fun setup(){
        creatureFactory = CreatureFactory(World())
    }

    @Test
    fun shouldBeUnique() {
        val firstCreature = creatureFactory.creatureBy(defaultCoordinate)
        val secondCreature = creatureFactory.creatureBy(defaultCoordinate)

        assertFalse(firstCreature == secondCreature)
    }

    @Test
    fun shouldBeNumberOneWhenFirstCreatureCreated() {
        val firstCreature = creatureFactory.creatureBy(defaultCoordinate)

        assertEquals(1, firstCreature.creatureNumber)
    }

    @Test
    fun shouldIncrementCreatureNumber() {
        val firstCreature = creatureFactory.creatureBy(defaultCoordinate)

        val secondCreature = creatureFactory.creatureBy(defaultCoordinate)

        assertEquals(firstCreature.creatureNumber + 1, secondCreature.creatureNumber)
    }
}