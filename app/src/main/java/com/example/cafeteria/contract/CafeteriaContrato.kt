package com.example.cafeteria.contract

import com.example.cafeteria.model.VariedadCafe

class CafeteriaContrato {
    interface View {
        fun mostrarVariedadesEnSpinner(nombresCafes: List<String>)
        fun navegarADetalleCafe(idCafe: Int)
        fun mostrarError(mensaje: String)
    }

    interface Presenter {
        fun cargarVariedadesCafe()
        fun onCafeSeleccionado(indice: Int)
        fun onVerDetallesClick()
    }

    interface Model {
        fun obtenerVariedadesDeCafe(): List<VariedadCafe>
        fun obtenerCafePorId(id: Int): VariedadCafe?
    }
}