import java.text.DecimalFormat;
import java.util.Scanner;

public class Index {

    // Instancia de ExchangeRateAPI utilizada para obtener la tasa de cambio.
    private final ExchangeRateAPI exchangeRateAPI;

    // Constructor que inicializa la clase CurrencyConverter con una instancia de ExchangeRateAPI.
    public Index(ExchangeRateAPI exchangeRateAPI) {
        this.exchangeRateAPI = exchangeRateAPI; // Se almacena la instancia para utilizarla en la conversión de divisas.
    }

    // Metodo que convierte entre dólares y la moneda local basada en la tasa de cambio proporcionada.
    public void convertCurrency(double rate, String currency) {
        // Verifica que la tasa de cambio sea válida (mayor que 0).
        if (rate <= 0) {
            System.out.println("La tasa de cambio no es válida.");
            return; // Sale del metodo si la tasa no es válida.
        }

        Scanner scanner = new Scanner(System.in);
        // Muestra las opciones de conversión al usuario.
        System.out.println("Seleccione la conversión:");
        System.out.println("1. De Dólares a " + currency);
        System.out.println("2. De " + currency + " a Dólares");

        // Captura la elección del usuario.
        int choice = scanner.nextInt();
        // Configura el formato decimal para la salida.
        DecimalFormat df = new DecimalFormat("#.##");

        // Realiza la conversión según la elección del usuario.
        switch (choice) {
            case 1:
                // Convierte de Dólares a la moneda local.
                System.out.print("Ingrese la cantidad en Dólares: ");
                double dollars = scanner.nextDouble();
                double localCurrencyFromDollars = dollars * rate; // Calcula el equivalente en la moneda local.
                System.out.println(dollars + " Dólares equivalen a " + df.format(localCurrencyFromDollars) + " " + currency + ".");
                break;

            case 2:
                // Convierte de la moneda local a Dólares.
                System.out.print("Ingrese la cantidad en " + currency + ": ");
                double localCurrency = scanner.nextDouble();
                double dollarsFromLocalCurrency = localCurrency / rate; // Calcula el equivalente en Dólares.
                System.out.println(localCurrency + " " + currency + " equivalen a " + df.format(dollarsFromLocalCurrency) + " Dólares.");
                break;

            default:
                // Maneja una selección no válida del usuario.
                System.out.println("Selección no válida.");
                break;
        }
    }

    // Metodo principal que inicia la aplicación de conversión de divisas.
    public static void main(String[] args) {
        // Define la clave de API para acceder al servicio de tasas de cambio.
        String apiKey = "e25b92b2f97cdefad90a1350"; // Tu clave de API
        // Crea una instancia de ExchangeRateAPI.
        ExchangeRateAPI exchangeRateAPI = new ExchangeRateAPI(apiKey);
        // Obtiene la tasa de cambio actual.
        double rate = exchangeRateAPI.getExchangeRate();
        // Detecta la moneda local basada en la dirección IP del usuario.
        String currency = Country.detectCountry();

        // Si se detectó una moneda válida, realiza la conversión.
        if (currency != null) {
            System.out.println("Moneda detectada: " + currency);
            Index converter = new Index(exchangeRateAPI);
            converter.convertCurrency(rate, currency); // Llama al metodo de conversión.
        } else {
            // Si no se detectó una moneda válida, imprime un mensaje de error.
            System.out.println("No se pudo detectar una moneda válida para la conversión.");
        }
    }
}