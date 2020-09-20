package com.example.controller

import com.example.model.Creature
import com.example.model.Creature.CreatureFactory
import com.example.utlis.Coordinate
import com.example.utlis.Grid
import com.example.utlis.get
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
                    clearCreature()
                    val cell = CreatureFactory().creatureBy(
                        Coordinate(
                            Random.nextInt(WORLD_GRID_ROWS_SIZE),
                            Random.nextInt(WORLD_GRID_COLUMNS_SIZE)
                        )
                    )
                    drawCreature(cell)
                }
                sleep(100)
            }
        }
    }

    private fun drawCreatures(creatures: List<Creature>) = creatures.forEach(::drawCreature)

    private fun drawCreature(creature: Creature) {
        rectanglesColorsGrid[creature.coordinate].value = creature.color
    }

    private fun clearCreature() = rectanglesColorsGrid.forEach { it.forEach { it.value = BACKGROUND_COLOR } }

}