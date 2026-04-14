fun main() {
    println("Secuencial:")

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

fun tarea(nombre: String) {
    println("Tarea $nombre iniciada")
    Thread.sleep(2000)
    println("Tarea $nombre terminada")
}