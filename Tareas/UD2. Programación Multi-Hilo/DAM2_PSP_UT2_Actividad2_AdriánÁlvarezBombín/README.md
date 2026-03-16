# ACTIVIDAD 2.2 Acceso compartido a datos por hilos

**Ciclo Formativo de Grado Superior**  
**Desarrollo de Aplicaciones Multiplataforma**  
**Programación de Servicios y Procesos**

---

## 1. Objetivo

Demostrar conocimientos sobre desarrollo de aplicaciones compuestas por varios hilos, considerando los siguientes aspectos:

- Programar aplicaciones que implementen varios hilos (RA2-c).
- Identificar los posibles estados de ejecución de un hilo y programar aplicaciones que los gestionen (RA2-d).
- Utilizar mecanismos para compartir información entre varios hilos de un mismo proceso (RA2-e).

---

## 2. Enunciado

**Individualmente**, realizad la siguiente tarea competencial evaluable que debe simular una cuenta bancaria utilizada por varios clientes:

1. Crear un programa según los siguientes criterios:

   - **a)** La clase `CuentaBancaria` debe tener métodos para:
     - Devolver el saldo actual.
     - Retirar dinero.
     - Depositar dinero.
     - Se deberá poder fijar con una constante el saldo inicial de la cuenta.

   - **b)** La clase `Cliente` es, en este caso, el Thread y:
     - Realiza un bucle con una cantidad y tipología aleatoria de operaciones (podrán ser de retirada o de depósito de dinero) con cantidades y pausas también aleatorias.
     - Se deberá poder fijar máximos como valores constantes para el máximo número de operaciones del cliente, la máxima cantidad de una operación y el máximo tiempo de pausa.
     - Cada operación que realice un cliente debe ser mostrada por consola, con el nombre del cliente, el tipo de operación realizada y la cantidad retirada/depositada.

   - **c)** La clase principal debe crear una cuenta bancaria, así como el número de clientes que indique el usuario (por teclado) e iniciarlos de manera que todos operen sobre la cuenta bancaria compartida.

   - **d)** Se debe evitar las condiciones de carrera y comprobar que el saldo final resultante se corresponde con las operaciones realizadas por todos los clientes.

   - **e)** Debe realizarse gestión de errores en la introducción de datos del usuario y no se permite retirar más dinero del saldo existente (la cuenta no puede quedar en números rojos).

2. Generad un fichero `.zip` con el contenido del proyecto y subidlo al aula virtual con el siguiente nombre:  
   `DAM2_PSP_UT2_Actividad2_Nombre-alumno`

---

## 3. Evaluación

- La evaluación se realizará conforme a la siguiente lista de cotejo.
- La presentación de trabajos **no genuinos o plagiados** conllevará una calificación de **0 puntos** en la actividad para todos los alumnos involucrados.
- La actividad deberá entregarse antes de la fecha de cierre configurada en el aula virtual para ser evaluada. De no ser así, se puntuará con un 0. Se reabrirá el plazo de entrega para las convocatorias de Ordinaria y Extraordinaria para aquellos alumnos que deban presentarse a ellas.
- Tras la entrega, el docente podrá realizar preguntas a los alumnos para evaluar su grado de contribución a la actividad.

---

## Lista de Cotejo – Acceso compartido a datos por hilos

| Criterio de evaluación | Sí | No | Observaciones |
|---|---|---|---|
| 1. **(3 puntos)** La aplicación cuenta con las clases y métodos requeridos, incluyendo las constantes solicitadas. | ☐ | ☐ | |
| 2. **(3 puntos)** La aplicación genera correctamente los hilos que ejecutan en paralelo los clientes y realiza los tipos de operaciones requeridas sobre una única cuenta bancaria. | ☐ | ☐ | |
| 3. **(3 puntos)** Los hilos se ejecutan correctamente y de manera concurrente haciendo el cálculo tal y como se pide (solo sumas y división). | ☐ | ☐ | |
| 4. **(1 punto)** Se aplican mecanismos para controlar errores y no dejar la cuenta en números rojos. | ☐ | ☐ | |

**Puntuación máxima: 10 puntos.**
