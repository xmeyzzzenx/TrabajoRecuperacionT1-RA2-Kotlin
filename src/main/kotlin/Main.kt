import kotlinx.coroutines.*

// ==============================
// MAIN
// ==============================
fun main() {

    println("=== HILO BASICO ===")
    demoHiloBasico()

    println("=== SECUENCIAL VS CONCURRENTE (HILOS) ===")
    demoComparacionHilos()

    println("=== PRODUCTOR-CONSUMIDOR (HILOS) ===")
    demoComunicacionHilos()

    println("\nERRORES:")
    demoErrores()

    println("\nCORRUTINAS")
    demoCorrutina()
}

// ==============================
// 1. HILO BASICO
// ==============================
fun demoHiloBasico() {

    println("Inicio del programa")

    val hilo = Thread {
        println("Ejecutando hilo...")
    }

    hilo.start()
    hilo.join()

    println("Hilo finalizado")
}

// ====================================
// 2. SECUENCIAL VS CONCURRENTE (HILOS)
// ====================================
fun demoComparacionHilos() {

    val inicioSecuencial = System.currentTimeMillis()

    tareaHilo("A")
    tareaHilo("B")

    val finSecuencial = System.currentTimeMillis()
    println("Tiempo secuencial: ${finSecuencial - inicioSecuencial} ms")

    val inicioConcurrente = System.currentTimeMillis()

    val hilo1 = Thread { tareaHilo("A") }
    val hilo2 = Thread { tareaHilo("B") }

    hilo1.start()
    hilo2.start()

    hilo1.join()
    hilo2.join()

    val finConcurrente = System.currentTimeMillis()
    println("Tiempo concurrente: ${finConcurrente - inicioConcurrente} ms")
}

// ====================================
// FUNCION TAREA (HILOS)
// ====================================
fun tareaHilo(nombre: String) {
    println("Tarea $nombre iniciada")
    Thread.sleep(2000)
    println("Tarea $nombre finalizada")
}

// ====================================
// 3. PRODUCTOR-CONSUMIDOR (HILOS)
// ====================================
fun demoComunicacionHilos() {

    val lista = mutableListOf<Int>()
    val lock = Object()

    val productor = Thread {
        for (i in 1..5) {
            synchronized(lock) {
                println("Produciendo $i")
                lista.add(i)
            }
            Thread.sleep(500)
        }
    }

    val consumidor = Thread {
        for (i in 1..5) {
            Thread.sleep(1000)
            synchronized(lock) {
                if (lista.isNotEmpty()) {
                    val valor = lista.removeAt(0)
                    println("Consumiendo $valor")
                } else {
                    println("Lista vacía")
                }
            }
        }
    }

    productor.start()
    consumidor.start()

    productor.join()
    consumidor.join()

    println("Comunicación finalizada")
}

fun demoErrores() {

    val hiloError = Thread {
        try {
            println("Hilo con error iniciado")
            throw Exception("Error simulado")
        } catch (e: Exception) {
            println("Error capturado: ${e.message}")
        }
    }

    hiloError.start()
    hiloError.join()
}

// 5.CORRUTINAS
fun demoCorrutina() = runBlocking {

    println("Corrutina iniciada")

    val job = launch {
        delay(1000)
        println("Trabajo dentro de la corrutina")
    }

    job.join()

    println("Corrutina terminada")
}