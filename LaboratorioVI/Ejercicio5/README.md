# Ejercicio 5 - API de Cursos

Implementación con Java y Spring Boot del diseño realizado en el Ejercicio 2.
Los datos se almacenan temporalmente en una lista en memoria y se intercambian
en formato JSON.

## Ejecutar el proyecto

```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080/cursos`.

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/cursos` | Crear un curso |
| GET | `/cursos` | Consultar todos los cursos |
| GET | `/cursos/codigo/{codigo}` | Consultar un curso por código |
| PUT | `/cursos/{id}` | Actualizar un curso |
| DELETE | `/cursos/{id}` | Eliminar un curso |

## Ejemplo de JSON

```json
{
  "nombre": "Programación II",
  "codigo": "PROG-201",
  "creditos": 4,
  "estado": "ACTIVO"
}
```

