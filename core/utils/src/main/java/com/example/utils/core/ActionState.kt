package com.example.utils.core


sealed class ActionState {

    data class Open(val url: String) : ActionState()


}