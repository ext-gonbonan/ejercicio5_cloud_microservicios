# Proyecto de Microservicios de Formación y Cursos

Este proyecto implementa una arquitectura de microservicios para gestionar formaciones y cursos. Está compuesto por varios servicios que trabajan en conjunto para proporcionar una solución escalable y flexible.

## Arquitectura

El sistema está compuesto por los siguientes componentes:

1. **Servidor Eureka**: Servicio de descubrimiento que permite a los microservicios registrarse y descubrirse entre sí.
2. **Servidor de Configuración**: Centraliza la configuración de todos los microservicios.
3. **API Gateway**: Punto de entrada único para todas las peticiones de los clientes.
4. **Servicio de Cursos**: Gestiona la información de los cursos (3 instancias).
5. **Servicio de Formación**: Maneja la lógica de formaciones (1 instancia).

## Servicios

### Servidor Eureka
- Puerto: 8761
- Función: Registro y descubrimiento de servicios

### Servidor de Configuración
- Puerto: 8888
- Función: Proporciona configuración centralizada para todos los microservicios

### API Gateway
- Puerto: 9000
- Función: Enrutamiento y filtrado de peticiones

### Servicio de Cursos
- Puerto: Dinámico (asignado por Eureka)
- Instancias: 3
- Función: CRUD de cursos y lógica relacionada

### Servicio de Formación
- Puerto: Dinámico (asignado por Eureka)
- Instancias: 1
- Función: Gestión de formaciones y su relación con los cursos

## Tecnologías Utilizadas

- Spring Boot
- Spring Cloud Netflix (Eureka)
- Spring Cloud Config
- Spring Cloud Gateway
- Spring Data JPA (para persistencia)
- Base de datos (especificar cuál, por ejemplo, MySQL, PostgreSQL, etc.)

## Configuración y Despliegue

1. Inicie el Servidor Eureka
2. Inicie el Servidor de Configuración
3. Inicie el API Gateway
4. Inicie las instancias del Servicio de Cursos
5. Inicie el Servicio de Formación

Asegúrese de que cada servicio esté correctamente configurado para conectarse al Servidor Eureka y al Servidor de Configuración.

## Uso

Este sistema se puede probar utilizando Postman. A continuación, se muestran algunos endpoints de ejemplo:

### Endpoints de Prueba

1. Probar el servicio de Formación:
   
      - GET http://localhost:9000/sformacion/formacion/test
      - Este endpoint verifica si el servicio de formación está funcionando correctamente.

2. Obtener todas las formaciones:
   
     - GET http://localhost:9000/sformacion/formacion
     - Retorna la lista de todas las formaciones disponibles.

3. Obtener todos los cursos:
   
     - GET http://localhost:9000/scursos/cursos
     - Retorna la lista de todos los cursos disponibles.
