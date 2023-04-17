# Ejercicio 1

La aplicacion fue creada con una arquitectura de tres capas:

1. Capa de presentacion: Es donde se contruye la interfaz de usuario
1. Capa de aplicacion: Esta capa contiene toda la logica de negocio
1. Capa de datos: Aqui se almacenan los datos

![Diagrama sin título drawio (1)](https://user-images.githubusercontent.com/7455572/232559712-f3004767-8c23-41b4-9501-0c2647034cf8.png)



## Front End
Para la creacion del Front se utilizo, React, Vite y tailwind.css. [Code](FrontEnd)

## Back End
Para el Backend se creo un microservicio utilizando Spring boot framework. [Code](BackEnd)

## Despliegue
La aplicacion fue dockerizada y desplegada en fly.io

[Live Preview](https://wandering-surf-8416.fly.dev/).

Usuarios de prueba

| username  | password |
| ------------- |:-------------:|
| username      | password    |
| pedro         | 123    |
| pablo     | 456     |
