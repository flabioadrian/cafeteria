package com.example.cafeteria.contract

import com.example.cafeteria.model.VariedadCafe

class DetallesContract {
    interface View {
        fun mostrarDetallesCafe(cafe: VariedadCafe)
        fun mostrarTotalPagar(total: Double)
        fun mostrarErrorStock(mensaje: String)
        fun ocultarErrorStock()
        fun mostrarError(mensaje: String)
    }

    interface Presenter {
        fun cargarDetallesCafe(idCafe: Int)
        fun onBotonCalcularClick(cantidad: Int)
    }

    interface Model {
        fun calcularTotalYValidarStock(cantidad: Int, cafe: VariedadCafe ): Double
    }
}