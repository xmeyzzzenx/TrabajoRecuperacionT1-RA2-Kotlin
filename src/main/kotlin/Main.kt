import kotlinx.coroutines.*

fun main() {

    println("HILO BASICO:")
    ejemploHiloBasico()

    println("\nSECUENCIAL VS CONCURRENTE:")
    compararSecuencialVsConcurrente()

    println("\nPRODUCTOR - CONSUMIDOR:")
    productorConsumidor()

    println("\nERRORES:")
    demoErrores()

    println("\nCORRUTINAS")
    demoCorrutina()
}

// 1. HILO BASICO

fun ejemploHiloBasico(){

    println("Inicio del programa")

    val hilo = Thread {
        println("Hola soy un hilo")
    }

    hilo.start()
    hilo.join()

    println("Fin del programa")
}

// 2. SECUENCIAL VS CONCURRENTE

fun compararSecuencialVsConcurrente() {

    println("\nSecuencial:")

    val inicioSecuencial = System.currentTimeMillis()

    tarea("A")
    tarea("B")

    val finSecuencial = System.currentTimeMillis()

    println("Tiempo secuencial: ${finSecuencial - inicioSecuencial} ms")

    println("\nConcurrente:")

    val inicioConcurrente = System.currentTimeMillis()

    val hilo1 = Thread {
        tarea("A")
    }

    val hilo2 = Thread {
        tarea("B")
    }

    hilo1.start()
    hilo2.start()

    hilo1.join()
    hilo2.join()

    val finConcurrente = System.currentTimeMillis()

    println("Tiempo concurrente: ${finConcurrente - inicioConcurrente} ms")
}

// FUNCION TAREA

fun tarea(nombre: String) {
    println("Tarea $nombre iniciada")
    Thread.sleep(2000)
    println("Tarea $nombre terminada")
}

// 3. PRODUCTOR - CONSUMIDOR

fun productorConsumidor() {

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
                    println("No hay datos para consumir")
                }
            }
        }
    }

    productor.start()
    consumidor.start()

    productor.join()
    consumidor.join()
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