package com.example.nikita.profitmanager.api


import com.example.nikita.profitmanager.models.CoinCap
// импортируем даные из COINCAP
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerApi {
    //добавляем к ссылке на апи серверное приставку тикер
    @GET("ticker")
    //вызываем функцию для загрузки листа с максимальным колличеством эелементов = 20
    fun loadData(@Query ("limit") limit:Int = 50):Deferred<List<CoinCap>>
}
