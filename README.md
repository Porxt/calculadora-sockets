## Calculadora Sockets

Calculadora creada en Java que implementa sockets, actualmente UDP, para
comunicarse con los servidores, escritos en Python que finalmente se encargan de
resolver las operaciones solicitadas.

### ¿Por qué hacer esto?

El objetivo de hacer esta calculadora no está en la funcionalidad en si misma
sino en dar un ejemplo introductorio a la programación y uso de sockets.

### Arquitectura

El proyecto se basa en la arquitectura siguiente

![Arquitectura](arquitectura.png)

Una vez iniciado el servidor de nombres, iniciamos los servidores que ofrecen
los distintos servicios y estos (1) enviaran una solicitud de registro al
servidor de nombres. (2) El servidor de nombres enviara un mensaje de
confirmación de su registro.

Con la comunicación ya establecida ahora podemos trabajar.

(3) La calculadora envía una operación con sus datos, el servidor de nombres
interpretará la operación y (4) enviará los datos al servidor correspondiente,
quien (5) responderá con el resultado de la operación o un mensaje de error,
ejemplo de esto es si se intenta hacer una división por cero, (6) la respuesta
será inmediatamente enviada por el servidor de nombres al cliente sin procesar
nada. Si la operación solicitada no tiene un servidor registrado capaz de
atenderla el servidor de nombres devuelve un mensaje de error inmediatamente.

### Estructura del proyecto

El proyecto esta estructurado en dos carpetas cliente y servidor.

- **cliente:** Contiene la carpeta src donde esta el código sin compilar de la
  calculadora para saber más sobre como funciona el cliente y como compilarlo
  vaya a su documentación.
- **servidor:** Contiene los servidores cuyos scripts siguen el siguiente
  formato de nombramiento servidor\_<nombre>.py, en la sección de configuración
  por defecto tiene la lista de los nombres de los servidores. También tenemos
  el script connection.py, contiene la clase ConnectionHelper.

### Configuración por defecto

Por defecto el proyecto funciona en localhost y utiliza los puertos
20000 – 20006

| Nombre | Puerto | Host | Operación |
| ------ | ------ | ---- | --------- |
| nombres | 20000 | localhost | - |
| pow | 20001 | localhost | a ** b |
| mod | 20002 | localhost | a % b |
| sum | 20003 | localhost | a + b |
| sub | 20004 | localhost | a - b |
| mul | 20005 | localhost | a * b |
| div | 20006 | localhost | a / b |