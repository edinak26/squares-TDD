package com.example.utlis

import com.example.controller.WORLD_GRID_COLUMNS_SIZE
import com.example.controller.WORLD_GRID_ROWS_SIZE

data class Coordinate(val row: Int, val column: Int) {
    companion object {
        fun coordinateOf(row: Int, column: Int) =
            Coordinate(Math.floorMod(row, WORLD_GRID_ROWS_SIZE), Math.floorMod(column, WORLD_GRID_COLUMNS_SIZE))
    }
}