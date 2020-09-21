package com.example.utlis

import com.example.controller.WORLD_GRID_COLUMNS_SIZE
import com.example.controller.WORLD_GRID_ROWS_SIZE
import com.example.utlis.Coordinate.Companion.coordinateOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CoordinateTest {
    @Test
    fun shouldBeInTopBorder() {
        assertEquals(Coordinate(WORLD_GRID_ROWS_SIZE - 3, 3), coordinateOf(-3, 3))
    }

    @Test
    fun shouldBeInBottomBorder() {
        assertEquals(Coordinate(5, 2), coordinateOf(WORLD_GRID_ROWS_SIZE + 5, 2))
    }

    @Test
    fun shouldBeInRightBorder() {
        assertEquals(Coordinate(1, 5), coordinateOf(1, WORLD_GRID_COLUMNS_SIZE + 5))
    }

    @Test
    fun shouldBeInLeftBorder() {
        assertEquals(Coordinate(1, WORLD_GRID_COLUMNS_SIZE - 7), coordinateOf(1, -7))
    }
}
