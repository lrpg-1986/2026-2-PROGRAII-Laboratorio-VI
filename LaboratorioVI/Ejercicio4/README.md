# Ejercicio 4 - API de Libros

Implementación con Java y Spring Boot del diseño realizado en el Ejercicio 1.
Los datos se almacenan temporalmente en una lista en memoria y se intercambian
en formato JSON.

## Ejecutar el proyecto

```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080/libros`.

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/libros` | Registrar un libro |
| GET | `/libros` | Consultar todos los libros |
| GET | `/libros/titulo/{titulo}` | Consultar libros por título |
| PUT | `/libros/{id}` | Actualizar un libro |
| DELETE | `/libros/{id}` | Eliminar un libro |

## Ejemplo de JSON

```json
{
  "titulo": "Cien años de soledad",
  "autor": "Gabriel García Márquez",
  "isbn": "978-0307474728",
  "anioPublicacion": 1967,
  "estado": "DISPONIBLE"
}
```

