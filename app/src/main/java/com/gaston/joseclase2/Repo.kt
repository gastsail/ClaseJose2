package com.gaston.joseclase2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Created by Gastón Saillén on 28 November 2019
 */
class Repo {

    private val db = FirebaseDatabase.getInstance().reference
    fun getDeviceData(deviceId:String):LiveData<Device>{
        val mutableData = MutableLiveData<Device>()
        db.child(deviceId).child("config/device").addListenerForSingleValueEvent(object: ValueEventListener{

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()){
                    val device = dataSnapshot.getValue(Device::class.java)
                    mutableData.value = device
                }else{
                    mutableData.value = Device()
                    Log.e("Mala referencia","NO SE ENCONTRO LOS DATOS")
                }

            }

            override fun onCancelled(dataError: DatabaseError) {
                TODO("not implemented")
            }
        })

        return mutableData
    }




    fun getEstadoGlobal(deviceId:String):LiveData<EstadoGlobal>{

        val mutableData = MutableLiveData<EstadoGlobal>()
        db.child(deviceId).child("estadoGlobal").addValueEventListener(object: ValueEventListener{

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    val estadoGlobal = p0.getValue(EstadoGlobal::class.java)
                    mutableData.value = estadoGlobal
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }
        })

        return mutableData
    }
}