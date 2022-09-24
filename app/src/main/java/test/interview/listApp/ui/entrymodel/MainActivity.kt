package test.interview.listApp.ui.entrymodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import test.interview.listApp.R
import test.interview.listApp.databinding.ActivityMainBinding
import test.interview.listApp.data.model.EntryModel
import test.interview.listApp.data.model.network.CategoriesApi
import test.interview.listApp.data.repository.CategoriesRepository

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var entryModelAdapter: EntryModelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        entryModelAdapter = EntryModelAdapter()
        entryModelAdapter.createContext(this)

        activityMainBinding.listView.also {
            it.adapter = entryModelAdapter
            it.setHasFixedSize(true)
        }

        val api = CategoriesApi()
        val repository = CategoriesRepository(api)
        viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]

        viewModel.getEntryModels()

        viewModel.entryModels.observe(this) {
            entryModelAdapter.insertItemsToList(it.entries as ArrayList<EntryModel>)
        }
    }
}