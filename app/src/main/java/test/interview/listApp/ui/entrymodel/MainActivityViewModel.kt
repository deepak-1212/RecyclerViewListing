package test.interview.listApp.ui.entrymodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import test.interview.listApp.utils.BackgroundThread
import test.interview.listApp.data.model.CategoriesModel
import test.interview.listApp.data.repository.CategoriesRepository

class MainActivityViewModel(private val repository: CategoriesRepository) : ViewModel() {

    private lateinit var job: Job

    private val _entryModels = MutableLiveData<CategoriesModel>()
    val entryModels: LiveData<CategoriesModel>
        get() = _entryModels

    fun getEntryModels() {
        job = BackgroundThread.ioThenMain(
            { repository.getCategories() },
            {
                _entryModels.value = it
            }
        )
    }

    override fun onCleared() {
        super.onCleared()

        if (::job.isInitialized) job.cancel()

    }

}