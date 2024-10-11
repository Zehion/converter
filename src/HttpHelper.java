import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class HttpHelper {

    public static String getResponseFromURL(String urlString) throws Exception {
        // Esta clase la creé para facilitar la conexión a cualquier API que devuelva datos en formato JSON
        // (en mi caso, la API ip-api.com para obtener la localización basada en la IP).
        // El metodo getResponseFromURL es útil para hacer solicitudes GET y obtener las respuestas en forma de String.
        // Se decidió hacer esto genérico para poder reutilizarlo en distintas partes de la aplicación.

        // Creo una instancia de URI para encapsular la URL proporcionada. Esto asegura que la URL esté bien formada.
        URI uri = new URI(urlString);
        // Convierto el URI en una instancia de URL para poder abrir una conexión HTTP.
        URL url = uri.toURL();

        // Abro la conexión HTTP utilizando HttpURLConnection y configuro la solicitud como GET,
        // que es el metodo más común para obtener datos de una API.
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Establezco el metodo GET, ya que solo estoy solicitando datos, no enviándolos.
        connection.setRequestMethod("GET");

        // Utilizo BufferedReader junto con InputStreamReader para leer la respuesta de la API línea por línea,
        // de esta forma puedo manejar respuestas largas sin problemas de memoria.
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        // StringBuilder es eficiente para concatenar múltiples líneas de texto.
        StringBuilder content = new StringBuilder();

        // Leo cada línea de la respuesta de la API y la añado al StringBuilder para construir el contenido completo.
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        // Después de leer la respuesta completa, cierro el BufferedReader y la conexión HTTP
        // para liberar recursos y evitar posibles fugas de memoria.
        in.close();
        connection.disconnect();

        // Devuelvo el contenido de la respuesta en forma de String. Generalmente, este será un JSON,
        // que luego puedo procesar en otros métodos o clases como necesite.
        return content.toString();
    }
}