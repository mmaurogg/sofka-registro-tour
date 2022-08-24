# sofka-registro-tour
Repositorio para entrega de ejercicio práctico de un sistema de registro para un evento como el Tour de Francia

# Ejercicio-JavaBackend
Aplicación CRUD Backend implementada con una arquitectura por capas, escrita en lenguaje Java usando el framework Spring Boot. 
Se implementa Maven como sistema para la gestión y construcción de la aplicación, y para la persistencia de datos una base de datos NoSQL con MongoDB.

## Enunciado
Uno de los eventos más importantes del ciclismo a nivel mundial es el Tour de Francia. Como parte del equipo de tecnología que apoya a la competición, se te ha encargado la tarea de desarrollar una aplicación o servicio que permita el registro de los equipos y sus respectivos ciclistas.

## Requerimientos
* Cada equipo debe tener como datos principales: nombre de equipo, un código abreviado único (alfanumérico, máximo 3 caracteres), y un país asociado.
* Cada ciclista debe tener como datos principales: nombre completo, un número de competidor único (máximo 3 dígitos), estar asociado a un equipo y un país de procedencia (nacionalidad).
* Un equipo de ciclismo estará conformado por un máximo de 8 corredores.
* Se sugiere la programación de APIs Rest para las actividades de inserción, modificación, consulta y eliminación de registros en las entidades consideradas (por ejemplo, registrar un equipo, modificar un equipo, registrar un ciclista, etc). La idea es seguir las buenas prácticas para el desarrollo de APIs Restful, como el uso de los métodos GET, POST, PUT y DELETE.
* Método que permita consultar los ciclistas pertenecientes a un equipo a partir del código de un equipo.
* Consulta de los equipos asociados a un determinado país.
* Consulta de los ciclistas por su nacionalidad.

## Base de datos
* Para correr el programa localmente, se debe contar con una base de datos mongodb en el puerto 27017

## Docker
* Pendiente para solucion de conflictos

