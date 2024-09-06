# User Management API

## Descripción

Esta API proporciona un endpoint para registrar usuarios, utilizando Spring Boot, JPA con H2 y JWT para la autenticación.

## Requisitos

- Java 17
- Gradle

## Configuración

1. Clona el repositorio.
2. Navega al directorio del proyecto.
3. Ejecuta `./gradlew build` para construir el proyecto.
4. Ejecuta `./gradlew bootRun` para iniciar la aplicación.

## Endpoints

### Registrar Usuario

- **URL**: `/users/register`
- **Método**: `POST`
- **Cuerpo**: JSON con formato:

```json
{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}
