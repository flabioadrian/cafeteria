package com.example.cafeteria.presenter

import com.example.cafeapp.data.repository.CafeteriaModel
import com.example.cafeteria.contract.CafeteriaContrato

class CafeteriaPresenter(private var vista: CafeteriaContrato.View, private val modelo: CafeteriaModel ) : CafeteriaContrato.Presenter {

    private val cafes = modelo.obtenerVariedadesDeCafe()
    private var cafeSeleccionadoId: Int? = null

    override fun cargarVariedadesCafe() {
        try {
            val nombresCafes = mutableListOf<String>()
            for (cafe in cafes) {
                nombresCafes.add(cafe.nombre)
            }
            vista.mostrarVariedadesEnSpinner(nombresCafes)
            if (cafes.isNotEmpty()) {
                cafeSeleccionadoId = cafes[0].id
            } else {
                cafeSeleccionadoId = null
            }
        } catch (e: Exception) {
            vista.mostrarError("Error al cargar las variedades de café")
        }
    }

    override fun onCafeSeleccionado(indice: Int) {
        if (indice in cafes.indices) {
            cafeSeleccionadoId = cafes[indice].id
        }
    }

    override fun onVerDetallesClick() {
        val idCafe = cafeSeleccionadoId
        if (idCafe != null) {
            vista.navegarADetalleCafe(idCafe)
        } else {
            vista.mostrarError("Por favor seleccione un café")
        }
    }
}