package com.gaston.joseclase2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Gastón Saillén on 28 November 2019
 */
class MainViewmodel: ViewModel() {

    private val repo = Repo()
    fun fetchDeviceData(deviceId:String):LiveData<Device>{
        val mutableData = MutableLiveData<Device>()
        repo.getDeviceData(deviceId).observeForever {
            mutableData.value = it
        }

        return mutableData
    }

    fun fetchEstadoGlobal(deviceId: String):LiveData<EstadoGlobal>{
        val mutableData = MutableLiveData<EstadoGlobal>()
        repo.getEstadoGlobal(deviceId).observeForever {
            mutableData.value = it
        }

        return mutableData
    }
}