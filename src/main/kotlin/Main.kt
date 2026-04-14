fun main() {
    println("Inicio del programa")

    val hilo = Thread {
        println("Hola desde el hilo")
    }

    hilo.start()
    hilo.join()

    println("Fin del programa")
}