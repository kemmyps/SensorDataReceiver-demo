public class SensorDataReceiver {
    private boolean testMode = true;
    private SerialCommunication serialCommunication;
    private SensorDataProcessor dataProcessor;

    public SensorDataReceiver(String portName) {
        this.serialCommunication = new SerialCommunication(portName);
        this.dataProcessor = new SensorDataProcessor();
    }

    public String readSensorData() {
        return testMode ? dataProcessor.generateTestData() : serialCommunication.readData();
    }

    public void close() {
        if (!testMode) {
            serialCommunication.close();
        }
    }
}
