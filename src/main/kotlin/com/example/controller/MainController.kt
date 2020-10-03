package com.example.controller

import com.example.model.CreatureBrain
import com.example.model.Creature
import com.example.model.Creature.CreatureFactory
import com.example.model.CreatureAction
import com.example.model.World
import com.example.utlis.Grid
import com.example.utlis.get
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.paint.Color
import tornadofx.Controller
import tornadofx.runLater
import tornadofx.toProperty
import java.lang.Thread.sleep

val WORLD_GRID_ROWS_SIZE = 100
val WORLD_GRID_COLUMNS_SIZE = 100
val BASE_CHARGE_AMOUNT = 10

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
            val creaturesFactory = CreatureFactory(world)
            val a = creaturesFactory.creatureIn(50, 50)
            a.brain = CreatureBrain(Array(50) { CreatureAction.values.random() })
            a.color = Color(0.9, 0.1, 0.1, 1.0)
            world.addCreature(a)
            while (true) {
                runLater {
                    clearCreature()
                    drawCreatures(world.creatures)
                    repeat(4){
                        ArrayList(world.creatures).forEach { it.runCycle() }

                    }
                }
                sleep(2 + world.creatures.size/1000L)
            }
        }
    }

    private fun drawCreatures(creatures: List<Creature>) = creatures.forEach(::drawCreature)

    private fun drawCreature(creature: Creature) {
        rectanglesColorsGrid[creature.coordinate].value = creature.color
    }

    private fun clearCreature() = rectanglesColorsGrid.forEach { it.forEach { it.value = BACKGROUND_COLOR } }

}