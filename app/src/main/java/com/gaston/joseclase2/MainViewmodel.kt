package com.gaston.joseclase2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * Created by Gastón Saillén on 28 November 2019
 */
class MainViewmodel: ViewModel() {

    private val repo = Repo()
    private val selectedDeviceId = MutableLiveData<String>()
    private val selectedEstadoId = MutableLiveData<String>()

    // Al setear el nuevo valor a selectedDeviceId hacemos trigger a repo.getDeviceData(deviceId)
    // y como este metodo retorna un LiveData, cada vez que cambie va a ejecutar selectedDevice
    // si el selectedDeviceId cambia, se vuelve a hacer la llamada pero con el nuevo id
    fun onSelectedDeviceChanged(deviceId: String) {
        selectedDeviceId.value = deviceId
    }

    fun onSelectedEstadoGlobal(deviceId: String){
        selectedEstadoId.value = deviceId
    }

    // Transformation solo se va a llamar si hay al menos un observer escuchando por sus datos
    // switchmap funciona solo cuando retornamos un LiveData
    val selectedDevice = Transformations.switchMap(selectedDeviceId) { deviceId ->
        repo.getDeviceData(deviceId)
    }

    val selectedEstado = Transformations.switchMap(selectedEstadoId) { deviceId ->
        repo.getEstadoGlobal(deviceId)
    }
}