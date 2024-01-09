## Documentación del ejercicio para cencosud.
### Introducción:
Este proyecto de Android cuenta con la solución que diseñe para resolver el ejercicio propuesto de mostrar una lista de películas
y su detalle.
### Componentes utilizados:
- Para desplegar la información de las películas en pantalla se utilizó la librería de jetpack compose.
- Para transformar los posters de las películas de url a imágenes se utilizó la librería de glide compatible con jetpack compose.
  También se utilizaron otras librerías complementarias para garantizar el correcto funcionamiento de las funciones composables y el resto de
  componente.
- Para hacer la integración entre la vista y el resto de los componentes se utilizó la arquitectura mvvm.
- También se agregó el caso de uso para manejar la llamada a la api con el objetivo de integrar clean architecture.
- Se utilizo el patrón repositorio para la parte de llamadas a la api y room para mantener una base de datos local, con
  el cual persistir y devolver la lista de películas cuando no se tiene internet.
- La integración con la api que retornaba la lista de películas se utilizó la liberia de retrofit y gson.
- Se integro Hilt como solución para la inyección de dependencias ya que hace mucho más fácil manejar la parte de mantenimiento
  de instancias de los distintos objetos y hace la integración de room y retrofit muy sencilla.
### Resumen del diseño de la ui:
La idea fue hacer una app sencilla donde se mostrarán las películas disponibles por la api. Para esto se cuenta con dos vistas,
la vista principal que yo llamo dashboard donde se tiene una grilla que en cada ítem se muestra el poster de la película, el nombre, el año que salió en los cines y la fecha concreta de su lanzamiento.
Cuando el usuario da clic sobre un ítem de la lista, se abre la segunda vista nombrada MovieDescription donde se muestran mas detalles sobre la película.
### Resumen de diseño de las otras capas:
Decidí organizar el proyecto en una estructura de una carpeta por feature y otras carpetas para las cosas comunes del proyecto como helpers, la navegacion y las extensiones.
### Mejoras:
- Agregar mas unit test y otros tipos como instrumentation test
- Arreglar los unit test del networkUitls que no funcionan
- Mejorar la ui.