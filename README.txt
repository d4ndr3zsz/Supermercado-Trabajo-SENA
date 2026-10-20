API REST de Supermercado



Tecnologías utilizadas
Java
Spring Boot
Spring Data JPA (Hibernate)
MySQL
Maven




estructura del Proyecto

El proyecto está organizado en arquitectura por capas:

controller: Clases @RestController con los endpoints expuestos para la API.
dto: Objetos de transferencia para las peticiones de entrada con validaciones (Bean Validation).
exception: Manejador global de errores (@RestControllerAdvice) y excepciones personalizadas.
entity: Modelos de datos mapeados a las tablas relacionales de MySQL.
repository: Interfaces de conexión a la base de datos que extienden de JpaRepository.
service: Lógica de negocio, validaciones y control de transacciones.






Cómo poner a funcionar el proyecto

1.descargar el repositorio.

2.Crear la Base de Datos:
   - Abrir DBeaver y conectarse al servidor MySQL, ejecutar el script en este repositorio para crear la base de datos supermercado_db.

3.Configurar las credenciales:
   - ir a el archivo src/main/resources/application.yml y asegúrate de colocar el usuario y la contraseña de MySQL (root y 123456).

4.Ejecutar la aplicación:
   - abrir el proyecto en VS Code y ejecutar la clase principal SupermarketApplication.java.

5.Probar los Endpoints:
   - importar la colección de Postman dentro de el repositorio para probar todas las operaciones CRUD y el registro de ventas de forma ordenada.