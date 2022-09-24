package test.interview.listApp.data.model.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import test.interview.listApp.data.model.CategoriesModel

interface CategoriesApi {

    @GET("entries")
    suspend fun getCategories(): Response<CategoriesModel>

    companion object {
        operator fun invoke(): CategoriesApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.publicapis.org/")
                .build()
                .create(CategoriesApi::class.java)

        }
    }
}