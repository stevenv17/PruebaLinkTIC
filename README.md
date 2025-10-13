# Despliegue

En una carpeta vacía ubicar el archivo docker-compose.yml y en esa misma carpeta descargar los microservicios del repositorio:

- **https://github.com/stevenv17/PruebaLinkTIC.git** (microservicio productos)
- **https://github.com/stevenv17/PruebaInventarioLinkTIC.git** (microservicio inventario)
- **https://github.com/stevenv17/PruebaCompraLinkTIC.git** (microservicio compra)

 ![imagen](./imagenes/imagen1.png)


Ubicarse en la raíz de cada uno de los microservicios y crear el archivo jar con el comando “**./mvnw clean package -DskipTest**” (Instalar Maven para este paso):

![imagen](./imagenes/imagen2.png)

![imagen](./imagenes/imagen3.png)

Después desde la ruta donde está ubicado el archivo docker-compose.yml ejecutar el comando “docker-compose up -d” para crear y subir los contenedores de los microservicios y la base de datos:

![imagen](./imagenes/imagen4.png)

También se puede subir uno por uno los servicios (orden recomendado):

- **docker-compose up mysql -d**
- **docker-compose up productos-service -d**
- **docker-compose up inventario-service -d**
- **docker-compose up compra-service -d**

![imagen](./imagenes/imagen5.png)


Conectarse a la DB de MySQL con las siguientes credenciales (password: pass):

![imagen](./imagenes/imagen6.png)
 
Y ejecutar los scripts del archivo linktic_scripts-mysql.sql para crear las tablas.


### Acceso a Swagger:

- http://localhost:8086/swagger-ui/index.html (producto)
- http://localhost:8087/swagger-ui/index.html (inventario)
- http://localhost:8088/swagger-ui/index.html (compra)
