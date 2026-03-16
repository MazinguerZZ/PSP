# ACTIVIDAD 2.3 Gestión de puestos de trabajo y ordenadores limitados

**Ciclo Formativo de Grado Superior**  
**Desarrollo de Aplicaciones Multiplataforma**  
**Programación de Servicios y Procesos**

---

## 1. Objetivo

Demostrar conocimientos sobre desarrollo de aplicaciones compuestas por varios hilos, considerando los siguientes aspectos:

- Desarrollar programas formados por varios hilos sincronizados mediante técnicas específicas (RA2-f).
- Depurar y documentar los programas desarrollados (RA2-h).
- Analizar el contexto de ejecución de los hilos (RA2-i).
- Analizar librerías específicas del lenguaje de programación que permiten la programación multihilo (RA2-j).
- Reconocer los problemas derivados de la compartición de información entre los hilos de un mismo proceso (RA2-k).

---

## 2. Enunciado

**Individualmente**, realizad la siguiente tarea competencial evaluable que debe gestionar las limitaciones de una empresa a la hora de asignar puestos de trabajo y ordenadores en su sede a los empleados los días que no teletrabajan:

1. La empresa dispone de una zona con la misma cantidad de mesas libres en la primera planta, que de ordenadores disponibles custodiados en otra planta por el departamento de IT. Dos días a la semana los empleados deben trabajar desde la sede, aunque esos días no están prefijados. Al llegar a la empresa los empleados deben reservar una mesa y recoger un ordenador, o al revés (aleatoriamente).

2. Se debe programar una clase `EMPLEADO` que:

   - **a)** Ejecute en un hilo y deba tomar control de una mesa y de un ordenador libres para poder trabajar durante un tiempo aleatorio definible como una constante.
   - **b)** Como los empleados son poco constantes y enseguida abandonan su mesa y su ordenador para tomarse un descanso durante un tiempo aleatorio, la política de la empresa es que los empleados liberen esos recursos para que otros puedan usarlos mientras.

3. La clase principal debe:

   - **a)** Crear el número de `EMPLEADOS` que indique el usuario (por teclado, siendo mayor que el número de puestos/ordenadores disponibles).
   - **b)** Hacer conscientes a los empleados de los recursos, tanto `ORDENADORES` como `PUESTOS` disponibles, indicados por el usuario (por teclado y siendo una cantidad idéntica para ambos).

4. Deben evitarse condiciones de carrera o interbloqueos (utilizando semáforos), que provoquen que los empleados bloqueen recursos pero no puedan ponerse a trabajar.

5. Generad un fichero `.zip` con el contenido del proyecto y subidlo al aula virtual con el siguiente nombre:  
   `DAM2_PSP_UT2_Actividad3_Nombre-alumno`

---

## 3. Evaluación

- La evaluación se realizará conforme a la siguiente lista de cotejo.
- La presentación de trabajos **no genuinos o plagiados** conllevará una calificación de **0 puntos** en la actividad para todos los alumnos involucrados.
- La actividad deberá entregarse antes de la fecha de cierre configurada en el aula virtual para ser evaluada. De no ser así, se puntuará con un 0. Se reabrirá el plazo de entrega para las convocatorias de Ordinaria y Extraordinaria para aquellos alumnos que deban presentarse a ellas.
- Tras la entrega, el docente podrá realizar preguntas a los alumnos para evaluar su grado de contribución a la actividad.

---

## Lista de Cotejo – Gestión de puestos de trabajo y ordenadores limitados

| Criterio de evaluación | Sí | No | Observaciones |
|---|---|---|---|
| 1. **(3 puntos)** La aplicación cuenta con las clases y métodos requeridos, incluyendo las constantes solicitadas. | ☐ | ☐ | |
| 2. **(3 puntos)** La aplicación genera correctamente los hilos que ejecutan en paralelo los empleados. | ☐ | ☐ | |
| 3. **(3 puntos)** Los hilos se ejecutan correctamente y de manera concurrente repartiendo el uso de los recursos entre los empleados sin riesgo de interbloqueos y usando semáforos. | ☐ | ☐ | |
| 4. **(1 punto)** Se aplican mecanismos para controlar errores en la entrada de los datos. | ☐ | ☐ | |

**Puntuación máxima: 10 puntos.**
