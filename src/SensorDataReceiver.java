public class SensorDataReceiver {
    private boolean testMode = true; //true para modo teste ou false hardware físico
    private SerialCommunication serialCommunication;
    private SensorDataProcessor dataProcessor;

    public SensorDataReceiver(String portName) {
        this.serialCommunication = new SerialCommunication(portName);
        this.dataProcessor = new SensorDataProcessor();
    }

    public void start() {
        if (!testMode && !serialCommunication.initialize()) {
            return;
        }

        System.out.println("Iniciando a leitura de dados da porta serial...");
        System.out.println();

        while (true) {
            String data = testMode ? dataProcessor.generateTestData() : serialCommunication.readData();
            if (data != null) {
                dataProcessor.processSensorData(data);
                try {
                    Thread.sleep(testMode ? 5000 : 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void close() {
        if (!testMode) {
            serialCommunication.close();
        }
    }
}