# Universidad Tecnologica de Mexico

## Materia:
Aplicaciones Moviles

## Equipo 16
 Integrantes
 Tellez Orozco Claudia Camila  
 Martinez Penaloza Jose Hernando

## TicketsApp

Aplicación móvil en Android (Jetpack Compose + Room + CameraX + ML Kit) para capturar tickets, almacenar su información y extraer automáticamente monto y fecha mediante OCR.


## Características principales

  * Crear tickets con título, monto, fecha y fotografía.
  
  * Captura de imagen integrada con CameraX.
  
  * Procesamiento OCR con ML Kit para:
  
  * Extraer texto del ticket.
 
  * Sugerir automáticamente monto.
  
  * Sugerir automáticamente fecha (normalizada a YY/MM/DD).
  
  * Cancelar foto (borra archivo y limpia estado).
  
  * Mostrar/ocultar texto detectado.
  
  * Base de datos local con Room.
  
  * UI moderna con Jetpack Compose.
  
  * Lista, detalle y eliminación de tickets.
  
## Tecnologías y librerías utilizadas
 
 ### Tecnología / Librería	Uso
 
 * Kotlin	Lenguaje principal
 * Jetpack Compose	Interfaz de usuario
 * Navigation Compose	Navegación entre pantallas
 * Room	Persistencia local (SQLite)
 * CameraX	Captura de fotos
 * ML Kit Text Recognition	OCR
 * Coil	Carga de imágenes
 * ViewModel + StateFlow	Manejo de estado

## Instalación y ejecución

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

## Explicación técnica del funcionamiento

 ### 1. Arquitectura general
     
   app/  
   ├── data/       → Base de datos, DAO, entidades y repositorio

   ├── ui/         → ViewModel y lógica de presentación
   
   ├── ui/screens/ → Pantallas Jetpack Compose
   
   ├── Routes.kt   → Rutas centralizadas de navegación
   
   └── MainActivity.kt → Host de navegación
   
  
 ### 2. Flujo interno de la aplicación
 
  
  **Agregar ticket**
  
   1.Usuario abre pantalla AddTicketScreen.
   
   2.Puede:
   
   Escribir datos manualmente
    
   Tomar foto del ticket
   
   3.Foto pasa por CameraScreen (CameraX) → se guarda en externalCacheDir.
   
   4.La ruta se almacena en ViewModel.ultimaRutaImagen.
   
   5.Usuario pulsa Leer texto del ticket:
   
   ML Kit procesa la imagen → InputImage.fromFilePath()
     
   Se obtiene result.text
     
   Se analiza texto:
   
   sugerirMontoDesdeTexto()
      
   sugerirFechaDesdeTexto()
      
   6.Campos se autocompletan.
   
   7.Usuario guarda → Room guarda el ticket.
  
  ### 3. Dependencias clave
   
  **Room**

  Ticket.kt → entidad SQLite
  
  TicketDao.kt → consultas SQL
  
  TicketsDatabase.kt → instancia de Room
  
  TicketRepository.kt → capa intermedia
  
  TicketsViewModel.kt → expone StateFlow<List<Ticket>>  

  
  
 **CameraX**
  
  Usa componentes:
  
  Preview
  
  ImageCapture
  
  ProcessCameraProvider   
  
