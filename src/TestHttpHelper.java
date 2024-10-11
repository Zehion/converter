import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class TestHttpHelper {

    public static void main(String[] args) {
        try {
            // URL de la API que devuelve información basada en la IP del usuario
            String url = "http://ip-api.com/json/";

            // Llamada al metodo que obtiene la respuesta de la API
            String response = HttpHelper.getResponseFromURL(url);

            // Configuro Gson para que aplique formato "pretty print"
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement jsonElement = JsonParser.parseString(response);

            // Convierte el JSON a formato legible con saltos de línea
            String prettyJson = gson.toJson(jsonElement);

            // Imprimo el JSON en formato legible
            System.out.println("Respuesta formateada de la API: ");
            System.out.println(prettyJson);
//            Respuesta formateada de la API:
//            {
//                "status": "success",
//                    "country": "Colombia",
//                    "countryCode": "CO",
//                    "region": "DC",
//                    "regionName": "Bogota D.C.",
//                    "city": "Bogotá",
//                    "zip": "111411",
//                    "lat": 4.6115,
//                    "lon": -74.0833,
//                    "timezone": "America/Bogota",
//                    "isp": "Colombia Telecomunicaciones S.a. ESP",
//                    "org": "Colombia Telecomunicaciones S.a. ESP",
//                    "as": "AS3816 COLOMBIA TELECOMUNICACIONES S.A. ESP",
//                    "query": "191.107.31.234"
//            }

        } catch (Exception e) {
            // Si ocurre un error, lo imprimo
            System.out.println("Error durante la conexión: " + e.getMessage());
        }
    }
}