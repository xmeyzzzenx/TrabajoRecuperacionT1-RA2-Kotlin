# TrabajoRecuperacionT1-RA2-Kotlin
## Autora: Ximena Meyzen Calderon - 2DAM - PSP

---

## Descripcion

Programa en Kotlin donde trabajo con hilos y corrutinas para ver
como funcionan y comparar su comportamiento. Esta hecho con ejemplos
practicos para entender bien la diferencia entre ejecutar tareas
de forma secuencial y concurrente.

---

## Objetivo

El objetivo es aprender a usar hilos y corrutinas mediante ejemplos reales:

- Lanzar hilos y corrutinas y ver como se comportan
- Comparar ejecucion secuencial vs concurrente y medir tiempos
- Implementar el patron productor-consumidor con hilos y corrutinas
- Controlar errores para que el programa no se caiga

---

## Que incluye el programa

El programa esta dividido en dos secciones:

### Seccion 1: Hilos

- Hilo basico con Thread, con nombre para identificarlo en consola
- Secuencial vs concurrente midiendo tiempos reales
- Productor-consumidor usando lista compartida y synchronized como candado
- Gestion de errores con try-catch dentro del hilo

### Seccion 2: Corrutinas

- Corrutina basica con launch y async, explicando la diferencia entre los dos
- Secuencial vs concurrente midiendo tiempos reales
- Productor-consumidor usando Channel, que regula el flujo automaticamente
- Gestion de errores con try-catch dentro de la corrutina

---

## Tecnologias utilizadas

- Kotlin
- Libreria `kotlinx.coroutines`

---

## Como ejecutar el programa

1. Abrir el proyecto en IntelliJ IDEA
2. Ejecutar el archivo `Main.kt`
3. Ver la salida en la consola

---

## Resultados

Los resultados completos estan en el fichero `RESULTADOS.md`.

En resumen:

- Concurrente tarda la mitad que secuencial porque las tareas van a la vez
- Con hilos hay que controlar el acceso a datos compartidos con synchronized
- Con corrutinas el Channel lo gestiona solo, el codigo es mas limpio
- Los tiempos de hilos y corrutinas son muy similares, la ventaja de
  las corrutinas es que son mas faciles de usar

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

## Repositorio

https://github.com/xmeyzzzenx/TrabajoRecuperacionT1-RA2-Kotlin

---
