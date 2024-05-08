package com.rinbows.soft.pranklam.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CollectViewModel : ViewModel() {
    private var likeData: MutableLiveData<List<DataListDTO>> = MutableLiveData<List<DataListDTO>>()

    init {
        viewModelScope.launch {
            likeData.value = AppDatabase.dataBase.getDataListDao().getCollectData()
        }
    }

    fun update() {
        viewModelScope.launch {
            likeData.value = AppDatabase.dataBase.getDataListDao().getCollectData()
        }
    }

    fun getList() = likeData

    override fun onCleared() {
        super.onCleared()
    }

}