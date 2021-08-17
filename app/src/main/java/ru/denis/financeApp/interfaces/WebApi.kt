package ru.denis.financeApp.interfaces

import retrofit2.Response
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.denis.financeApp.joke_fragment.Message

interface WebApi {
    @GET("/jokes/random/{count}")
    suspend fun getJokes(@Path("count") count: Int): Response<Message>
}