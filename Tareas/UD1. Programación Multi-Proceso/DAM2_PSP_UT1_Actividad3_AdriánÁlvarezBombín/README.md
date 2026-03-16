# ACTIVIDAD 1.3 Ejecución de procesos en paralelo

**Ciclo Formativo de Grado Superior**  
**Desarrollo de Aplicaciones Multiplataforma**  
**Programación de Servicios y Procesos**

---

## 1. Objetivo

Demostrar conocimientos sobre desarrollo de aplicaciones compuestas por varios procesos, considerando los siguientes aspectos:

- Diferenciar entre programación paralela y programación distribuida (RA1-b).
- Desarrollar aplicaciones que gestionen y utilicen procesos para la ejecución de varias tareas en paralelo (RA1-h).
- Depurar y documentar las aplicaciones desarrolladas (RA1-i).

---

## 2. Enunciado

**Individualmente**, realizad la siguiente tarea competencial evaluable, creando un programa Java que realice lo siguiente usando como base el fichero `A1.3_TEMPLATE.zip` facilitado por el profesor:

1. Una clase principal `GestorBusqueda.java` que realice lo siguiente:

   - **a)** Leer por teclado en un proceso repetitivo una de las siguientes opciones:
     - `BUSCAR`
     - `CANCELAR`
     - `SALIR`

   - **b)** En caso de seleccionar **BUSCAR**, debe dar opción a introducir los siguientes datos:
     - Seleccionar un texto a buscar entre las 3 siguientes opciones: `CASA`, `PERRO`, `COCHE`.
     - El identificador de un fichero de 1 a 5.
     - Los datos deben ser validados comprobando su corrección y mostrar mensajes de error en caso contrario.
     - Después de introducir datos válidos, se debe lanzar un proceso encargado de buscar y contar las apariciones de la opción elegida en el fichero con el identificador seleccionado (p.e.: si se selecciona la opción PERRO para el id de fichero 2, se buscarán y contarán las apariciones de la palabra "PERRO" en el fichero `texto2.txt`).
     - **IMPORTANTE**: La generación de los ficheros debe realizarse ejecutando la clase `GeneradorFichsTexto.java` proporcionada en la plantilla.
     - **MUY IMPORTANTE**: El proceso principal no debe quedar bloqueado durante la ejecución de la búsqueda, pudiendo admitir nuevas acciones mientras la búsqueda finaliza. No se permitirá buscar una segunda opción en el mismo fichero hasta que no haya finalizado la anterior búsqueda.
     - Cuando el proceso de búsqueda haya finalizado se mostrará el resultado del conteo mediante `JOptionPane.showMessageDialog`.

   - **c)** En caso de seleccionar **CANCELAR** se deberán destruir todos los procesos de búsqueda que estuvieran activos.

   - **d)** En caso de seleccionar **SALIR** se terminará el programa.

   - **e)** Se deben documentar adecuadamente las clases y sus métodos utilizando comentarios Javadoc (incluyendo descripciones, parámetros, valores de retorno, etc.).

   - **f)** Como mejora se podrá realizar la captura de las opciones mediante una interfaz gráfica de usuario (GUI). La opción SALIR no es necesaria siempre que al pulsar la cruz de la ventana se termine la ejecución de la aplicación.

2. Generad un fichero `.zip` con el contenido de la carpeta del proyecto y subidlo al aula virtual con el siguiente nombre:  
   `DAM2_PSP_UT1_Actividad3_Nombre-alumno`

---

## 3. Evaluación

- La evaluación se realizará conforme a la siguiente lista de cotejo.
- La presentación de trabajos **no genuinos o plagiados** conllevará una calificación de **0 puntos** en la actividad para todos los alumnos involucrados.
- La actividad deberá entregarse antes de la fecha de cierre configurada en el aula virtual para ser evaluada. De no ser así, se puntuará con un 0. Se reabrirá el plazo de entrega para las convocatorias de Ordinaria y Extraordinaria para aquellos alumnos que deban presentarse a ellas.
- Tras la entrega, el docente podrá realizar preguntas a los alumnos para evaluar su grado de contribución a la actividad.

---

## Lista de Cotejo – Ejecución de procesos en paralelo

| Criterio de evaluación | Sí | No | Observaciones |
|---|---|---|---|
| 1. **(1 punto)** Se recopilan correctamente los datos de opciones e identificador de fichero y se valida la información recogida para que no haya valores nulos/vacíos y sean los esperados. | ☐ | ☐ | |
| 2. **(1 punto)** El proceso padre lanza correctamente los procesos de búsqueda conforme a las opciones seleccionadas al seleccionar la opción de BUSCAR. | ☐ | ☐ | |
| 3. **(3 puntos)** La ejecución de los procesos no es bloqueante, pudiendo interactuar con la aplicación mientras se ejecutan una o varias búsquedas. | ☐ | ☐ | |
| 4. **(1 punto)** Se gestiona correctamente la destrucción de los procesos al seleccionar la opción de CANCELAR. | ☐ | ☐ | |
| 5. **(1 punto)** La aplicación se ejecuta sin ningún error o advertencia. | ☐ | ☐ | |
| 6. **(2 puntos)** La aplicación está suficientemente documentada. | ☐ | ☐ | |
| 7. **(1 punto)** Se ha programado correctamente la interfaz principal con una GUI. | ☐ | ☐ | |

**Puntuación máxima: 10 puntos.**
