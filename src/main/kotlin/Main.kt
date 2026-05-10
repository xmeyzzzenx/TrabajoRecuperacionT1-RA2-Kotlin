import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

// Proyecto RA2 - Multihilo y Sincronizacion en Kotlin
// Ximena Meyzen Calderon - 2DAM
//
// En este programa trabajo con hilos y corrutinas para ver
// como funcionan y comparar su comportamiento.

fun main() {

    println("=== SECCION 1: HILOS ===")

    println("\n-- 1.1 Hilo basico --")
    demoHiloBasico()

    println("\n-- 1.2 Secuencial vs Concurrente --")
    demoComparacionHilos()

    println("\n-- 1.3 Productor-Consumidor --")
    demoComunicacionHilos()

    println("\n-- 1.4 Gestion de errores --")
    demoErroresHilos()

    println("\n=== SECCION 2: CORRUTINAS ===")

    println("\n-- 2.1 Corrutina basica --")
    demoCorrutinaBasica()

    println("\n-- 2.2 Secuencial vs Concurrente --")
    demoComparacionCorrutinas()

    println("\n-- 2.3 Productor-Consumidor --")
    demoComunicacionCorrutinas()

    println("\n-- 2.4 Gestion de errores --")
    demoErroresCorrutinas()

    println("\n=== FIN DEL PROGRAMA ===")
}

// ============================================================
// 1.1 HILO BASICO
// ============================================================
// Un hilo es una tarea que el programa puede ejecutar de forma
// independiente mientras el resto sigue funcionando.
//
// - Thread { } : define lo que hace el hilo
// - .name      : le damos nombre para verlo en consola
// - .start()   : lo arrancamos
// - .join()    : esperamos a que termine antes de continuar
// ============================================================
fun demoHiloBasico() {

    println("Hilo basico iniciado")

    val hilo = Thread {
        println("  [${Thread.currentThread().name}] Hilo ejecutandose...")
        println("  [${Thread.currentThread().name}] Hilo finalizado")
    }

    hilo.name = "Hilo-Basico"
    hilo.start()
    hilo.join()

    println("Hilo basico finalizado correctamente")
}


// ============================================================
// 1.2 SECUENCIAL VS CONCURRENTE (HILOS)
// ============================================================
// Ejecutamos las mismas dos tareas de dos formas distintas
// para ver la diferencia de tiempo:
//
// Secuencial: A termina y luego empieza B. Tarda el doble.
// Concurrente: A y B corren a la vez. Tarda la mitad.
// ============================================================
fun demoComparacionHilos() {

    // secuencial: una tarea detras de la otra
    println("Ejecucion secuencial:")

    val inicioSecuencial = System.currentTimeMillis()

    val hiloA = Thread { tareaHilo("A") }
    val hiloB = Thread { tareaHilo("B") }

    hiloA.name = "Hilo-A"
    hiloB.name = "Hilo-B"

    hiloA.start()
    hiloA.join()
    hiloB.start()
    hiloB.join()

    val finSecuencial = System.currentTimeMillis()
    println("Tiempo secuencial: ${finSecuencial - inicioSecuencial} ms\n")

    // concurrente: las dos tareas a la vez con dos hilos
    println("Ejecucion concurrente:")

    val inicioConcurrente = System.currentTimeMillis()

    val hilo1 = Thread { tareaHilo("A") }
    val hilo2 = Thread { tareaHilo("B") }

    hilo1.name = "Hilo-A"
    hilo2.name = "Hilo-B"

    hilo1.start()
    hilo2.start()

    hilo1.join()
    hilo2.join()

    val finConcurrente = System.currentTimeMillis()
    println("Tiempo concurrente: ${finConcurrente - inicioConcurrente} ms")
}

// tarea que tarda 2 segundos en completarse
fun tareaHilo(nombre: String) {
    println("  [${Thread.currentThread().name}] Tarea $nombre iniciada")
    Thread.sleep(2000)
    println("  [${Thread.currentThread().name}] Tarea $nombre finalizada")
}


// ============================================================
// 1.3 PRODUCTOR-CONSUMIDOR (HILOS)
// ============================================================
// Un hilo produce datos y los mete en una lista compartida,
// otro hilo los saca y los consume.
//
// El problema es que si los dos acceden a la lista a la vez
// pueden causarse errores. Para evitarlo uso synchronized,
// que funciona como un candado: solo entra un hilo a la vez.
// ============================================================
fun demoComunicacionHilos() {

    println("Productor-Consumidor con hilos iniciado")

    val lista = mutableListOf<Int>()
    val lock = Object()

    val productor = Thread {
        for (i in 1..5) {
            synchronized(lock) {
                println("  [Productor] Produciendo $i")
                lista.add(i)
            }
            Thread.sleep(500)
        }
        println("  [Productor] Sin mas datos")
    }

    val consumidor = Thread {
        for (i in 1..5) {
            Thread.sleep(800)
            synchronized(lock) {
                if (lista.isNotEmpty()) {
                    val valor = lista.removeAt(0)
                    println("  [Consumidor] Consumiendo $valor")
                } else {
                    println("  [Consumidor] Lista vacia, esperando...")
                }
            }
        }
        println("  [Consumidor] Sin mas datos que consumir")
    }

    productor.name = "Hilo-Productor"
    consumidor.name = "Hilo-Consumidor"

    productor.start()
    consumidor.start()

    productor.join()
    consumidor.join()

    println("Productor-Consumidor con hilos finalizado correctamente")
}


