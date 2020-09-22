package com.example.controller

import com.example.model.Creature
import com.example.model.Creature.CreatureFactory
import com.example.model.World
import com.example.utlis.Coordinate
import com.example.utlis.Coordinate.Companion.coordinateOf
import com.example.utlis.Direction
import com.example.utlis.Direction.Companion.move
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
            val world = World()
            val creaturesFactory = CreatureFactory()
            for (row in 0 until WORLD_GRID_ROWS_SIZE) {
                for (column in 0 until WORLD_GRID_COLUMNS_SIZE) {
                    if (row != 50 || column != 50)
                        world.addCreature(creaturesFactory.creatureIn(row, column))
                }
            }
            val eater = creaturesFactory.creatureIn(50, 50)
            eater.color = Color(1.0, 0.0, 0.0, 1.0)
            world.addCreature(eater)
            while (true) {
                runLater {
                    clearCreature()
                    drawCreatures(world.creatures)
                    val newCoordinate = eater.coordinate.move(Direction.values().random())
                    world.eatIn(eater, newCoordinate)
                    world.moveCreatureTo(eater, newCoordinate)
                    world.creatures.forEach {
                        if(it != eater) world.moveCreatureTo(it,it.coordinate.move(Direction.DOWN))
                    }
                }
                sleep(200)
            }
        }
    }

    private fun drawCreatures(creatures: List<Creature>) = creatures.forEach(::drawCreature)

    private fun drawCreature(creature: Creature) {
        rectanglesColorsGrid[creature.coordinate].value = creature.color
    }

    private fun clearCreature() = rectanglesColorsGrid.forEach { it.forEach { it.value = BACKGROUND_COLOR } }

}