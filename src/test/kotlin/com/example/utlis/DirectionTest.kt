package com.example.utlis

import com.example.utlis.Direction.Companion.move
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DirectionTest {
    @Test
    fun shouldMoveUp() {
        val coordinate = Coordinate(1, 1)
        assertEquals(Coordinate(0, 1), coordinate.move(Direction.UP))
    }
    @Test
    fun shouldMoveRight() {
        val coordinate = Coordinate(1, 1)
        assertEquals(Coordinate(1, 2), coordinate.move(Direction.RIGHT))
    }

    @Test
    fun shouldMoveDown() {
        val coordinate = Coordinate(1, 1)
        assertEquals(Coordinate(2, 1), coordinate.move(Direction.DOWN))
    }

    @Test
    fun shouldMoveLeft() {
        val coordinate = Coordinate(1, 1)
        assertEquals(Coordinate(1, 0), coordinate.move(Direction.LEFT))
    }
}