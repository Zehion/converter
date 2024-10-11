public class TestExchangeRateAPI {

    public static void main(String[] args) {
        // Reemplaza "ApiKey" con una clave de API válida
        ExchangeRateAPI exchangeRateAPI = new ExchangeRateAPI("e25b92b2f97cdefad90a1350");

        // Llama al metodo getExchangeRate y muestra el resultado
        double rate = exchangeRateAPI.getExchangeRate();

        if (rate > 0) {
            System.out.println("Tasa de cambio obtenida: " + rate);
        } else {
            System.out.println("No se pudo obtener la tasa de cambio.");
        }
    }
}
