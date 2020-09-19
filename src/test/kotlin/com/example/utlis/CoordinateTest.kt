package com.example.utlis

import org.junit.Test
import kotlin.test.assertEquals

internal class CoordinateTest {

    @Test
    fun shouldReturnValueFromGridByCoordinate() {
        val grid: Grid<Int> = arrayOf(
            arrayOf(1, 2, 3),
            arrayOf(4, 5, 6),
            arrayOf(7, 8, 9),
        )
        assertEquals(grid[Coordinate(1,2)], 6)
    }

    @Test
    fun shouldSetGridValueByCoordinate() {
        val grid: Grid<Int> = arrayOf(
            arrayOf(1, 2, 3),
            arrayOf(4, 5, 6),
            arrayOf(7, 8, 9),
        )

        grid[Coordinate(2,0)] = 42

        assertEquals(grid[2][0], 42)
    }
}

