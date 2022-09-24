package test.interview.listApp.data.repository

import test.interview.listApp.data.model.network.CategoriesApi
import test.interview.listApp.data.model.network.SafeApiRequest

class CategoriesRepository(private val categoriesApi: CategoriesApi): SafeApiRequest() {
    suspend fun getCategories() = apiRequest { categoriesApi.getCategories() }
}