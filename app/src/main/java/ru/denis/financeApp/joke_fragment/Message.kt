package ru.denis.financeApp.joke_fragment

import com.google.gson.annotations.SerializedName

//Объекты для парсинга JSON строки.
data class Message (
    @SerializedName("type")
    var type: String?,
    val value: List<Jokes>?
)

data class Jokes(
    val id: String?,
    val joke: String?
)