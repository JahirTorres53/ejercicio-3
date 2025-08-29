# ejercicio-3
Estructura de la aplicación
Interfaz gráfica (JFrame)
Es la ventana principal de la aplicación.
Tiene dos cajas de texto para escribir el nombre y el teléfono.

Tiene tres botones:
Agregar: inserta un nuevo contacto en la lista.
Modificar: permite cambiar el nombre o teléfono de un contacto seleccionado.
Eliminar: borra un contacto seleccionado.
Tiene una tabla (JTable) donde se muestran todos los contactos cargados.
Manejo de la información en memoria (Tabla)
Los contactos se guardan temporalmente en una tabla (JTable con DefaultTableModel).
Cada fila representa un contacto, con dos columnas: Nombre y Teléfono.

Manejo de Archivos (contactos.txt)
El programa guarda los contactos en un archivo de texto simple.
Al iniciar: el programa lee el archivo contactos.txt (si existe) y carga los contactos en la tabla.
Al cerrar: el programa escribe en el archivo todos los contactos de la tabla para que queden guardados.
Cada contacto se guarda en una línea con el formato: Nombre;Teléfono

Ejemplo:
Juan Pérez;12345678
María López;87654321

🔹 Flujo de uso del programa
El usuario abre el programa → se cargan los contactos desde el archivo.
Puede agregar, modificar o eliminar contactos desde la ventana.
La lista actualizada se muestra en la tabla.

Al cerrar el programa, todos los cambios se guardan en el archivo contactos.txt.

La próxima vez que se abra, se muestran los contactos guardados.
