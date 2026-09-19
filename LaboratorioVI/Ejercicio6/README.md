# Ejercicio 6 - API de Reservas

Implementación con Java y Spring Boot del diseño realizado en el Ejercicio 3.
Los datos se almacenan temporalmente en una lista en memoria y se intercambian
en formato JSON.

## Ejecutar el proyecto

```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080/reservas`.

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/reservas` | Crear una reserva |
| GET | `/reservas` | Consultar todas las reservas |
| GET | `/reservas/{id}` | Consultar una reserva por ID |
| PUT | `/reservas/{id}` | Actualizar una reserva |
| PATCH | `/reservas/{id}/cancelacion` | Cancelar una reserva |

## Ejemplo de JSON

```json
{
  "nombreCliente": "Ana López",
  "habitacion": "204",
  "fechaEntrada": "2026-10-15",
  "fechaSalida": "2026-10-18",
  "estado": "CONFIRMADA"
}
```

