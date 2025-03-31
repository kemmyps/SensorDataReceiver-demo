public class SensorData {
    private double temperature; // Temperatura medida pelo sensor
    private int humidity; // Umidade medida pelo sensor
    private int light; // Intensidade luminosa medida pelo sensor

    /**
     * Construtor da classe SensorData.
     * Inicializa os valores de temperatura, umidade e luminosidade.
     *
     * @param temperature Temperatura medida em graus Celsius.
     * @param humidity Umidade medida em percentual.
     * @param light Intensidade luminosa medida em Lux.
     */
    public SensorData(double temperature, int humidity, int light) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.light = light;
    }

    public double getTemperature() { return temperature; }
    public int getHumidity() { return humidity; }
    public int getLight() { return light; }

    /**
     * Verifica se a temperatura está alta.
     * @return true se a temperatura for maior ou igual a 27°C.
     */
    public boolean isTemperatureHigh() { return temperature >= 27; }

    /**
     * Verifica se a temperatura está moderada.
     * @return true se a temperatura estiver entre 25°C e 27°C.
     */
    public boolean isTemperatureModerate() { return temperature > 25 && temperature < 27; }

    /**
     * Verifica se a umidade está baixa.
     * @return true se a umidade for menor ou igual a 45%.
     */
    public boolean isHumidityLow() { return humidity <= 45; }

    /**
     * Verifica se a luminosidade está alta.
     * @return true se a intensidade luminosa for maior ou igual a 512 Lux.
     */
    public boolean isLightHigh() { return light >= 512; }

    /**
     * Retorna as ações a serem tomadas com base nos valores dos sensores.
     * @return String formatada com status dos sistemas de controle.
     */
    public String getSystemActions() {
        String acStatus = isTemperatureHigh() ? "🔴 Ligando o Ar-Condicionado!" :
                isTemperatureModerate() ? "🟡 Temperatura moderada, monitorando..." :
                        "🟢 Desligando o Ar-Condicionado, temperatura ideal.";

        String humidifierStatus = isHumidityLow() ? "🔴 Ligando o Umidificador!" : "🟢 Umidade adequada, desligando umidificador.";
        String lightsStatus = isLightHigh() ? "🔴 Luz muito intensa! Reduzindo iluminação." : "🟢 Iluminação adequada.";

        return String.format("\n=== Controle dos Sistemas ===\n" +
                        "❄ Ar-Condicionado: %s\n" +
                        "💦 Umidificador: %s\n" +
                        "💡 Iluminação: %s\n",
                acStatus, humidifierStatus, lightsStatus);
    }

    /**
     * Formata os dados do sensor para exibição amigável no console.
     * @return String formatada com os valores dos sensores e status dos sistemas.
     */
    public String formatData() {
        return String.format("\n===========================================\n" +
                        "📡 Leitura Atual dos Sensores 📡\n" +
                        "-------------------------------------------\n" +
                        "🌡  Temperatura:  %.2f°C  %s\n" +
                        "💧  Umidade:      %d%%  %s\n" +
                        "💡  Luminosidade: %d Lux  %s\n" +
                        "-------------------------------------------\n" +
                        "%s",
                temperature, isTemperatureHigh() ? "⚠ ALERTA: ALTA!" : "OK",
                humidity, isHumidityLow() ? "⚠ ALERTA: BAIXA!" : "OK",
                light, isLightHigh() ? "⚠ ALERTA: ALTA!" : "OK",
                getSystemActions());
    }
}
