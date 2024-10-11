# Conversor de Monedas

## Descripción
Este es un proyecto en Java que permite convertir monedas locales a dólar estadounidense y viceversa. Utiliza una API para obtener la tasa de cambio actual, lo que permite realizar conversiones en tiempo real. Además, la aplicación detecta automáticamente la moneda local basada en la dirección IP del usuario.

## Tecnologías Usadas
- **Java**: El lenguaje principal en el que está construido el proyecto.
- **Gson**: Una biblioteca que facilita el trabajo con datos en formato JSON, que es el formato utilizado para la comunicación con la API.
- **API externa**: Utiliza ip-api.com para detectar la ubicación y la moneda local, y exchangerate-api.com para obtener las tasas de cambio.

## Estructura del Proyecto
El proyecto está compuesto por varias clases que cumplen funciones específicas:

1. **HttpHelper**: Esta clase se encarga de gestionar las solicitudes a la API. Facilita la conexión y la obtención de datos en formato JSON de manera sencilla y reutilizable.

2. **Country**: Aquí se maneja la detección del país del usuario a través de su dirección IP. También contiene un arreglo que relaciona códigos de países con sus respectivas monedas.

3. **ExchangeRateAPI**: Esta clase se ocupa de comunicarse con la API de tasas de cambio. Utiliza la clave de API para autenticar las solicitudes y obtiene la tasa de cambio de USD a la moneda local.

4. **Index**: Esta es la clase principal donde el usuario interactúa. Permite al usuario elegir entre convertir de dólares a su moneda local o viceversa. También muestra los resultados de las conversiones.

## Cómo Funciona
1. **Detección Automática de Moneda**: Cuando el usuario inicia la aplicación, esta detecta su moneda local utilizando su dirección IP. Esto se hace llamando a la API de ip-api.com.
  
2. **Obtención de la Tasa de Cambio**: Después de identificar la moneda local, la aplicación se comunica con la API de exchangerate-api.com para obtener la tasa de cambio actual entre el dólar y la moneda local.

3. **Conversión de Monedas**: Una vez que se tiene la tasa de cambio, el usuario puede elegir convertir dólares a su moneda local o viceversa. La aplicación solicita la cantidad y realiza la conversión, mostrando el resultado.

## Cualidades Destacadas
- **Interfaz Simple**: La aplicación se ejecuta en la consola y permite al usuario realizar conversiones de manera rápida y sin complicaciones.
- **Detección Automática**: El usuario no necesita ingresar manualmente su moneda local; la aplicación lo hace automáticamente, lo que mejora la experiencia de uso.
- **Uso de APIs**: Al utilizar APIs externas, el proyecto se mantiene actualizado con las tasas de cambio reales, garantizando resultados precisos.

## Ejecución
Para ejecutar la aplicación, asegúrate de tener Java 23 instalado. Luego, compila y ejecuta la clase `Index`. Recuerda reemplazar la clave de API en el código por tu propia clave para que funcione correctamente.
