# RESULTADOS - RA2

## Ejecución del programa

Al ejecutar el programa se muestra por consola cómo funcionan los hilos y las corrutinas.

En cada caso se puede ver:

* Cuándo empieza cada tarea
* Qué está haciendo
* Cuándo termina

Esto permite entender fácilmente el comportamiento del programa.

---

## Captura de salidas y estados

Durante la ejecución se muestran mensajes en consola que indican:

* Inicio de cada hilo o corrutina
* Ejecución de las tareas
* Finalización correcta
* Mensajes de error controlados

De esta forma se puede comprobar que todo funciona correctamente.

---

## Comparación: Secuencial vs Concurrente

### Hilos

* **Secuencial**: las tareas se ejecutan una después de otra
* **Concurrente**: las tareas se ejecutan a la vez

Ejemplo de tiempos:

| Tipo        | Tiempo aproximado |
| ----------- | ----------------- |
| Secuencial  | ~4000 ms          |
| Concurrente | ~2000 ms          |

---

### Corrutinas

* **Secuencial**: ejecución una tras otra
* **Concurrente**: ejecución al mismo tiempo usando `async`

Ejemplo de tiempos:

| Tipo        | Tiempo aproximado |
| ----------- | ----------------- |
| Secuencial  | ~4000 ms          |
| Concurrente | ~2000 ms          |

---

### Explicación

La ejecución concurrente es más rápida porque las tareas se realizan al mismo tiempo, mientras que en la ejecución secuencial se hacen una detrás de otra.

---

## Comunicación entre procesos

### Hilos

Se ha implementado un sistema productor-consumidor usando:

* Una lista compartida
* `synchronized` para evitar errores

Un hilo produce datos y otro los consume.

---

### Corrutinas

Se utiliza un `Channel`:

* Una corrutina envía datos
* Otra los recibe

Es más sencillo y seguro que usar hilos.

---

## Gestión de errores

### Hilos

Se lanza una excepción dentro de un hilo y se captura con `try-catch`, evitando que el programa se detenga.

---

### Corrutinas

Se hace lo mismo en una corrutina, capturando el error correctamente.

---

## Conclusiones

* La ejecución concurrente reduce el tiempo total del programa
* En secuencial, las tareas tardan más
* Los hilos requieren más control (`synchronized`)
* Las corrutinas son más fáciles de usar
* `Channel` facilita la comunicación
* Las corrutinas son una mejor opción en Kotlin
