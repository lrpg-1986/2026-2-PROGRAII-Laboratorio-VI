# Laboratorio VI - Programación II

Laboratorio práctico de diseño e implementación de API REST. Los diseños se
documentan con OpenAPI y las implementaciones se desarrollan con Java y
Spring Boot, utilizando listas en memoria e intercambio de información en JSON.

## Repositorio

<https://github.com/lrpg-1986/2026-2-PROGRAII-Laboratorio-VI>

## Estructura

| Ejercicio | Contenido | Archivo o proyecto principal |
|---|---|---|
| 1 | Diseño de la API de libros | `LaboratorioVI/Ejercicio1/openapi.yaml` |
| 2 | Diseño de la API de cursos | `LaboratorioVI/Ejercicio2/openapi.yaml` |
| 3 | Diseño de la API de reservas | `LaboratorioVI/Ejercicio3/openapi.yaml` |
| 4 | Implementación de la API de libros | `LaboratorioVI/Ejercicio4` |
| 5 | Implementación de la API de cursos | `LaboratorioVI/Ejercicio5` |
| 6 | Implementación de la API de reservas | `LaboratorioVI/Ejercicio6` |

## Tecnologías

- OpenAPI 3.0.3.
- Java 17 o superior.
- Spring Boot 3.5.16.
- Maven 3.6.3 o superior.
- JUnit 5.

## Organización de las implementaciones

Cada proyecto de Spring Boot está organizado en las siguientes capas:

- `controller`: recibe las solicitudes HTTP y devuelve las respuestas JSON.
- `service`: contiene las reglas del negocio.
- `repository`: administra la lista de datos en memoria.
- `model`: contiene las clases y enumeraciones del dominio.
- `dto`: define los datos recibidos y las respuestas de error.
- `exception`: centraliza el manejo de errores HTTP.

## Ejecutar una API

Abrir Git Bash en la carpeta del ejercicio que se desea ejecutar. Por ejemplo:

```bash
cd LaboratorioVI/Ejercicio4
mvn spring-boot:run
```

Las aplicaciones utilizan el puerto `8080`. Por esa razón, se debe ejecutar
solamente una API a la vez.

## Ejecutar las pruebas

Desde la carpeta de cualquiera de los ejercicios 4, 5 o 6:

```bash
mvn test
```

## Rutas principales

### API de libros

- `POST /libros`
- `GET /libros`
- `GET /libros/titulo/{titulo}`
- `PUT /libros/{id}`
- `DELETE /libros/{id}`

### API de cursos

- `POST /cursos`
- `GET /cursos`
- `GET /cursos/codigo/{codigo}`
- `PUT /cursos/{id}`
- `DELETE /cursos/{id}`

### API de reservas

- `POST /reservas`
- `GET /reservas`
- `GET /reservas/{id}`
- `PUT /reservas/{id}`
- `PATCH /reservas/{id}/cancelacion`

## Nota

Los datos se conservan únicamente mientras la aplicación está en ejecución.
Al detenerla, las listas en memoria se reinician.
