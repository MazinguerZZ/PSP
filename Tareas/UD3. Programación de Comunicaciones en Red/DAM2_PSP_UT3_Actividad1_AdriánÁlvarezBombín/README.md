# ACTIVIDAD 3.1 Herramienta de escaneo de redes

**Ciclo Formativo de Grado Superior**  
**Desarrollo de Aplicaciones Multiplataforma**  
**Programación de Servicios y Procesos**

---

## 1. Objetivo

Demostrar conocimientos sobre desarrollo de aplicaciones compuestas por varios hilos, considerando los siguientes aspectos:

- Identificar escenarios que precisan establecer comunicación en red entre varias aplicaciones (RA3-a).
- Identificar los roles de cliente y de servidor y sus funciones asociadas (RA3-b).
- Reconocer librerías y mecanismos del lenguaje de programación que permiten programar aplicaciones en red (RA3-c).

---

## 2. Enunciado

**En parejas**, realizad la siguiente tarea competencial evaluable, relacionada con mecanismos de comunicación en red, que funcione como una herramienta de escaneo de redes similar a [Nmap](https://nmap.org) (aunque mucho más básica):

1. Crear una aplicación Java que permita identificar dispositivos conectados a una red en un rango de direcciones IP (con máscara /24), determinar los puertos que tienen abiertos y ofrecer funcionalidades básicas de análisis de red.

2. Para ello debe:

   - **a)** Pedir al usuario que proporcione una subred de 24 bits que analizar, en formato `X.X.X` donde cada X puede tomar un valor entre 0 y 255 (p.e.: `192.168.0`), verificando que la entrada tiene el formato requerido. SE PUEDE REUTILIZAR EL CÓDIGO USADO EN EL EXAMEN DE LA 1ª EVALUACIÓN.
   - **b)** Lanzar pings a todos los hosts de la subred (del `X.X.X.1` al `X.X.X.254`) en paralelo y procesar la respuesta discerniendo si están arriba (valor de salida del proceso = 0) o abajo (cualquier otro valor). SE PUEDE REUTILIZAR EL CÓDIGO USADO EN EL EXAMEN DE LA 1ª EVALUACIÓN.
   - **c)** Escanear en paralelo todas las IPs que estén ARRIBA, averiguando qué puertos TCP están abiertos (a la escucha) dentro del rango de 0 a un número máximo fijable mediante una constante (p.e.: 1000) y recopilar la información.
   - **d)** El programa principal deberá ir mostrando la información recopilada por cada hilo conforme vayan terminando su ejecución, tras haber acabado el escaneo de todos los puertos de la IP en el rango configurado.
   - **e)** Solo deben mostrarse las IPs activas, informando al menos del host remoto y el número de los puertos abiertos.
   - **f)** Para mostrar el servicio asociado a los puertos, dentro del rango de los WELL KNOWN PORTS, la aplicación debería descargarse el fichero txt disponible en la URL `http://ftp.sun.ac.za/ftp/pub/documentation/security/port-numbers.txt` y procesar su contenido para extraer la asociación entre cada puerto (solo para los TCP) y el servicio correspondiente.

3. Generad un fichero `.zip` con el contenido de la carpeta src del proyecto y subidlo al aula virtual con el siguiente nombre:  
   `DAM2_PSP_UT3_Actividad1_Nombres-alumnos`

---

## 3. Evaluación

- La evaluación se realizará conforme a la siguiente lista de cotejo.
- La presentación de trabajos **no genuinos o plagiados** conllevará una calificación de **0 puntos** en la actividad para todos los alumnos involucrados.
- La actividad deberá entregarse antes de la fecha de cierre configurada en el aula virtual para ser evaluada. De no ser así, se puntuará con un 0. Se reabrirá el plazo de entrega para las convocatorias de Ordinaria y Extraordinaria para aquellos alumnos que deban presentarse a ellas.
- Tras la entrega, el docente podrá realizar preguntas a los alumnos para evaluar su grado de contribución a la actividad.

---

## Lista de Cotejo – Herramienta de escaneo de redes

| Criterio de evaluación | Sí | No | Observaciones |
|---|---|---|---|
| 1. **(1 punto)** Recopila la información de la subred de 24 bits a analizar, verificando que tiene el formato requerido (X.X.X). | ☐ | ☐ | |
| 2. **(1 punto)** La aplicación lanza los pings en paralelo a todas las IPs del rango de 1 a 254 de la subred y recopila la información sobre si están arriba o abajo. | ☐ | ☐ | |
| 3. **(1 punto)** La aplicación discierne y actúa correctamente en función de si se trata de un sistema Windows o Linux al lanzar los pings. | ☐ | ☐ | |
| 4. **(1 punto)** La aplicación es capaz de abrir conexiones contra los hosts que están activos dentro de la subred y verificar el estado de todos los puertos desde 0 hasta el máximo definido como constante. | ☐ | ☐ | |
| 5. **(1 punto)** El escaneo de las diferentes IPs se realiza en paralelo. | ☐ | ☐ | |
| 6. **(1 punto)** La aplicación identifica correctamente los puertos abiertos para la IP contra la que se ha abierto la conexión. | ☐ | ☐ | |
| 7. **(2 puntos)** El programa principal recopila y muestra por consola correctamente la información de cada hilo escaneando una IP como muestra la imagen de ejemplo (a excepción del servicio asociado al puerto). | ☐ | ☐ | |
| 8. **(2 puntos)** La aplicación es capaz de procesar el fichero de la URL proporcionada con puertos conocidos de IANA y asociar los servicios a cada puerto abierto como muestra la imagen de ejemplo. | ☐ | ☐ | |

**Puntuación máxima: 10 puntos.**
