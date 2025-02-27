public class Main {
    public static void main(String[] args) {
        SensorDataReceiver receiver = new SensorDataReceiver("COM3");
        receiver.start();
        receiver.close();
    }
}