import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class Country {
    /*
     * Este es un array bidimensional que asocia códigos de países (por ejemplo, "US" para Estados Unidos)
     * con sus respectivas monedas (como "USD" para el dólar estadounidense).
     *
     * Se declara como static final porque estos datos son constantes que no cambiarán durante la ejecución del programa.
     */
    private static final String[][] countryCurrencyArray = {
            {"US", "USD"}, {"CA", "CAD"}, {"MX", "MXN"},
            {"BZ", "BZD"}, {"CR", "CRC"}, {"SV", "SVC"},
            {"GT", "GTQ"}, {"HN", "HNL"}, {"NI", "NIO"},
            {"PA", "PAB"}, {"AG", "XCD"}, {"BS", "BSD"},
            {"BB", "BBD"}, {"HT", "HTG"}, {"JM", "JMD"},
            {"KN", "XCD"}, {"LC", "XCD"}, {"VC", "XCD"},
            {"DO", "DOP"}, {"TT", "TTD"}, {"AR", "ARS"},
            {"BO", "BOB"}, {"BR", "BRL"}, {"CL", "CLP"},
            {"CO", "COP"}, {"EC", "USD"}, {"GY", "GYD"},
            {"PY", "PYG"}, {"PE", "PEN"}, {"SR", "SRD"},
            {"UY", "UYU"}, {"VE", "VES"}
    };

    /*
     * Este metodo se encarga de detectar el país del usuario
     * basándose en su ubicación, utilizando la API ip-api.com para obtener
     * la información de geolocalización.
     */
    public static String detectCountry() {
        // Inicializa la variable country como null, lo que indica que no hay un valor por defecto para la moneda.
        String country = null;

        try {
            // Llama al metodo HttpHelper para obtener la respuesta JSON de la API ip-api.com
            String jsonResponse = HttpHelper.getResponseFromURL("http://ip-api.com/json");

            // Utiliza Gson para convertir el contenido de la respuesta (en formato JSON) en un objeto JsonObject.
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(jsonResponse, JsonObject.class);

            // Extrae el código de país (countryCode) de la respuesta JSON
            // utilizando el metodo getAsString().
            String detectedCountry = json.get("countryCode").getAsString();

            // Crea un HashMap para asociar códigos de país con sus respectivas monedas.
            Map<String, String> countryCurrencyMap = new HashMap<>();

            // Itera sobre cada entrada del array countryCurrencyArray
            // y agrega la relación entre el código de país (ej. "US") y su moneda (ej. "USD") al mapa.
            for (String[] entry : countryCurrencyArray) {
                countryCurrencyMap.put(entry[0], entry[1]);
            }

            // Usa el código de país detectado para buscar su moneda en el mapa countryCurrencyMap.
            country = countryCurrencyMap.get(detectedCountry);

            // Si no se encuentra una moneda para el país detectado,
            // imprime un mensaje indicando que no se pudo encontrar la moneda.
            if (country == null) {
                System.out.println("Moneda no encontrada para el país: " + detectedCountry);
            }

        } catch (Exception e) {
            // Captura cualquier excepción que ocurra durante el proceso
            // e imprime un mensaje de error.
            System.out.println("Error al detectar el país: " + e.getMessage());
        }

        // Devuelve la moneda correspondiente al país detectado.
        // Si no se detectó un país válido, devuelve null.
        return country;
    }
}