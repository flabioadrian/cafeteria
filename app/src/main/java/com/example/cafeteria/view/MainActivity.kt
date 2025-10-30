package com.example.cafeteria.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeapp.data.repository.CafeteriaModel
import com.example.cafeteria.R
import com.example.cafeteria.contract.CafeteriaContrato
import com.example.cafeteria.presenter.CafeteriaPresenter

class MainActivity : AppCompatActivity(), CafeteriaContrato.View {

    private lateinit var spinnerCafes: Spinner
    private lateinit var btnVerDetalles: Button

    private val presenter: CafeteriaContrato.Presenter by lazy {
        CafeteriaPresenter(this, CafeteriaModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerCafes = findViewById(R.id.spinnerCafes)
        btnVerDetalles = findViewById(R.id.btnVerDetalles)

        spinnerCafes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.onCafeSeleccionado(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        btnVerDetalles.setOnClickListener {
            presenter.onVerDetallesClick()
        }

        presenter.cargarVariedadesCafe()
    }

    override fun mostrarVariedadesEnSpinner(nombresCafes: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresCafes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCafes.adapter = adapter
    }

    override fun navegarADetalleCafe(idCafe: Int) {
        val intent = Intent(this, DetallesActivity::class.java).apply {
            putExtra("ID_CAFE", idCafe)
        }
        startActivity(intent)
    }

    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}