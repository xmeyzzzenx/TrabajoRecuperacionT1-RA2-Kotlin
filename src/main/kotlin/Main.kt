import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

// ==============================
// MAIN
// ==============================
fun main() {

    println("=== HILO BASICO ===")
    demoHiloBasico()

    println("\n=== SECUENCIAL VS CONCURRENTE (HILOS) ===")
    demoComparacionHilos()

    println("\n=== PRODUCTOR-CONSUMIDOR (HILOS) ===")
    demoComunicacionHilos()

    println("\n=== ERRORES (HILOS) ===")
    demoErroresHilos()

    println("\n=== CORRUTINA BASICA ===")
    demoCorrutinaBasica()

    println("\n=== SECUENCIAL VS CONCURRENTE (CORRUTINAS) ===")
    demoComparacionCorrutinas()

    println("\n=== PRODUCTOR-CONSUMIDOR (CORRUTINAS) ===")
    demoComunicacionCorrutinas()

    println("\n=== ERRORES (CORRUTINAS) ===")
    demoErroresCorrutinas()
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

    println("\n--- Ejecución Secuencial ---")

    val inicioSecuencial = System.currentTimeMillis()

    tareaHilo("A")
    tareaHilo("B")

    val finSecuencial = System.currentTimeMillis()
    println("Tiempo secuencial: ${finSecuencial - inicioSecuencial} ms")

    println("\n--- Ejecución Concurrente ---")

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

    println("Comunicacion finalizada")
}


// ====================================
// 4. ERRORES (HILOS)
// ====================================
fun demoErroresHilos() {

    val hiloError = Thread {
        try {
            println("Hilo con error iniciado")
            throw Exception("Error simulado en hilo")
        } catch (e: Exception) {
            println("Error capturado: ${e.message}")
        }
    }

    hiloError.start()
    hiloError.join()

    println("Hilo de error finalizado")
}

// ====================================
// 5. CORRUTINA BÁSICA
// ====================================
fun demoCorrutinaBasica() = runBlocking {

    println("Inicio de la corrutina")

    val trabajo = async {
        delay(1000)
        println("Ejecutando corrutina...")
        "Resultado de la corrutina"
    }

    println(trabajo.await())

    println("Corrutina finalizada")
}

// =========================================
// 6. SECUENCIAL VS CONCURRENTE (CORRUTINAS)
// =========================================
fun demoComparacionCorrutinas() = runBlocking {

    println("\n--- Corrutinas Secuencial ---")

    val inicioSecuencial = System.currentTimeMillis()

    tareaCorrutina("A")
    tareaCorrutina("B")

    val finSecuencial = System.currentTimeMillis()
    println("Tiempo secuencial corrutinas: ${finSecuencial - inicioSecuencial} ms")

    println("\n--- Corrutinas Concurrente ---")

    val inicioConcurrente = System.currentTimeMillis()

    val job1 = async { tareaCorrutina("A") }
    val job2 = async { tareaCorrutina("B") }

    job1.await()
    job2.await()

    val finConcurrente = System.currentTimeMillis()
    println("Tiempo concurrente corrutinas: ${finConcurrente - inicioConcurrente} ms")
}

suspend fun tareaCorrutina(nombre: String) {
    println("Tarea $nombre iniciada")
    delay(2000)
    println("Tarea $nombre finalizada")
}

// ====================================
// 7. PRODUCTOR-CONSUMIDOR (CORRUTINAS)
// ====================================
fun demoComunicacionCorrutinas() = runBlocking {

    val canal = Channel<Int>()

    val productor = launch {
        for (i in 1..5) {
            println("Produciendo $i")
            canal.send(i)
            delay(500)
        }
        canal.close()
    }

    val consumidor = launch {
        for (valor in canal) {
            println("Consumiendo $valor")
            delay(1000)
        }
    }

    productor.join()
    consumidor.join()

    println("Comunicación finalizada")
}

// ====================================
// 8. ERRORES (CORRUTINAS)
// ====================================
fun demoErroresCorrutinas() = runBlocking {

    val job = launch {
        try {
            println("Corrutina con error iniciada")
            throw Exception("Error simulado en corrutina")
        } catch (e: Exception) {
            println("Error capturado: ${e.message}")
        }
    }

    job.join()

    println("Corrutina de error finalizada")
}