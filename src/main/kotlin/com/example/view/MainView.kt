package com.example.view

import com.example.controller.MainController
import com.example.controller.WORLD_GRID_COLUMNS_SIZE
import com.example.controller.WORLD_GRID_ROWS_SIZE
import javafx.scene.paint.Color
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    val SCREEN_HEIGHT = 600.0
    val SCREEN_WIDTH = 600.0

    val controller: MainController by inject()

    override val root = pane {
        for (lineNum in 0 until WORLD_GRID_ROWS_SIZE) {
            for (columnNum in 0 until WORLD_GRID_COLUMNS_SIZE) {
                rectangle {
                    height = SCREEN_HEIGHT / WORLD_GRID_ROWS_SIZE
                    width = SCREEN_WIDTH / WORLD_GRID_COLUMNS_SIZE
                    x = columnNum * height
                    y = lineNum * width
                    fillProperty().bind(controller.rectanglesColorsGrid[lineNum][columnNum])
                }
            }
        }
    }

//        runAsync {
//            world.create()
//            while (true) {
//                runLater {
//                    clearPixels()
//                    drawCells(world.cells)
//                    world.runCycle()
//                }
//                sleep(2 +world.cells.size/1000L)
//            }
//        }
//    }

}
