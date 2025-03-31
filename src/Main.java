public class Main {
    public static void main(String[] args) {
        SensorManager manager = new SensorManager("COM3"); // Substitua pela porta correta
        manager.start(); // ✅ Agora a execução começa no SensorManager
    }
}



