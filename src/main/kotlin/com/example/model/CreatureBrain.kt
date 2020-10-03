package com.example.model

data class CreatureBrain(val creatureActions: Array<CreatureAction>) {
    var nextActionIndex = 0

    fun nextAction() = creatureActions[nextActionIndex]
        .also { nextActionIndex = (nextActionIndex + 1) % creatureActions.size }
}