package com.gaston.joseclase2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

/**
 * Created by Gastón Saillén on 28 November 2019
 */
class Repo {

    private val db = FirebaseDatabase.getInstance().reference

    fun getDeviceData(deviceId:String):LiveData<Device>{

        return object: MutableLiveData<Device>(){
            private val mutableLiveData = this

            private var query: Query? = null
            private var listener: ValueEventListener = object: ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if(dataSnapshot.exists()){
                        val device = dataSnapshot.getValue(Device::class.java)
                        mutableLiveData.value = device
                    }
                }

                override fun onCancelled(dataError: DatabaseError) {
                    Log.e("Error","handle error callback")
                }
            }

            override fun onActive() {
                super.onActive()
                query?.removeEventListener(listener)
                val query = db.child(deviceId).child("config/device")
                this.query = query
                query.addListenerForSingleValueEvent(listener)
            }

            override fun onInactive() {
                super.onInactive()
                query?.removeEventListener(listener)
                query = null
            }
        }
    }


    fun getEstadoGlobal(deviceId:String):LiveData<EstadoGlobal>{

        return object: MutableLiveData<EstadoGlobal>(){

            private val mutableData = this

            private var query:Query? = null
            private var listener:ValueEventListener = object: ValueEventListener{

                override fun onDataChange(p0: DataSnapshot) {
                    if(p0.exists()){
                        val estadoGlobal = p0.getValue(EstadoGlobal::class.java)
                        mutableData.value = estadoGlobal
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    Log.e("Error","handle error callback")
                }
            }

            override fun onActive() {
                super.onActive()
                query?.removeEventListener(listener)
                val query = db.child(deviceId).child("estadoGlobal")
                this.query = query
                query.addValueEventListener(listener)
            }

            override fun onInactive() {
                super.onInactive()
                query?.removeEventListener(listener)
                query = null
            }
        }
    }
}