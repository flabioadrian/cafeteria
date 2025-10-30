package com.example.cafeteria.model

import com.example.cafeteria.contract.DetallesContract

class DetallesModel : DetallesContract.Model{
    override fun calcularTotalYValidarStock(
        cantidad: Int,
        cafe: VariedadCafe
    ): Double {

        if (cantidad <= 0 || cantidad > cafe.stock) {
            return 0.0
        }

        val total = cantidad * cafe.precio
        return total
    }
}