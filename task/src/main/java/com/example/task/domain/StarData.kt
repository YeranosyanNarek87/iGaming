package com.example.igaming.com.example.task.domain

data class StarData(
    val name: String = "",
    val height: String = "",
    val mass: String = "",
    val hairColor: String = "",
    val skinColor: String = "",
    val eyeColor: String = "",
    val birthYear: String = "",
    val gender: String = "",
    val homeWorld: String = "",
    val films: List<String> = emptyList(),
    val species: List<String> = emptyList(),
    val vehicles: List<String> = emptyList(),
    val starships: List<String> = emptyList(),
    val created: String = "",
    val edited: String = "",
    val url: String = ""
) : BaseItemData
