public class TestCountry {

    public static void main(String[] args) {
        // Llama al metodo detectCountry() y muestra el resultado
        String currency = Country.detectCountry();

        if (currency != null) {
            System.out.println("Moneda detectada: " + currency);
        } else {
            System.out.println("No se pudo detectar la moneda.");
        }
    }
}
