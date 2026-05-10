# RESULTADOS - RA2
## Autora: Ximena Meyzen Calderon - 2DAM

---

## 1. Ejecucion del programa

El programa funciona correctamente y muestra por consola
el comportamiento de hilos y corrutinas en cada seccion.
En cada apartado se ve cuando empieza cada tarea, que esta
haciendo y cuando termina.

---

## 2. Seccion 1: Hilos

### 1.1 Hilo basico

Creo un hilo con Thread, le pongo nombre para reconocerlo
en consola y lo arranco con start(). Con join() hago que
el programa espere a que termine antes de continuar.

Salida obtenida:

```
Hilo basico iniciado
  [Hilo-Basico] Hilo ejecutandose...
  [Hilo-Basico] Hilo finalizado
Hilo basico finalizado correctamente
```

### 1.2 Secuencial vs Concurrente (Hilos)

Ejecuto las mismas dos tareas de dos formas para ver
la diferencia de tiempo.

Salida obtenida:

```
Ejecucion secuencial:
  [Hilo-A] Tarea A iniciada
  [Hilo-A] Tarea A finalizada
  [Hilo-B] Tarea B iniciada
  [Hilo-B] Tarea B finalizada
Tiempo secuencial: 4016 ms

Ejecucion concurrente:
  [Hilo-A] Tarea A iniciada
  [Hilo-B] Tarea B iniciada
  [Hilo-B] Tarea B finalizada
  [Hilo-A] Tarea A finalizada
Tiempo concurrente: 2014 ms
```

Tabla comparativa:

| Tipo        | Tiempo real |
| ----------- | ----------- |
| Secuencial  | 4016 ms     |
| Concurrente | 2014 ms     |

En secuencial tarda el doble porque las tareas van una detras
de la otra (2000 + 2000 ms). En concurrente las dos corren a la
vez y tarda la mitad. Tambien se puede ver que en concurrente
el orden de finalizacion no es siempre el mismo, depende de
como el sistema reparte los hilos.

### 1.3 Productor-Consumidor (Hilos)

Un hilo produce datos y los mete en una lista compartida,
otro los saca y los consume. Uso synchronized como candado
para que no accedan a la lista a la vez y no haya errores.

Salida obtenida:

```
Productor-Consumidor con hilos iniciado
  [Productor] Produciendo 1
  [Productor] Produciendo 2
  [Consumidor] Consumiendo 1
  [Productor] Produciendo 3
  [Productor] Produciendo 4
  [Consumidor] Consumiendo 2
  [Productor] Produciendo 5
  [Consumidor] Consumiendo 3
  [Productor] Sin mas datos
  [Consumidor] Consumiendo 4
  [Consumidor] Consumiendo 5
  [Consumidor] Sin mas datos que consumir
Productor-Consumidor con hilos finalizado correctamente
```

Se ve que el productor va mas rapido que el consumidor y se
acumulan varios datos antes de que el consumidor los procese.
Eso es porque el productor produce cada 500ms y el consumidor
tarda 800ms en procesar cada dato.

### 1.4 Gestion de errores (Hilos)

Lanzo una excepcion a proposito dentro del hilo para ver
que con try-catch el programa no se cae aunque haya un error.

Salida obtenida:

```
Gestion de errores con hilos iniciada
  [Hilo-Error] Hilo iniciado
  [Hilo-Error] Error capturado: Error simulado en el hilo
  [Hilo-Error] Hilo finalizado
Gestion de errores con hilos finalizada correctamente
```

El hilo falla pero el programa sigue funcionando porque
capturamos el error antes de que cause problemas.

---

## 3. Seccion 2: Corrutinas

### 2.1 Corrutina basica

Aqui demuestro la diferencia entre launch y async:
- launch lo uso cuando no necesito que devuelva nada
- async lo uso cuando si necesito un resultado, que recojo con await()

Salida obtenida:

