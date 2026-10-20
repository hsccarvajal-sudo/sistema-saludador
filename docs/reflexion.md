# Plantilla de reflexión — Sistema Saludador

Usa este archivo para escribir **tu** reflexión del taller. Las preguntas están vacías a propósito: no copies respuestas inventadas.

## 1. Retos

- ¿Qué parte del prototipo te resultó más difícil y por qué?
- ¿Qué error viste en la terminal o en la ventana y cómo lo resolviste?
- ¿Qué harías distinto si volvieras a empezar el proyecto?
  
RESPUESTA:

La parte más complicada fue configurar el entorno para ejecutar el proyecto. 
Mi equipo tenía un JRE de Java 8, pero no contaba con el compilador javac ni con JAVA_HOME. Lo resolví instalando Eclipse Temurin JDK 21, configurando las variables necesarias y reiniciando Cursor.

Si empezara nuevamente, primero comprobaría las versiones de Java y las herramientas instaladas antes de generar el código.

## 2. Descubrimientos

- ¿Qué aprendiste sobre Java, JavaFX o Maven que no sabías al inicio?
- ¿Qué diferencia encontraste entre un JRE y un JDK?
- ¿Para qué sirve el Maven Wrapper (`mvnw.cmd`) en Windows?
  
RESPUESTA:

Aprendí que tener Java instalado no significa tener todo lo necesario para desarrollar. 
El JRE permite ejecutar programas, mientras que el JDK incluye herramientas como el compilador.

También entendí mejor la función de JavaFX para construir la interfaz y de Maven para gestionar las dependencias. El Maven Wrapper permite utilizar Maven desde el proyecto con mvnw.cmd, sin instalarlo de forma global.

## 3. Sorpresas

- ¿Qué comportamiento de la aplicación no esperabas?
- ¿Alguna herramienta o mensaje de error te sorprendió? ¿Cuál y por qué?
- ¿La regla de buenos días / tardes / noches coincidió con lo que tú dirías en la vida real?
  
RESPUESTA:

Me llamó la atención que el formulario mostrara varios mensajes de validación al enviarlo vacío, en lugar de cerrar la aplicación o continuar con datos incompletos. 
También comprobé que, al ingresar las 10:22 PM, mostraba “Buenas noches” junto con el nombre y la edad.

Al principio pensé que el mensaje que apareció después de cerrar la ventana podía indicar un problema, pero la terminal reportó BUILD SUCCESS. 
La regla de horarios me parece razonable, aunque en la vida real la forma de saludar puede variar según la costumbre de cada persona.

## 4. Estructura del código

- ¿Qué clase se encarga de la ventana y cuál se encarga de la lógica del saludo?
- ¿Por qué conviene separar la validación de la interfaz?
- Si mañana agregaras un campo nuevo (por ejemplo, apellido), ¿qué archivos tendrías que tocar?
  
RESPUESTA:

La clase SistemaSaludadorApp se encarga de la ventana y de la interacción con el usuario.
GeneradorSaludo construye el saludo, mientras que ValidadorEntrada revisa los datos y DatosSaludo los representa.

Separar la validación de la interfaz ayuda a mantener el código organizado y facilita modificar las reglas sin mezclar todo con los botones y campos.

Si agregara un apellido, revisaría el formulario, la clase de datos, las validaciones y el generador del saludo para decidir cómo incorporarlo.

## 5. Herramientas

- ¿Qué versión de JDK usaste y cómo lo comprobaste?
- ¿Qué comando usaste para compilar y cuál para ejecutar?
- ¿Qué archivos no deben subirse a GitHub y por qué existen en `.gitignore`?
  
RESPUESTA:

Utilicé Cursor como entorno de desarrollo y Eclipse Temurin JDK 21.0.12.1. Comprobé la instalación con:
java -version
javac -version
echo $env:JAVA_HOME
Para compilar y ejecutar utilicé:
.\mvnw.cmd clean compile
.\mvnw.cmd javafx:run
La carpeta target contiene resultados de la compilación y no necesita publicarse porque se puede generar nuevamente. 
El .gitignore permite excluir esos archivos y otros temporales. En cambio, conservé .mvn y los scripts del Wrapper para facilitar la ejecución del proyecto.

## Notas libres

Cursor me ayudó a generar y organizar el prototipo, pero fue necesario configurar el entorno, revisar los resultados y probar la aplicación manualmente. 
El taller me permitió recorrer el proceso desde los requerimientos hasta la publicación en GitHub, aunque todavía necesito profundizar en algunas partes del código generado.
