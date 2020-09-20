package com.example.model

import com.example.utlis.Coordinate
import javafx.scene.paint.Color

data class Creature(val coordinate: Coordinate) {
    val color = Color(0.1, 0.1, 0.1, 0.1)
}