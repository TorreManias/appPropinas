package ni.edu.uca.tiempodepropinas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import ni.edu.uca.tiempodepropinas.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.text.NumberFormat.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calcularBtn.setOnClickListener{ calcularPropina() }
        binding.costoServicioEditText.setOnKeyListener{ view, keyCode, _ -> handleKeyEvent(view, keyCode)}
    }

    private fun calcularPropina() {
        val textoEnTV = binding.costoServicioEditText.text.toString()
        val costo = textoEnTV.toDoubleOrNull()

        if (costo == null || costo == 0.0){
            mostrarPropina(0.0)
            return
        }

        val porcentajePropina = when(binding.opcionesPropina.checkedRadioButtonId) {
            R.id.opc_20_per -> 0.20
            R.id.opc_18_per -> 0.18
            else -> 0.15
        }

        var propina = porcentajePropina * costo

        if(binding.redondearSwitch.isChecked)
            propina = kotlin.math.ceil(propina)

        mostrarPropina(propina)
    }

    private fun mostrarPropina(tip:Double){
        val propinaFormateada = NumberFormat.getCurrencyInstance().format(tip)
        binding.resultadoPropina.text = getString(R.string.propina, propinaFormateada)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

}