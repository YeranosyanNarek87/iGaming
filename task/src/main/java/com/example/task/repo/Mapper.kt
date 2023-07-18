package com.example.igaming.com.example.task.repo

import com.example.igaming.com.example.task.domain.StarData
import com.example.igaming.com.example.task.domain.StarsListData

interface Mapper<SOURCE, RESULT> {
    fun map(s: SOURCE): RESULT
    fun map(s: List<SOURCE>): List<RESULT> {
        return s.map { map(it) }
    }
}

val starInfoToDataMapper = object : Mapper<StarsResponses?, StarData> {
    override fun map(s: StarsResponses?): StarData =
        StarData(
            name = s?.name ?: "",
            height = s?.height ?: "",
            mass = s?.mass ?: "",
            hairColor = s?.hairColor ?: "",
            skinColor = s?.skinColor ?: "",
            eyeColor = s?.eyeColor ?: "",
            birthYear = s?.birthYear ?: "",
            gender = s?.gender ?: "",
            homeWorld = s?.homeWorld ?: "",
            films = s?.films ?: emptyList(),
            species = s?.species ?: emptyList(),
            vehicles = s?.vehicles ?: emptyList(),
            starships = s?.starships ?: emptyList(),
            created = s?.created ?: "",
            edited = s?.edited ?: "",
            url = s?.url ?: ""
        )
}

val starListInfoToDataMapper = object : Mapper<StarsListResponse?, StarsListData> {
    override fun map(s: StarsListResponse?): StarsListData =
        StarsListData(
            count = s?.count ?: -1,
            next = s?.next ?: "",
            previous = s?.previous ?: "",
            results = starInfoToDataMapper.map(s?.results ?: emptyList())
        )
}