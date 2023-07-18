package com.example.igaming.com.example.task.repo

sealed class ActionResult<T> {
    data class SUCCESS<T>(val data: T) : ActionResult<T>()
    data class ERROR<T>(val message: String) : ActionResult<T>()
}