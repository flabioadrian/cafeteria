package com.example.cafeapp.data.repository

import com.example.cafeteria.contract.CafeteriaContrato
import com.example.cafeteria.model.VariedadCafe

//CafeRepository
class CafeteriaModel: CafeteriaContrato.Model{
    override fun obtenerVariedadesDeCafe(): List<VariedadCafe> {
        return listOf(
            VariedadCafe(1, "Espresso Americano", "Clásico café americano, suave y aromático.", 2.50, 100),
            VariedadCafe(2, "Latte Macchiato", "Leche manchada con un toque de espresso.", 3.50, 80),
            VariedadCafe(3, "Mocha", "Deliciosa mezcla de espresso, chocolate y leche vaporizada.", 4.00, 60),
            VariedadCafe(4, "Cappuccino", "Espresso, leche vaporizada y abundante espuma.", 3.25, 75),
            VariedadCafe(5, "Cold Brew", "Infusión de café en frío durante 12 horas, intenso y bajo en acidez.", 4.50, 50),
            VariedadCafe(6, "Caramel Frappé", "Bebida helada de café, leche, hielo y sirope de caramelo.", 5.00, 40),
            VariedadCafe(7, "Geisha de Panamá", "Variedad exótica de alta puntuación, notas florales y afrutadas.", 8.00, 15),
            VariedadCafe(8, "Bourbon Rojo", "Café de origen único, cuerpo medio y notas a frutos rojos.", 5.50, 30),
            VariedadCafe(9, "Kopi Luwak", "El café más exótico de Indonesia, procesado por civetas.", 15.00, 10),
            VariedadCafe(10, "Ristretto", "Un espresso corto y mucho más concentrado, sabor intenso.", 2.25, 90)
        )
    }

    override fun obtenerCafePorId(id: Int): VariedadCafe? {
        return obtenerVariedadesDeCafe().find { it.id == id }
    }
}