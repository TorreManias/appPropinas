package ni.edu.uca.tiempodepropinas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }

    private fun calcularPropina() {
        val textoEnTV = binding.costoServicio.text.toString()
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
}