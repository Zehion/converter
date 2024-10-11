import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ExchangeRateAPI {

    // Clase interna que modela la respuesta de la API de tasas de cambio.
    private static class ExchangeRateResponse {
        JsonObject conversion_rates; // Aquí se almacenan las tasas de conversión para distintas monedas.
    }

    // Clave de API que se utiliza para autenticar las solicitudes a la API de tasas de cambio.
    private final String apiKey;

    // Constructor que inicializa la instancia de ExchangeRateAPI con la clave de API proporcionada.
    public ExchangeRateAPI(String apiKey) {
        this.apiKey = apiKey; // Se almacena la clave de API para usarla en las solicitudes posteriores.
    }

    // Metodo principal para obtener la tasa de cambio de USD a la moneda local detectada.
    public double getExchangeRate() {
        // Detecta automáticamente la moneda local utilizando la dirección IP del usuario.
        String localCurrency = getCurrencyCodeFromIP();
        if (localCurrency == null) {
            // Si no se pudo detectar la moneda local, imprime un mensaje y devuelve 0.0.
            System.out.println("No se pudo detectar la moneda local.");
            return 0.0;
        }

        // Construye la URL para la solicitud de la tasa de cambio desde USD a la moneda local.
        String urlExchangeRate = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";
        double rate = 0.0;

        try {
            // Llama al metodo HttpHelper para obtener la respuesta JSON de la API.
            String jsonResponse = HttpHelper.getResponseFromURL(urlExchangeRate);
            System.out.println("Respuesta de la API: " + jsonResponse);

            // Verifica que la respuesta no esté vacía antes de intentar procesarla.
            if (!jsonResponse.isEmpty()) {
                // Utiliza Gson para convertir la respuesta JSON en un objeto ExchangeRateResponse.
                Gson gson = new Gson();
                ExchangeRateResponse response = gson.fromJson(jsonResponse, ExchangeRateResponse.class);

                // Obtiene la tasa de conversión de USD a la moneda local desde el objeto JSON.
                rate = response.conversion_rates.get(localCurrency).getAsDouble();
                System.out.println("Tasa de cambio USD a " + localCurrency + ": " + rate);
            } else {
                // Imprime un mensaje si la respuesta de la API está vacía.
                System.out.println("La respuesta de la API está vacía.");
            }

        } catch (Exception e) {
            // Captura cualquier excepción que ocurra durante la obtención de la tasa de cambio
            // e imprime el mensaje de error correspondiente.
            System.out.println("Error al obtener la tasa de cambio: " + e.getMessage());
        }

        // Devuelve la tasa de conversión obtenida (USD a moneda local).
        return rate;
    }

    // Metodo privado que obtiene el código de moneda local basándose en la detección de país.
    private String getCurrencyCodeFromIP() {
        // Llama al metodo detectCountry() de la clase Country para obtener la moneda local.
        return Country.detectCountry();
    }
}