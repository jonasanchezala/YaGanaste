# Ejercicio 6

## Concurrencia

Cuando tenemos dos hilos que acceden a una misma variable al mismo tiempo, podemos usar la palabra reservada synchronized 
y encapsular la variable para que de este modo el acceso a la variable se haria uno a la vez.

``` java
public class GlobalData {
    private int sharedVariable;

    public synchronized int getSharedVariable() {
        return sharedVariable;
    }

    public synchronized void setSharedVariable(int value) {
        sharedVariable = value;
    }
}
```

Tambien si nuestra variable es una collection de datos podemos hacer uso de las implementacion que son thread safe por ejemplo
https://www.baeldung.com/java-synchronized-collections
