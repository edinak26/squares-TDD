package com.example.utlis

typealias Grid<T> = Array<Array<T>>

operator fun <T> Grid<T>.get(coordinate: Coordinate): T =
    this[coordinate.row][coordinate.column]

operator fun <T> Grid<T>.set(coordinate: Coordinate, value: T) {
    this[coordinate.row][coordinate.column] = value
}
