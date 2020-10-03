package com.example.model

import com.example.utlis.Direction

sealed class CreatureAction {
    data class Move(val direction: Direction) : CreatureAction()
    data class Eat(val direction: Direction) : CreatureAction()
    data class Reproduce(val direction: Direction) : CreatureAction()
    object Charge : CreatureAction()

    companion object {
        val values = listOf(
            *Direction.values().map { Move(it) }.toTypedArray(),
            *Direction.values().map { Eat(it) }.toTypedArray(),
            *Direction.values().map { Reproduce(it) }.toTypedArray(),
//            Eat(Direction.RIGHT),
//            Move(Direction.RIGHT),
//            Move(Direction.UP,),
//            Reproduce(Direction.RIGHT),
            Charge
        )
    }
}