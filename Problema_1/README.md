# Notas Importantes

El proyecto está organizado en dos carpetas principales:

- **`src/`**: Contiene los archivos fuente con extensión `.java`.
- **`bin/`**: Contiene los archivos compilados generados automáticamente con extensión `.class`.

---

# Aclaración sobre el Problema 1 de la Actividad Práctica

En el enunciado original, se menciona inicialmente que la madre de Marco se desplaza **100 km diarios**. Sin embargo, en el siguiente problema se indica que se desplaza **80 km diarios**.

Para mantener coherencia y permitir que Marco pueda alcanzarla en el cálculo del programa, utilicé la velocidad de **80 km diarios** en lugar de 100 km.

Este comportamiento puede verificarse modificando la constante ubicada en la línea 22 del archivo correspondiente:

```java
final int VELOCIDAD_MADRE_MARCO = 80;
````
Si se cambia el valor a 100, el programa mostrará que Marco nunca consigue alcanzarla.

