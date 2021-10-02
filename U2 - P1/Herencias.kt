import kotlin.math.PI
import kotlin.math.sqrt

fun main() {
    
    val cabina = cabinaCuadrada(4, 25.50)
    with(cabina) {
        println("\nCabina cuadrada\n----------")
        println("- Capacidad: ${capacidad}")
        println("- Material: ${materialConstruccion}")
        println("- Tiene espacio?: ${tieneEspacio()}")
        conseguirCuarto()
        println("- Tiene espacio?: ${tieneEspacio()}")
        conseguirCuarto()
        println("- Tiene espacio?: ${tieneEspacio()}")
        conseguirCuarto()
        println("- Area: ${area()}")
    }
    
    val cabinaCircular = CabinaCircular(3, 15.20)
    with(cabinaCircular) {
        println("\nCabina circular")
        println("Material: ${materialConstruccion}")
        println("Capacidad: ${capacidad}")
        println("Tiene espacio? ${tieneEspacio()}")
        println("- Area: ${area()}")
        println("- Tamaño de alfombra: ${calcularAlfombra()}")
	}
    
    val torre = TorreCircular(4, 32.15)
    with(torre) {
        println("\nTorre circular\n----------")
        println("- Capacidad: ${capacidad}")
        println("- Material: ${materialConstruccion}")
        println("- Tiene espacio?: ${tieneEspacio()}")
        println("- Area: ${area()}")
        println("- Tamaño de alfombra: ${calcularAlfombra()}")
    }
}

abstract class Vivienda(private var residentes : Int) {
    abstract val materialConstruccion : String
    abstract val capacidad : Int
    
    fun tieneEspacio() : Boolean {
        return capacidad > residentes
    }
    
    abstract fun area() : Double
    
    fun conseguirCuarto() {
        if (capacidad > residentes) {
            residentes++
            println("Tienes un cuarto!")
        } else {
            println("Ya no hay cuartos disponibles")
        }
	}
}

class cabinaCuadrada(residentes : Int, val lado: Double) : Vivienda(residentes) {
    override val materialConstruccion = "Madera"
    override val capacidad = 6
    
    override fun area(): Double {
		return lado * lado
	}
}

open class CabinaCircular(
    residentes : Int,
	val radio : Double) : Vivienda(residentes) {
    override val materialConstruccion = "Paja"
    override val capacidad = 4
    
    override fun area(): Double {
    	return PI * radio * radio
	}
    
    fun calcularAlfombra(): Double {
        val diametro = 2 * radio
        return sqrt(diametro * diametro / 2)
	}
}

class TorreCircular(
    residentes : Int, 
    radio : Double,
    val pisos : Int = 2) : CabinaCircular(residentes, radio)
{
    override val materialConstruccion = "Piedra"
    override val capacidad = 4 * pisos
    
    override fun area(): Double {
    	return super.area() * pisos
	}
}