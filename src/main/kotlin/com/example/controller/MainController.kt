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
            world.addCreature(creaturesFactory.creatureBy(coordinateOf(50, 50)))
            world.addCreature(creaturesFactory.creatureBy(coordinateOf(51, 50)))
            world.addCreature(creaturesFactory.creatureBy(coordinateOf(50, 51)))
            world.addCreature(creaturesFactory.creatureBy(coordinateOf(51, 51)))
            while (true) {
                runLater {
                    clearCreature()
                    drawCreatures(world.creatures)

                    world.moveCreatureTo(world.creatures[0], world.creatures[0].coordinate.move(Direction.UP))
                    world.moveCreatureTo(world.creatures[1], world.creatures[1].coordinate.move(Direction.RIGHT))
                    world.moveCreatureTo(world.creatures[2], world.creatures[2].coordinate.move(Direction.LEFT))
                    world.moveCreatureTo(world.creatures[3], world.creatures[3].coordinate.move(Direction.DOWN))
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