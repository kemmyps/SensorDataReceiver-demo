public class SensorDataProcessor {

    public String generateTestData() {
        double temperature = 25 + Math.random() * 5;
        int humidity = 40 + (int) (Math.random() * 20);
        int light = (int) (Math.random() * 1024);
        return "Temperatura: " + String.format("%.2f", temperature) + "\tUmidade: " + humidity + "%\tLuminosidade: " + light;
    }

    public void processSensorData(String data) {
        String[] values = data.split("\t");
        if (values.length >= 3) {
            try {
                String temperature = values[0].split(": ")[1];
                String humidity = values[1].split(": ")[1];
                String light = values[2].split(": ")[1];

                System.out.println("------------------------------------");
                System.out.println("Temperatura: " + temperature + "°C");
                System.out.println("Umidade: " + humidity);
                System.out.println("Luminosidade: " + light + " Lumens");
                System.out.println();

                double tempValue = Double.parseDouble(temperature);
                int humidityValue = Integer.parseInt(humidity.replace("%", ""));
                int lightValue = Integer.parseInt(light);

                if (tempValue >= 27) {
                    System.out.println("ALERTA: Temperatura elevada (" + temperature + "°C). Sistema de refrigeração ativado.");
                }

                if (humidityValue <= 45) {
                    System.out.println("ALERTA: Umidade baixa (" + humidity + "). Sistema de humidificação ativado.");
                }

                if (lightValue >= 512) {
                    System.out.println("ALERTA: Luminosidade alta (" + light + "). Luzes apagadas.");
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.err.println("Erro ao processar dados do sensor: Formato inválido. Dados recebidos: " + data);
            }
        } else {
            System.err.println("Erro ao processar dados do sensor: Dados incompletos. Dados recebidos: " + data);
        }
    }
}