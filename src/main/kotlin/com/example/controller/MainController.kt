package com.example.controller

import com.example.model.Cell
import com.example.utlis.Coordinate
import com.example.utlis.Grid
import com.example.utlis.get
import com.example.view.MainView
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.paint.Color
import tornadofx.Controller
import tornadofx.runLater
import tornadofx.toProperty
import java.lang.Thread.sleep
import kotlin.random.Random

val WORLD_GRID_ROWS_SIZE = 100
val WORLD_GRID_COLUMNS_SIZE = 100

class MainController : Controller() {
    val BACKGROUND_COLOR = Color(0.0, 0.0, 0.0, 1.0)

    val rectanglesColorsGrid: Grid<SimpleObjectProperty<Color>> =
        Array(WORLD_GRID_ROWS_SIZE) { f ->
            Array(WORLD_GRID_COLUMNS_SIZE) {
                BACKGROUND_COLOR.toProperty()
            }
        }

    init {
        runAsync {
            while (true) {
                runLater {
                    clearGrid()
                    val cell = Cell(
                        Coordinate(
                            Random.nextInt(WORLD_GRID_ROWS_SIZE),
                            Random.nextInt(WORLD_GRID_COLUMNS_SIZE)
                        ), Color(
                            Random.nextDouble(),
                            Random.nextDouble(),
                            Random.nextDouble(),
                            Random.nextDouble()
                        )
                    )
                    drawCell(cell)
                }
                sleep(100)
            }
        }
    }

    private fun drawCells(cells: List<Cell>) = cells.forEach(::drawCell)

    private fun drawCell(cell: Cell) {
        rectanglesColorsGrid[cell.coordinate].value = cell.color
    }

    private fun clearGrid() = rectanglesColorsGrid.forEach { it.forEach { it.value = BACKGROUND_COLOR } }

}