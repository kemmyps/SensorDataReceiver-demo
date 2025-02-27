import com.fazecast.jSerialComm.SerialPort;
import java.io.InputStream;
import java.util.Scanner;

public class SerialCommunication {
    private SerialPort serialPort;

    public SerialCommunication(String portName) {
        SerialPort[] availablePorts = SerialPort.getCommPorts();
        for (SerialPort port : availablePorts) {
            if (port.getSystemPortName().equals(portName)) {
                this.serialPort = port;
                break;
            }
        }
        if (this.serialPort == null) {
            System.err.println("Porta serial '" + portName + "' não encontrada.");
        }
    }

    public boolean initialize() {
        if (serialPort != null) {
            if (serialPort.openPort()) {
                serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
                System.out.println("Porta serial '" + serialPort.getSystemPortName() + "' aberta com sucesso.");
                return true;
            } else {
                System.err.println("Erro ao abrir a porta serial '" + serialPort.getSystemPortName() + "'.");
                return false;
            }
        }
        return false;
    }

    public String readData() {
        if (serialPort != null && serialPort.isOpen()) {
            InputStream inputStream = serialPort.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            }
            scanner.close();
        }
        return null;
    }

    public void close() {
        if (serialPort != null && serialPort.isOpen()) {
            if (serialPort.closePort()) {
                System.out.println("Porta serial '" + serialPort.getSystemPortName() + "' fechada.");
            } else {
                System.err.println("Erro ao fechar a porta serial '" + serialPort.getSystemPortName() + "'.");
            }
        }
    }
}