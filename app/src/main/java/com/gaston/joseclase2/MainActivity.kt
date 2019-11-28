package com.gaston.joseclase2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this)[MainViewmodel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val deviceId = editText.text.toString().trim()
            observeData(deviceId)
        }
    }

    fun observeData(deviceId:String){
        viewModel.fetchDeviceData(deviceId).observe(this, Observer {
            textView.text = "Tipo: ${it.devType}"
        })

        viewModel.fetchEstadoGlobal(deviceId).observe(this, Observer {
            val armado = it.armado.toInt()
            when(armado){
                0 -> {
                    Toast.makeText(this,"Armado es $armado",Toast.LENGTH_SHORT).show()
                    button.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                }

                1 -> {
                    Toast.makeText(this,"Armado es $armado",Toast.LENGTH_SHORT).show()
                    button.setBackgroundColor(resources.getColor(R.color.colorAccent))
                }

                2 -> {
                    Toast.makeText(this,"Armado es $armado",Toast.LENGTH_SHORT).show()
                    button.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
                    button.visibility = View.GONE
                }

                3 -> {
                    Toast.makeText(this,"Armado es $armado",Toast.LENGTH_SHORT).show()
                    button.setBackgroundColor(resources.getColor(R.color.colorButton))
                }

                else -> { Toast.makeText(this,"No es posible cambiar a $armado",Toast.LENGTH_SHORT).show() }

            }
        })
    }
}
