package com.example.cafeteria.presenter

import com.example.cafeapp.data.repository.CafeteriaModel
import com.example.cafeteria.contract.DetallesContract
import com.example.cafeteria.model.DetallesModel
import com.example.cafeteria.model.VariedadCafe

class DetallesPresenter(
    private var view: DetallesContract.View?,
    private val repository: CafeteriaModel,
    private val model: DetallesModel = DetallesModel()
) : DetallesContract.Presenter {

    private var cafeActual: VariedadCafe? = null

    override fun cargarDetallesCafe(idCafe: Int) {
        try {
            val cafe = repository.obtenerCafePorId(idCafe)
            if (cafe != null) {
                cafeActual = cafe
                view?.mostrarDetallesCafe(cafe)
            } else {
                view?.mostrarError("Café no encontrado")
            }
        } catch (e: Exception) {
            view?.mostrarError("Error al cargar los detalles del café")
        }
    }

    override fun onCantidadCambiada(cantidad: Int) {
        val cafe = cafeActual

        if (cafe != null) {
            val resultado = model.calcularTotalYValidarStock(cantidad, cafe)

            if (resultado == 0.0) {
                view?.mostrarErrorStock("Error en la operación Stock invalido")
                view?.mostrarTotalPagar(resultado)
            } else {
                view?.ocultarErrorStock()
                view?.mostrarTotalPagar(resultado)
            }
        }
    }
}