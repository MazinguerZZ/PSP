# ACTIVIDAD 5.1 Protegiendo aplicación y datos

**Ciclo Formativo de Grado Superior**  
**Desarrollo de Aplicaciones Multiplataforma**  
**Programación de Servicios y Procesos**

---

## 1. Objetivo

Demostrar conocimientos sobre protección de aplicaciones y datos definiendo y aplicando criterios de seguridad, considerando los siguientes aspectos:

- Identificar y aplicar principios y prácticas de programación segura (RA5-a).
- Analizar las principales técnicas y prácticas criptográficas (RA5-b).
- Definir e implantar políticas de seguridad para limitar y controlar el acceso de los usuarios a las aplicaciones desarrolladas (RA5-c).
- Utilizar esquemas de seguridad basados en roles (RA5-d).
- Emplear algoritmos criptográficos para proteger el acceso a la información almacenada (RA5-e).
- Identificar métodos para asegurar la información transmitida (RA5-f).
- Desarrollar aplicaciones que utilicen comunicaciones seguras para la transmisión de información (RA5-g).
- Depurar y documentar las aplicaciones desarrolladas (RA5-h).

---

## 2. Enunciado

**En parejas**, realizad la siguiente tarea competencial evaluable, relacionada con técnicas de programación segura:

1. Importar la aplicación en Java de la plantilla proporcionada por el profesor (`A5.1_PLANTILLA.zip`), que permite mediante su interfaz gráfica seleccionar dos opciones por defecto. Todas las ventanas e interfaces gráficos están ya listos en el paquete `psp.a51.ui` para la funcionalidad requerida, no siendo necesario modificarlos:

   - **REGISTRO**: Abre una nueva ventana para introducir el username, la contraseña y su rol en la aplicación. El alumno deberá introducir la lógica necesaria para registrar el usuario en un fichero que sea accesible con privilegios muy limitados en el sistema (`/data/myShadow.txt`).
   - **LOGIN**: Abre una nueva ventana para introducir el username y la contraseña del usuario. El alumno deberá introducir la lógica necesaria para autenticar el usuario verificando si en el fichero `myShadow.txt` figura el usuario y la contraseña proporcionada es la correcta.
   - Solo en el caso de que un usuario autenticado en el sistema tenga privilegios de **ADMIN**, el menú principal mostrará automáticamente una tercera opción en un botón adicional.

2. Añadir la siguiente funcionalidad sobre los métodos ya existentes y marcados con la frase `/** CÓDIGO A COMPLETAR **/`:

   - **a)** Crear un fichero `myShadow.txt`, cuando no exista ya, que cuente con permisos restringidos únicamente de lectura y escritura para el usuario con el que se ejecuta la aplicación. El fichero debe encontrarse dentro de la ruta `/data` del proyecto, que también deberá ser creada desde el código y accesible solo para el usuario que ejecuta la aplicación.
   - **b)** Cuando se registre un usuario, que no exista ya en el fichero `myShadow.txt`, deberá almacenarse en una nueva línea de este fichero la información sobre: `username; rol; salt; hash de la password`. Los datos de cada usuario deben estar separados con el carácter `:`.
   - **c)** Cuando se autentique un usuario deberá chequearse en el fichero `myShadow.txt` si la contraseña proporcionada es correcta, teniendo en cuenta el username con el que se intenta hacer el login.

3. La aplicación debería discernir el sistema operativo en el que esté operando (Windows o Linux) y ejecutarse correctamente independientemente del que se esté usando.

4. Generad un fichero `.zip` con el contenido de la carpeta src del proyecto y subidlo al aula virtual con el siguiente nombre:  
   `DAM2_PSP_UT5_Actividad1_Nombres-alumnos`

---

## 3. Evaluación

- La evaluación se realizará conforme a la siguiente lista de cotejo.
- La presentación de trabajos **no genuinos o plagiados** conllevará una calificación de **0 puntos** en la actividad para todos los alumnos involucrados.
- La actividad deberá entregarse antes de la fecha de cierre configurada en el aula virtual para ser evaluada. De no ser así, se puntuará con un 0. Se reabrirá el plazo de entrega para las convocatorias de Ordinaria y Extraordinaria para aquellos alumnos que deban presentarse a ellas.
- Tras la entrega, el docente podrá realizar preguntas a los alumnos para evaluar su grado de contribución a la actividad.
- Las funcionalidades que no sean ejecutables no podrán ser evaluadas.

---

## Lista de Cotejo – Protegiendo aplicación y datos

| Criterio de evaluación | Sí | No | Observaciones |
|---|---|---|---|
| 1. **(1 punto)** Correcta creación de la ruta `/data` dentro del proyecto y con los permisos estrictamente necesarios. | ☐ | ☐ | |
| 2. **(2 puntos)** Correcta creación del fichero `myShadow.txt`, dentro de la ruta `/data` con los permisos requeridos. | ☐ | ☐ | |
| 3. **(1,5 puntos)** Correcto registro de un nuevo usuario en el fichero `myShadow.txt`, incluyendo toda la información necesaria y codificada como se requiere. | ☐ | ☐ | |
| 4. **(0,5 puntos)** Correcta verificación de si un usuario existe antes de registrarlo. | ☐ | ☐ | |
| 5. **(2 puntos)** Correcta verificación del usuario y contraseña al realizar el login de un usuario, devolviendo toda la información necesaria para que la aplicación pueda determinar el rol del usuario. | ☐ | ☐ | |
| 6. **(2 puntos)** Correcta ejecución tanto en sistemas operativos Linux como Windows. | ☐ | ☐ | |
| 7. **(1 punto)** El código está documentado correctamente con Javadoc y comentado. | ☐ | ☐ | |

**Puntuación máxima: 10 puntos.**