// ============================================================
// 1.4 GESTION DE ERRORES (HILOS)
// ============================================================
// Si un hilo falla y no lo controlamos, muere sin avisar.
// Con try-catch capturamos el error y el programa sigue.
// Aqui lanzo una excepcion a proposito para demostrarlo.
// ============================================================
fun demoErroresHilos() {

    println("Gestion de errores con hilos iniciada")

    val hiloError = Thread {
        try {
            println("  [${Thread.currentThread().name}] Hilo iniciado")
            throw Exception("Error simulado en el hilo")
        } catch (e: Exception) {
            println("  [${Thread.currentThread().name}] Error capturado: ${e.message}")
        }
        println("  [${Thread.currentThread().name}] Hilo finalizado")
    }

    hiloError.name = "Hilo-Error"
    hiloError.start()
    hiloError.join()

    println("Gestion de errores con hilos finalizada correctamente")
}


// ============================================================
// 2.1 CORRUTINA BASICA
// ============================================================
// Las corrutinas funcionan parecido a los hilos pero son mas
// ligeras y faciles de usar. Kotlin las gestiona solo.
//
// - runBlocking : mantiene el programa activo hasta que todo acabe
// - launch      : lanza una corrutina que no devuelve resultado
// - async       : lanza una corrutina que si devuelve resultado
// - delay()     : pausa la corrutina sin bloquear el hilo
// - join()      : espera a que un launch termine
// - await()     : espera el resultado de un async
// ============================================================
fun demoCorrutinaBasica() = runBlocking {

    println("Corrutina basica iniciada")

    // launch: no devuelve ningun resultado
    val job = launch {
        println("  [Corrutina-launch] Corrutina ejecutandose...")
        delay(500)
        println("  [Corrutina-launch] Corrutina finalizada")
    }
    job.join()

    // async: si devuelve un resultado, lo recogemos con await()
    val deferred = async {
        delay(1000)
        println("  [Corrutina-async] Corrutina ejecutandose...")
        "Resultado devuelto por async"
    }

    val resultado = deferred.await()
    println("  [Corrutina-async] Corrutina finalizada, resultado: $resultado")

    println("Corrutina basica finalizada correctamente")
}


// ============================================================
// 2.2 SECUENCIAL VS CONCURRENTE (CORRUTINAS)
// ============================================================
// Lo mismo que con hilos pero usando corrutinas.
// El resultado es el mismo: concurrente tarda la mitad.
// ============================================================
fun demoComparacionCorrutinas() = runBlocking {

    // secuencial: esperamos a que cada una termine antes de la siguiente
    println("Ejecucion secuencial:")

    val inicioSecuencial = System.currentTimeMillis()

    tareaCorrutina("A")
    tareaCorrutina("B")

    val finSecuencial = System.currentTimeMillis()
    println("Tiempo secuencial: ${finSecuencial - inicioSecuencial} ms\n")

    // concurrente: lanzamos las dos a la vez con async
    println("Ejecucion concurrente:")

    val inicioConcurrente = System.currentTimeMillis()

    val job1 = async { tareaCorrutina("A") }
    val job2 = async { tareaCorrutina("B") }

    job1.await()
    job2.await()

    val finConcurrente = System.currentTimeMillis()
    println("Tiempo concurrente: ${finConcurrente - inicioConcurrente} ms")
}

// suspend significa que la funcion puede pausarse sin bloquear el hilo
suspend fun tareaCorrutina(nombre: String) {
    println("  [Corrutina-$nombre] Tarea iniciada")
    delay(2000)
    println("  [Corrutina-$nombre] Tarea finalizada")
}


// ============================================================
// 2.3 PRODUCTOR-CONSUMIDOR (CORRUTINAS)
// ============================================================
// Igual que con hilos pero usando Channel, que es la forma
// que tienen las corrutinas de pasarse datos entre ellas.
// Es mas limpio porque no hay que gestionar el acceso a mano,
// el Channel ya lo controla solo.
//
// - send()  : el productor mete un dato en el canal
// - for     : el consumidor recibe los datos
// - close() : el productor avisa de que no hay mas datos
// ============================================================
fun demoComunicacionCorrutinas() = runBlocking {

    println("Productor-Consumidor con corrutinas iniciado")

    val canal = Channel<Int>()

    val productor = launch {
        for (i in 1..5) {
            println("  [Productor] Produciendo $i")
            canal.send(i)
            delay(500)
        }
        canal.close()
        println("  [Productor] Sin mas datos")
    }

    val consumidor = launch {
        for (valor in canal) {
            println("  [Consumidor] Consumiendo $valor")
            delay(800)
        }
        println("  [Consumidor] Sin mas datos que consumir")
    }

    productor.join()
    consumidor.join()

    println("Productor-Consumidor con corrutinas finalizado correctamente")
}


// ============================================================
// 2.4 GESTION DE ERRORES (CORRUTINAS)
// ============================================================
// Igual que con hilos: lanzo un error a proposito y lo capturo
// con try-catch para que el programa no se detenga.
// ============================================================
fun demoErroresCorrutinas() = runBlocking {

    println("Gestion de errores con corrutinas iniciada")

    val job = launch {
        try {
            println("  [Corrutina-Error] Corrutina iniciada")
            throw Exception("Error simulado en la corrutina")
        } catch (e: Exception) {
            println("  [Corrutina-Error] Error capturado: ${e.message}")
        }
        println("  [Corrutina-Error] Corrutina finalizada")
    }

    job.join()

    println("Gestion de errores con corrutinas finalizada correctamente")
}