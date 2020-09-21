package com.example.utlis

enum class Direction(val rowIncrement: Int, val columnIncrement: Int) {
    UP(-1, 0),
    DOWN(1, 0),
    RIGHT(0, 1),
    LEFT(0, -1);

    companion object {
        fun Coordinate.move(direction: Direction) =
            Coordinate(row + direction.rowIncrement, column + direction.columnIncrement)
    }
}