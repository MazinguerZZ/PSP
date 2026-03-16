# ACTIVIDAD 1.2 Comunicación entre procesos

**Ciclo Formativo de Grado Superior**  
**Desarrollo de Aplicaciones Multiplataforma**  
**Programación de Servicios y Procesos**

---

## 1. Objetivo

Demostrar conocimientos sobre desarrollo de aplicaciones compuestas por varios procesos, considerando los siguientes aspectos:

- Utilizar mecanismos para compartir información con los subprocesos iniciados (RA1-f).
- Utilizar mecanismos para sincronizar y obtener el valor devuelto por los subprocesos iniciados (RA1-g).

---

## 2. Enunciado

**Individualmente**, realizad la siguiente tarea competencial evaluable:

1. Crear un programa que realice lo siguiente:

   - **a)** Desarrollar una aplicación que genere números aleatorios naturales. Para su creación puede utilizarse cualquier lenguaje de programación generando el ejecutable correspondiente.
   - **b)** Generar otra aplicación que:
     - Lea líneas de la entrada estándar.
     - Por cada carácter en la línea, lance un proceso hijo encargado de llamar a la aplicación referida en a) para que genere un número aleatorio.
     - Los procesos hijo deberán compartir los números aleatorios con el proceso padre, para que sea éste el que los imprima por pantalla.
   - **c)** Cuando el proceso padre lea la línea `"fin"` finalizará la ejecución.

2. El siguiente es un ejemplo de la ejecución del programa:

   ```
   > ab
   75
   > abcdef
   810258
   > casa
   1574
   > fin
   ```

3. Generad un fichero `.zip` con el contenido de la carpeta src del proyecto y subidlo al aula virtual con el siguiente nombre:  
   `DAM2_PSP_UT1_Actividad2_Nombre-alumno`

---

## 3. Evaluación

- La evaluación se realizará conforme a la siguiente lista de cotejo.
- La presentación de trabajos **no genuinos o plagiados** conllevará una calificación de **0 puntos** en la actividad para todos los alumnos involucrados.
- La actividad deberá entregarse antes de la fecha de cierre configurada en el aula virtual para ser evaluada. De no ser así, se puntuará con un 0. Se reabrirá el plazo de entrega para las convocatorias de Ordinaria y Extraordinaria para aquellos alumnos que deban presentarse a ellas.
- Tras la entrega, el docente podrá realizar preguntas a los alumnos para evaluar su grado de contribución a la actividad.

---

## Lista de Cotejo – Comunicación entre procesos

| Criterio de evaluación | Sí | No | Observaciones |
|---|---|---|---|
| 1. **(1 punto)** Se ha definido correctamente una aplicación que genera números aleatorios. | ☐ | ☐ | |
| 2. **(1 punto)** Se ha generado una aplicación padre que lee correctamente las líneas de la entrada estándar. | ☐ | ☐ | |
| 3. **(3 puntos)** La aplicación padre genera correctamente procesos llamando a la aplicación de generación de números aleatorios. | ☐ | ☐ | |
| 4. **(3 puntos)** El proceso padre puede acceder a la información generada por los procesos hijo (número aleatorio generado). | ☐ | ☐ | |
| 5. **(2 puntos)** El programa genera una salida similar a la del ejemplo. | ☐ | ☐ | |

**Puntuación máxima: 10 puntos.**
