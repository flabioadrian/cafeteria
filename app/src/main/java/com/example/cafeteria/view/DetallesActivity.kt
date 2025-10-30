package com.example.cafeteria.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeapp.data.repository.CafeteriaModel
import com.example.cafeteria.R
import com.example.cafeteria.contract.DetallesContract
import com.example.cafeteria.model.VariedadCafe
import com.example.cafeteria.presenter.DetallesPresenter

class DetallesActivity : AppCompatActivity(), DetallesContract.View {

    private lateinit var tvNombre: TextView
    private lateinit var tvDescripcion: TextView
    private lateinit var tvPrecio: TextView
    private lateinit var tvStock: TextView
    private lateinit var etCantidad: EditText
    private lateinit var tvTotal: TextView
    private lateinit var tvError: TextView

    private val presenter: DetallesContract.Presenter by lazy {
        DetallesPresenter(this, CafeteriaModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //inicializar Vistas
        tvNombre = findViewById(R.id.tvNombre)
        tvDescripcion = findViewById(R.id.tvDescripcion)
        tvPrecio = findViewById(R.id.tvPrecio)
        tvStock = findViewById(R.id.tvStock)
        etCantidad = findViewById(R.id.etCantidad)
        tvTotal = findViewById(R.id.tvTotal)
        tvError = findViewById(R.id.tvError)

        // configurar eventos
        etCantidad.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val cantidad = s?.toString()?.toIntOrNull() ?: 0
                presenter.onCantidadCambiada(cantidad)
            }
        })


        val idCafe = intent.getIntExtra("ID_CAFE", -1)
        if (idCafe != -1) {
            presenter.cargarDetallesCafe(idCafe)
        } else {
            mostrarError("ID de café no válido")
        }
    }

    override fun mostrarDetallesCafe(cafe: VariedadCafe) {
        tvNombre.text = cafe.nombre
        tvDescripcion.text = cafe.descripcion
        tvPrecio.text = "Precio: $${cafe.precio}"
        tvStock.text = "Stock disponible: ${cafe.stock}"
    }

    override fun mostrarTotalPagar(total: Double) {
        tvTotal.text = "Total a Pagar: $${String.format("%.2f", total)}"
        tvTotal.setTextColor(resources.getColor(android.R.color.black, theme))
    }

    override fun mostrarErrorStock(mensaje: String) {
        tvError.text = mensaje
        tvError.visibility = TextView.VISIBLE
        tvTotal.setTextColor(resources.getColor(android.R.color.holo_red_dark, theme))
    }

    override fun ocultarErrorStock() {
        tvError.visibility = TextView.GONE
    }

    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}