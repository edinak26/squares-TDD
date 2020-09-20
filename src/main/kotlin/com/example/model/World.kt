package com.example.model

import com.example.controller.WORLD_GRID_COLUMNS_SIZE
import com.example.controller.WORLD_GRID_ROWS_SIZE
import com.example.utlis.Coordinate
import com.example.utlis.Grid
import com.example.utlis.get
import com.example.utlis.set
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.paint.Color
import tornadofx.toProperty

class World {
    val creatures = mutableListOf<Creature>()
    private val creaturesGrid: Grid<Creature?> =
        Array(WORLD_GRID_ROWS_SIZE) { f ->
            Array(WORLD_GRID_COLUMNS_SIZE) {
                null
            }
        }

    fun addCreature(creature: Creature): Boolean {
        if (creaturesGrid[creature.coordinate] != null) return false
        creaturesGrid[creature.coordinate] = creature
        return creatures.add(creature)
    }

    fun moveCreatureTo(creature: Creature, coordinate: Coordinate): Boolean {
        if(creaturesGrid[creature.coordinate] != creature) return false
        if(creaturesGrid[coordinate]!= null) return false
        creature.coordinate = coordinate
        return true
    }


}