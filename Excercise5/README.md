# Ejercicio 5
Se utilizo spring boot para crear los servicios rest.

### Request
``` bash
curl --location 'http://localhost:8080/customer/transfer' \
--header 'Content-Type: application/json' \
--data '{
    "customerSenderId": 1,
    "customerReceiverId": 2,
    "value": 10.0
}'
```
### Request
``` bash
200 ok
```
