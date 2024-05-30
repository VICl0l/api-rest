# API REST - RETO TÉCNICO GLOBAL HITSS

Esta proyecto API REST establece una conexión entre un proyecto back-end realizado con Spring Boot (Java) y una BD Oracle. 

Tiene la funcionalidad principal de consulta filtrada y mantenimiento parcial a una tabla 'producto' mediante un Stored Procedure (SP). 
El cual, en primera instancia, inserta un nuevo registro a la tabla según nombre y fecha de registro, para luego realizar una consulta a todos los registros del día.

## Tecnologías
- Java 17
- Oracle Database 21c
- Maven 3.9.6
- Spring Boot 3.3.0

## Herramientas
- SQL Developer
- Sring Tool Suite 4 (u otro IDE)
- Postman

## Procedimiento para la instalación de la API
1. Ejecutar mediante el usuario 'sysdba' (u otro usuario con los permisos necesarios) el 'dump.sql' para la creación de la Tabla, Índice y SP.
   El fichero 'query.sql' es solamente referencial y contiene las query DDL para estos mismos objetos. 
3. Clonar el repositorio mediante 'git clone https://github.com/VICl0l/api-rest.git'
4. Abrir el repositorio mediante Spring Tool Suite 4 (u otro IDE con soporte) y levantar el proyecto.
5. Importar la colección 'GLOBALHITSS.postman_collection.json' en POSTMAN y ejecutar el request contenido.
