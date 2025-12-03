#**TicketsApp**

Aplicación móvil en Android (Jetpack Compose + Room + CameraX + ML Kit) para capturar tickets, almacenar su información y extraer automáticamente monto y fecha mediante OCR.

**Equipo 16**

Integrantes
Tellez Orozco Claudia Camila
Martinez Penaloza Jose Hernando

**Características principales**

  *Crear tickets con título, monto, fecha y fotografía.
  
  *Captura de imagen integrada con CameraX.
  
  *Procesamiento OCR con ML Kit para:
  
  *Extraer texto del ticket.
  
  *Sugerir automáticamente monto.
  
  *Sugerir automáticamente fecha (normalizada a YY/MM/DD).
  
  *Cancelar foto (borra archivo y limpia estado).
  
  *Mostrar/ocultar texto detectado.
  
  *Base de datos local con Room.
  
  *UI moderna con Jetpack Compose.
  
  *Lista, detalle y eliminación de tickets.

**Tecnologías y librerías utilizadas**

Tecnología / Librería	Uso

*Kotlin	Lenguaje principal
*Jetpack Compose	Interfaz de usuario
*Navigation Compose	Navegación entre pantallas
*Room	Persistencia local (SQLite)
*CameraX	Captura de fotos
*ML Kit Text Recognition	OCR
*Coil	Carga de imágenes
*ViewModel + StateFlow	Manejo de estado

**Instalación y ejecución**

1. Clonar el repositorio
   
git clone https://github.com/Back-01/TicketsApp.git
cd TicketsApp

2. Abrir en Android Studio

File > Open

Seleccionar la carpeta del proyecto.

3. Sincronizar Gradle

Android Studio descargará automáticamente las dependencias (CameraX, ML Kit, Room, etc.).

4. Ejecutar en dispositivo físico

Debido a CameraX, se recomienda usar un teléfono real:

Activar Opciones de desarrollador.

Activar Depuración USB.

Conectar el dispositivo a la PC.

Seleccionar el teléfono en Android Studio y presionar Run.