```
Corrutina basica iniciada
  [Corrutina-launch] Corrutina ejecutandose...
  [Corrutina-launch] Corrutina finalizada
  [Corrutina-async] Corrutina ejecutandose...
  [Corrutina-async] Corrutina finalizada, resultado: Resultado devuelto por async
Corrutina basica finalizada correctamente
```

### 2.2 Secuencial vs Concurrente (Corrutinas)

Lo mismo que con hilos pero usando corrutinas.
El resultado es el mismo: concurrente tarda la mitad.

Salida obtenida:

```
Ejecucion secuencial:
  [Corrutina-A] Tarea iniciada
  [Corrutina-A] Tarea finalizada
  [Corrutina-B] Tarea iniciada
  [Corrutina-B] Tarea finalizada
Tiempo secuencial: 4015 ms

Ejecucion concurrente:
  [Corrutina-A] Tarea iniciada
  [Corrutina-B] Tarea iniciada
  [Corrutina-A] Tarea finalizada
  [Corrutina-B] Tarea finalizada
Tiempo concurrente: 2015 ms
```

Tabla comparativa:

| Tipo        | Tiempo real |
| ----------- | ----------- |
| Secuencial  | 4015 ms     |
| Concurrente | 2015 ms     |

Al igual que con los hilos, en concurrente el orden de finalizacion
no es siempre el mismo, depende de como Kotlin gestiona las corrutinas.

### 2.3 Productor-Consumidor (Corrutinas)

Lo mismo que con hilos pero usando Channel en vez de
una lista con synchronized. Es mas limpio porque el Channel
ya controla el acceso solo, no hay que hacerlo a mano.

Salida obtenida:

```
Productor-Consumidor con corrutinas iniciado
  [Productor] Produciendo 1
  [Consumidor] Consumiendo 1
  [Productor] Produciendo 2
  [Consumidor] Consumiendo 2
  [Productor] Produciendo 3
  [Consumidor] Consumiendo 3
  [Productor] Produciendo 4
  [Consumidor] Consumiendo 4
  [Productor] Produciendo 5
  [Consumidor] Consumiendo 5
  [Productor] Sin mas datos
  [Consumidor] Sin mas datos que consumir
Productor-Consumidor con corrutinas finalizado correctamente
```

Aqui se nota la diferencia con los hilos: con Channel el
productor y el consumidor van practicamente a la vez porque
el Channel regula el flujo automaticamente.

### 2.4 Gestion de errores (Corrutinas)

Igual que con hilos, lanzo un error a proposito y lo capturo
con try-catch para que el programa no se detenga.

Salida obtenida:

```
Gestion de errores con corrutinas iniciada
  [Corrutina-Error] Corrutina iniciada
  [Corrutina-Error] Error capturado: Error simulado en la corrutina
  [Corrutina-Error] Corrutina finalizada
Gestion de errores con corrutinas finalizada correctamente
```

---

## 4. Comparativa general

| Seccion            | Hilos                | Corrutinas  |
| ------------------ | -------------------- | ----------- |
| Secuencial         | 4016 ms              | 4015 ms     |
| Concurrente        | 2014 ms              | 2015 ms     |
| Comunicacion       | synchronized + lista | Channel     |
| Gestion de errores | try-catch            | try-catch   |

---

## 5. Conclusiones

- Concurrente tarda la mitad que secuencial tanto en hilos como
  en corrutinas porque las tareas se ejecutan a la vez.

- En el productor-consumidor se nota bastante la diferencia.
  Con hilos el productor se adelanta porque no hay nada que
  regule el flujo. Con Channel van mucho mas sincronizados.

- Los hilos necesitan mas control manual con synchronized.
  Las corrutinas con Channel lo gestionan solas, el codigo
  queda mas limpio y es mas facil de entender.

- Los tiempos de hilos y corrutinas son muy similares.
  La ventaja de las corrutinas no es la velocidad sino que
  son mas faciles de usar y el codigo es mas sencillo.

- La gestion de errores funciona igual en los dos casos con
  try-catch, pero en corrutinas hay que tener mas cuidado
  porque un error sin controlar puede afectar a otras corrutinas.
