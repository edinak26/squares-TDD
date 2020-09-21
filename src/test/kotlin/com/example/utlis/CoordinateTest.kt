package com.example.utlis

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GridTest {

    @Test
    fun shouldReturnValueFromGridByCoordinate() {
        val grid: Grid<Int> = arrayOf(
            arrayOf(1, 2, 3),
            arrayOf(4, 5, 6),
            arrayOf(7, 8, 9),
        )
        assertEquals(6, grid[Coordinate(1, 2)])
    }

    @Test
    fun shouldSetGridValueByCoordinate() {
        val grid: Grid<Int> = arrayOf(
            arrayOf(1, 2, 3),
            arrayOf(4, 5, 6),
            arrayOf(7, 8, 9),
        )

        grid[Coordinate(2, 0)] = 42

        assertEquals(42, grid[2][0])
    }
}

