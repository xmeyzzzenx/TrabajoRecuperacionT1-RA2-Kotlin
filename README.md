# Trabajo Recuperación RA2 - PSP

## Descripción

Este proyecto consiste en un programa en Kotlin donde se trabajan hilos y corrutinas para ver cómo funcionan y comparar su comportamiento.

---

## Objetivo

El objetivo es aprender a usar hilos y corrutinas mediante ejemplos prácticos:

* Ejecutar tareas de forma secuencial y concurrente
* Comunicar procesos (productor-consumidor)
* Controlar errores durante la ejecución

---

## Qué incluye el programa

El programa tiene varios ejemplos:

### Hilos

* Creación de un hilo básico
* Ejecución secuencial vs concurrente
* Productor-consumidor usando lista y `synchronized`
* Manejo de errores en hilos

### Corrutinas

* Corrutina básica
* Ejecución secuencial vs concurrente
* Productor-consumidor usando `Channel`
* Manejo de errores en corrutinas

---

## Tecnologías utilizadas

* Kotlin
* Librería `kotlinx.coroutines`

---

## Cómo ejecutar el programa

1. Abrir el proyecto en IntelliJ IDEA
2. Ejecutar el archivo `Main.kt`
3. Ver la salida en la consola

---

## Resultados

Se puede observar que:

* La ejecución concurrente es más rápida que la secuencial
* Los hilos necesitan más control manual (`synchronized`)
* Las corrutinas son más fáciles de usar
* `Channel` facilita la comunicación entre corrutinas

---

## Estructura del proyecto

```
TrabajoRecuperacionT1-RA2-Kotlin/
├── README.md
├── RESULTADOS.md
└── src/
    └── Main.kt
```

---

## Autor

Ximena Meyzen Calderón