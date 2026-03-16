# ACTIVIDAD 4.1 Desarrollo de un servicio en red

**Ciclo Formativo de Grado Superior**  
**Desarrollo de Aplicaciones Multiplataforma**  
**Programación de Servicios y Procesos**

---

## 1. Objetivo

Demostrar conocimientos sobre desarrollo de aplicaciones que ofrecen servicios en red, considerando los siguientes aspectos:

- Identificar diferentes protocolos estándar de comunicación para la implementación de servicios en red (RA4-a).
- Reconocer las ventajas de la utilización de protocolos estándar para la comunicación entre aplicaciones y procesos (RA4-b).
- Analizar librerías que permitan implementar servicios en red utilizando protocolos estándar de comunicación (RA4-c).
- Desarrollar y probar servicios de comunicación en red (RA4-d).
- Utilizar clientes de comunicaciones para verificar el funcionamiento de los servicios (RA4-e).
- Incorporar mecanismos para posibilitar la comunicación simultánea de varios clientes con el servicio (RA4-f).
- Verificar la disponibilidad del servicio (RA4-g).
- Depurar y documentar las aplicaciones desarrolladas (RA4-h).

---

## 2. Enunciado

**En parejas**, realizad la siguiente tarea competencial evaluable, relacionada con la generación de servicios en red:

1. Crear una aplicación Java con un modelo cliente/servidor que permita a los clientes solicitar al servidor remoto los servicios de **conteo** y **reemplazo** de palabras de un texto enviado. Se deberá implementar usando un mecanismo de comunicación en redes (sockets, servicios web, RMI, …), a elección de los alumnos, siempre que resulte adecuado para esta tarea.

2. El servidor debe:

   - **a)** Proporcionar el servicio en red de **conteo de palabras** en un texto, a ser solicitado por un cliente remoto que le facilitará ambos parámetros, debiendo devolverle el resultado correcto (número de veces que se repite la palabra en el texto).
   - **b)** Proporcionar el servicio en red de **reemplazo de palabras** en un texto, a ser solicitado por un cliente remoto que le facilitará la palabra a sustituir, la que le sustituye y el texto como parámetros, debiendo devolverle el resultado correcto (texto con todas las apariciones de la palabra a sustituir reemplazadas).
   - **c)** Ser capaz de atender múltiples solicitudes simultáneas de clientes.
   - **d)** Ser capaz de ejecutar de manera independiente a los clientes (no debe requerir la ejecución de un cliente en la misma máquina).

3. El cliente debe:

   - **a)** Permitir al usuario:
     - Seleccionar el servicio, de los dos disponibles, que quiere solicitar al servidor de manera interactiva.
     - Introducir manualmente, o seleccionar automáticamente mediante alguna configuración o constante, los parámetros asociados al servicio seleccionado.
   - **b)** Solicitar al servidor remoto el correspondiente servicio pasándole los parámetros correctos.
   - **c)** Gestionar posibles errores de conexión, comunicación, disponibilidad, etc.
   - **d)** Mostrar confirmación de la ejecución del servicio y su resultado (valor del conteo o texto/fichero con las palabras reemplazadas).

4. El código debe estar documentado mediante Javadoc, con la información de las clases, métodos y atributos, además de comentarios explicativos en el código.

5. Se deberá generar un documento de texto que recoja de manera resumida la siguiente información:

   - **a)** Mecanismo y protocolos de comunicación seleccionados.
   - **b)** Ventajas e inconvenientes de éstos.
   - **c)** Paquetes y librerías de comunicaciones en red Java utilizados.
   - **d)** Explicación a alto nivel de la estructura y funcionamiento del proyecto.

6. Generad un fichero `.zip` con el contenido de la carpeta src del proyecto, además del documento de texto, y subidlo al aula virtual con el siguiente nombre:  
   `DAM2_PSP_UT4_Actividad1_Nombres-alumnos`

---

## 3. Evaluación

- La evaluación se realizará conforme a la siguiente lista de cotejo.
- La presentación de trabajos **no genuinos o plagiados** conllevará una calificación de **0 puntos** en la actividad para todos los alumnos involucrados.
- La actividad deberá entregarse antes de la fecha de cierre configurada en el aula virtual para ser evaluada. De no ser así, se puntuará con un 0. Se reabrirá el plazo de entrega para las convocatorias de Ordinaria y Extraordinaria para aquellos alumnos que deban presentarse a ellas.
- Tras la entrega, el docente podrá realizar preguntas a los alumnos para evaluar su grado de contribución a la actividad.
- Las funcionalidades que no sean ejecutables no podrán ser evaluadas.

---

## Lista de Cotejo – Desarrollo de un Servicio en Red

| Criterio de evaluación | Sí | No | Observaciones |
|---|---|---|---|
| 1. **(1 punto)** Correcta identificación, en el documento de texto, de los mecanismos, protocolos y librerías usadas. | ☐ | ☐ | |
| 2. **(1 punto)** Correcta identificación, en el documento de texto, de las ventajas y desventajas de la aproximación seleccionada. | ☐ | ☐ | |
| 3. **(1 punto)** Correcta explicación a alto nivel, en el documento de texto, de la estructura y funcionamiento del proyecto. | ☐ | ☐ | |
| 4. **(1 punto)** El servidor expone correctamente los servicios a ser usados por los clientes, permitiendo que puedan ser utilizados remotamente. | ☐ | ☐ | |
| 5. **(1 punto)** El servidor es capaz de ejecutar de manera independiente a los clientes y atender simultáneamente a varios de ellos. | ☐ | ☐ | |
| 6. **(1 punto)** El cliente permite al usuario seleccionar, como se requiere, el servicio que quiere solicitar al servidor y los parámetros asociados. | ☐ | ☐ | |
| 7. **(1 punto)** El cliente solicita adecuadamente los respectivos servicios al servidor. | ☐ | ☐ | |
| 8. **(1 punto)** El cliente muestra correctamente la confirmación y el resultado de la ejecución de los servicios. | ☐ | ☐ | |
| 9. **(1 punto)** Los servicios se prestan sin que surjan excepciones o errores no controlados. | ☐ | ☐ | |
| 10. **(1 punto)** El código está documentado correctamente con Javadoc y comentado. | ☐ | ☐ | |

**Puntuación máxima: 10 puntos.**
