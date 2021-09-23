# Cliente

El programa cliente fue escrito en Java (v14) y requiere ser compilado previamente
antes de ejecutarlo. A continuación, se muestran los pasos para hacer esto de
forma manual, sin la necesidad de tener VS Code listo para Java o un IDE.

## Compilar

Para compilar hay que seguir los pasos siguientes:

1. Abrir una consola o terminal y posicionarnos en la ruta `<proyecto>/cliente/src/`
2. Ejecutar el siguiente comando: `javac -d ../bin Run.java.`

Se creará una carpeta bin dentro de cliente en la cual tendremos nuestro proyecto
compilado y listo para ser ejecutado.

Ejecutar

Vamos a la carpeta donde se guardó nuestro proyecto compilado, por defecto 
`<proyecto>/cliente/bin/`, y ejecutamos uno de los siguientes comandos:
- `java Run` – Ejecuta el cliente en modo consola
- `java Run --gui` o `java Run -g` – Ejecuta el cliente con interfaz gráfica.

