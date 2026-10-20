# Sistema Saludador

Prototipo de **aplicación de escritorio** hecho con **Java 21** y **JavaFX 21**. Muestra un formulario sencillo (nombre, edad y hora en formato 12 h) y genera un saludo coherente con esos datos. No usa base de datos, internet ni páginas web.

## Requisitos exactos

| Requisito | Valor |
| --- | --- |
| Sistema | Windows 10 o Windows 11 |
| JDK | **Eclipse Temurin JDK 21 (LTS)**, 64 bits |
| JavaFX | 21.0.5 (lo descarga Maven; no se instala a mano) |
| Maven | No hace falta instalarlo: el proyecto incluye **Maven Wrapper 3.3.2** y Maven 3.9.9 |
| Red | La primera ejecución necesita internet para descargar Maven y JavaFX |

**Importante:** hace falta un **JDK** (incluye `java` y `javac`). Un **JRE** solo ejecuta programas ya compilados y **no alcanza** para este taller.

Combinación usada en el proyecto:

- JDK 21 (LTS)
- JavaFX 21.0.5
- `maven-compiler-plugin` 3.13.0
- `javafx-maven-plugin` 0.0.8

## Instalación del JDK en Windows

1. Entra a la página oficial de Adoptium (Eclipse Temurin):  
   [https://adoptium.net/temurin/releases/?os=windows&arch=x64&package=jdk&version=21](https://adoptium.net/temurin/releases/?os=windows&arch=x64&package=jdk&version=21)
2. Descarga el instalador **.msi** de **Temurin 21 (JDK)** para **Windows x64**.
3. Ejecuta el instalador.
4. Marca las opciones para:
   - instalar el **JDK** (no un JRE),
   - agregar Java al **PATH**,
   - definir **JAVA_HOME**.
5. Cierra **todas** las ventanas de PowerShell y abre una nueva.

### Cómo comprobar que el JDK funciona

En PowerShell:

```powershell
java -version
javac -version
echo $env:JAVA_HOME
```

Debes ver algo parecido a:

- `openjdk version "21.0.12.1"` (u otra revisión 21.x)
- `javac 21.0.12.1`
- `JAVA_HOME` = `C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\`

Si `java -version` muestra `1.8` y `javac` no existe, todavía está activo un JRE viejo: reinstala Temurin 21 y vuelve a abrir PowerShell.

## Cómo ejecutar en Windows

Abre PowerShell, entra a la carpeta del proyecto y usa el wrapper:

```powershell
cd "C:\Users\Ryzen 7 7700x\Desktop\sistema-saludador"
.\mvnw.cmd clean javafx:run
```

La primera vez puede tardar porque descarga Maven y JavaFX.

Para solo comprobar que compila:

```powershell
.\mvnw.cmd clean compile
```

## Cómo descargar el proyecto desde GitHub

Cuando el repositorio esté publicado (aún no es obligatorio para este prototipo):

1. En GitHub, abre el repositorio del proyecto.
2. Pulsa el botón verde **Code**.
3. Elige **Download ZIP** o copia la URL HTTPS.
4. Si descargas ZIP: extrae la carpeta y ábrela.
5. Si usas Git:

```powershell
git clone https://github.com/USUARIO/sistema-saludador.git
cd sistema-saludador
.\mvnw.cmd clean javafx:run
```

Sustituye `USUARIO` por el usuario real del repositorio.

## Cómo usar la aplicación

1. Se abre una ventana titulada **Sistema Saludador**.
2. Pulsa **Solicitar saludo**.
3. Completa nombre, edad, hora (1–12), minutos (00–59) y AM/PM.
4. Pulsa **Aceptar**.
5. Lee el saludo. Si hay errores, corrige los campos y vuelve a aceptar.
6. Pulsa **Solicitar otro saludo** para repetir el proceso.

## Regla de saludo según la hora (decisión del prototipo)

Esta regla **no** lee el reloj del computador: usa la hora que la persona escribe en el formulario.

| Saludo | Intervalo |
| --- | --- |
| Buenos días | desde las **5:00 AM** hasta **antes de las 12:00 PM** |
| Buenas tardes | desde las **12:00 PM** hasta **antes de las 6:00 PM** |
| Buenas noches | el resto de las horas (6:00 PM hasta antes de las 5:00 AM) |

Ejemplos de conversión 12 h → periodo:

- `12:00 AM` es medianoche → buenas noches
- `4:59 AM` → buenas noches
- `5:00 AM` → buenos días
- `11:59 AM` → buenos días
- `12:00 PM` es mediodía → buenas tardes
- `5:59 PM` → buenas tardes
- `6:00 PM` → buenas noches

## Ejemplos de prueba

| Nombre | Edad | Hora | Resultado esperado |
| --- | --- | --- | --- |
| Ana | 20 | 8:00 AM | `Buenos días, Ana. Tienes 20 años.` |
| Luis | 34 | 12:00 PM | `Buenas tardes, Luis. Tienes 34 años.` |
| Marta | 19 | 7:30 PM | `Buenas noches, Marta. Tienes 19 años.` |
| Pedro | 45 | 12:15 AM | `Buenas noches, Pedro. Tienes 45 años.` |
| (vacío) | 20 | 9:00 AM | Error: el nombre no puede estar vacío |
| Ana | 0 | 9:00 AM | Error: edad entre 1 y 120 |
| Ana | abc | 9:00 AM | Error: la edad debe ser un número entero |
| Ana | 20 | (sin hora) | Error: hay que elegir hora, minutos y AM/PM |

## Estructura del código

- `SistemaSaludadorApp`: ventana, botones y cambio de pantallas.
- `ValidadorEntrada`: revisa nombre, edad y hora.
- `GeneradorSaludo`: elige buenos días / tardes / noches y arma el texto.
- `DatosSaludo`: guarda los datos ya validados.

## Licencia de uso académico

Prototipo de taller universitario. Uso educativo.
