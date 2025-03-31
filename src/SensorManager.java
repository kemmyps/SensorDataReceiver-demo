import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Classe SensorManager
 * Responsável por gerenciar a leitura dos sensores e interagir com o usuário pelo terminal.
 * - Aguarda comandos do usuário para iniciar uma nova leitura ou encerrar o programa.
 * - Processa os dados brutos recebidos e os transforma em um objeto SensorData.
 * - Registra as leituras em um arquivo de log.
 */
public class SensorManager {
    private SensorDataReceiver receiver; // Instância para receber dados do sensor
    private Scanner scanner; // Scanner para entrada de dados do usuário

    /**
     * Construtor da classe SensorManager.
     * Inicializa o receptor de dados do sensor e o scanner para entrada de comandos.
     *
     * @param portName Nome da porta serial utilizada para comunicação.
     */
    public SensorManager(String portName) {
        receiver = new SensorDataReceiver(portName);
        scanner = new Scanner(System.in);
    }

    /**
     * Inicia o sistema de monitoramento.
     * - Exibe mensagens no console para guiar o usuário.
     * - Aguarda comandos do usuário para solicitar novas leituras ou encerrar.
     */
    public void start() {
        System.out.println("Bem-vindo ao sistema de monitoramento de sensores!");
        System.out.println("Pressione ENTER para nova leitura ou 'q' para sair.");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Encerrando o programa...");
                break;
            }

            // Lê os dados do sensor
            String rawData = receiver.readSensorData();
            if (rawData != null) {
                SensorData data = processRawData(rawData); // Processa os dados recebidos
                System.out.println(data.formatData()); // Exibe os dados formatados
                logData(data); // Registra os dados no log
            }
        }
        receiver.close(); // Fecha a conexão ao encerrar o programa
    }

    /**
     * Processa os dados brutos recebidos do sensor e os converte para um objeto SensorData.
     *
     * @param rawData String contendo os dados do sensor no formato "Temperatura: X, Umidade: Y, Luminosidade: Z".
     * @return Objeto SensorData contendo os valores extraídos.
     */
    private SensorData processRawData(String rawData) {
        String[] values = rawData.split("\t"); // Divide os valores recebidos
        double temp = Double.parseDouble(values[0].split(": ")[1]);
        int humidity = Integer.parseInt(values[1].split(": ")[1].replace("%", ""));
        int light = Integer.parseInt(values[2].split(": ")[1]);
        return new SensorData(temp, humidity, light);
    }

    /**
     * Registra os dados do sensor em um arquivo de log.
     *
     * @param data Objeto SensorData contendo as informações coletadas.
     */
    private void logData(SensorData data) {
        try (FileWriter writer = new FileWriter("sensor_log.txt", true)) {
            writer.write(String.format("Temperatura: %.2f°C, Umidade: %d%%, Luminosidade: %d Lux\n",
                    data.getTemperature(), data.getHumidity(), data.getLight()));
        } catch (IOException e) {
            System.err.println("Erro ao registrar log: " + e.getMessage());
        }
    }
}
