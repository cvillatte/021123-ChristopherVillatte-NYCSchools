package com.example.firstnetworkapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstnetworkapi.service.ServiceApi
import com.example.firstnetworkapi.view.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.app.Application
import com.example.firstnetworkapi.model.Schools
import com.example.firstnetworkapi.model.SchoolsItem
import dagger.Module

@Module
class ApplicationModule(private val application: Application) {

    getSchoolresults()
    getSchoolItem()
    getschoolresultsItem()
}

var fragmentState: Boolean = false

private val _Schoolresults: MutableLiveData<UIState<List<>> = MutableLiveData(UIState.LOADING)
val Schoolresults: LiveData<UIState> get() = Schoolresults

private val _SchoolsItem : MutableLiveData<UIState<> = MutableLiveData(UIState.LOADING)
val _SchoolsItem: LiveData<UIState> get() = SchoolsItem

private val SchoolresultsItem : MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
val SchoolresultsItem: LiveData<UIState> get() = SchoolsItem

fun getSchoolItem(id: String? = null) {
    id?.let {
        viewModelScope.launch(ioDispatcher) {
            Schoolresults.getSchoolsItembyId(it).collect {
                Schools.postValue(it)
            }
        }
    }



private const val TAG = "SchoolsViewModel"

class SchoolsViewModel @inject constructor(
    private val serviceApi: ServiceApi,
    private val ioDispatcher :CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    init {
        fun get SchoolsItem(id:String?=null) {
        }


        }
    }

    private val _schools: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val schools: LiveData<UIState> get() = _schools

    fun getAllSchools() {
        viewModelScope.launch(ioDispatcher) {
            _schools.postValue(UIState.LOADING)

            try {
                val response = serviceApi.getAllSchools()
                if (response.isSuccessful) {
                    response.body()?.let {
                        // this post value works in main thread and worker thread
                        _schools.postValue(UIState.SUCCESS(it))
                        withContext(Dispatchers.Main) {
                            // this set value only works in the main thread
                            // _schools.value = UIState.SUCCESS(it)
                            Log.d(TAG, "onCreate: $it")
                        }
                    } ?: throw Exception("Error null schools response")
                } else {
                    throw Exception(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                _schools.postValue(UIState.ERROR(e))
                Log.e(TAG, "onCreate: ${e.localizedMessage}", e)
            }
        }
    }
}