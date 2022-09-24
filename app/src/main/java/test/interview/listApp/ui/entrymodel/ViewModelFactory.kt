package test.interview.listApp.ui.entrymodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import test.interview.listApp.data.repository.CategoriesRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: CategoriesRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(repository) as T
    }

}