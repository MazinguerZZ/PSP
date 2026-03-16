# ACTIVIDAD 2.1 Creación y ejecución de hilos

**Ciclo Formativo de Grado Superior**  
**Desarrollo de Aplicaciones Multiplataforma**  
**Programación de Servicios y Procesos**

---

## 1. Objetivo

Demostrar conocimientos sobre desarrollo de aplicaciones compuestas por varios hilos, considerando los siguientes aspectos:

- Identificar situaciones en las que resulte útil la utilización de varios hilos en un programa (RA2-a).
- Reconocer los mecanismos para crear, iniciar y finalizar hilos (RA2-b).
- Establecer y controlar la prioridad de cada uno de los hilos de ejecución (RA2-g).

---

## 2. Enunciado

**Individualmente**, realizad la siguiente tarea competencial evaluable que debe calcular el área de varios triángulos en paralelo usando hilos:

1. Crear un programa que:

   - **a)** Solicite al usuario **cuántos triángulos** quiere calcular, si desea asignar una prioridad al cálculo de las áreas y, a continuación, **ir preguntando los datos** (base y altura de cada triángulo, así como prioridad en caso de haberlo confirmado).
   - **b)** El programa principal debe ir creando un hilo por cada triángulo que indique el usuario y **lanzar (start) los hilos a la vez cuando el usuario haya terminado de introducir los datos de todos los triángulos**.
   - **c)** En el caso de que el usuario opte por introducir las prioridades, la aplicación debe asignarlas debidamente a los correspondientes hilos.
   - **d)** Cada hilo muestra por pantalla los datos de su triángulo y el área calculada.
   - **e)** Se deben aceptar valores decimales para la base y la altura, pero solo enteros para las prioridades.
   - **f)** **IMPORTANTE**: El cálculo del área debe hacerse utilizando sumas y divisiones, pero no multiplicaciones (salvo para la parte decimal en cuyo caso sí se permite el uso de una multiplicación).

2. Generad un fichero `.zip` con el contenido de la carpeta src del proyecto y subidlo al aula virtual con el siguiente nombre:  
   `DAM2_PSP_UT2_Actividad1_Nombre-alumno`

---

## 3. Evaluación

- La evaluación se realizará conforme a la siguiente lista de cotejo.
- La presentación de trabajos **no genuinos o plagiados** conllevará una calificación de **0 puntos** en la actividad para todos los alumnos involucrados.
- La actividad deberá entregarse antes de la fecha de cierre configurada en el aula virtual para ser evaluada. De no ser así, se puntuará con un 0. Se reabrirá el plazo de entrega para las convocatorias de Ordinaria y Extraordinaria para aquellos alumnos que deban presentarse a ellas.
- Tras la entrega, el docente podrá realizar preguntas a los alumnos para evaluar su grado de contribución a la actividad.

---

## Lista de Cotejo – Creación y ejecución de hilos

| Criterio de evaluación | Sí | No | Observaciones |
|---|---|---|---|
| 1. **(1 punto)** La aplicación muestra los menús requeridos y captura correctamente la información proporcionada por el usuario. | ☐ | ☐ | |
| 2. **(3 puntos)** La aplicación genera correctamente los hilos apoyándose en clases que extienden o implementan adecuadamente los mecanismos necesarios. | ☐ | ☐ | |
| 3. **(3 puntos)** Los hilos se ejecutan correctamente y de manera concurrente haciendo el cálculo tal y como se pide (solo sumas y división). | ☐ | ☐ | |
| 4. **(3 puntos)** La aplicación tiene en consideración correctamente las prioridades asignadas por el usuario. | ☐ | ☐ | |

**Puntuación máxima: 10 puntos.**
