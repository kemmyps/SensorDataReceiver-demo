# Sensor Data Receiver

Este projeto é um receptor de dados de sensores que se comunica com uma porta serial para ler dados de temperatura, umidade e luminosidade. Ele também inclui um modo de teste para simular dados de sensores sem a necessidade de hardware físico.

## Funcionalidades

* Leitura de dados de sensores por meio de uma porta serial.
* Processamento de dados de temperatura, humidade e luminosidade.
* Exibição de alertas quando os valores dos sensores ultrapassam os limites definidos.
* Modo de teste para simular dados de sensores sem ‘hardware’ físico.

## Estrutura do Projeto

* `Main.java`: Ponto de entrada da aplicação.
* `SensorDataReceiver.java`: Classe principal que gerencia a comunicação e o processamento dos dados.
* `SerialCommunication.java`: Classe que lida com a comunicação serial.
* `SensorDataProcessor.java`: Classe que processa os dados dos sensores e gera dados de teste.


## Pré-requisitos

* Java Development Kit (JDK) instalado.
* Biblioteca jSerialComm adicionada ao projeto.
* IDE da sua preferência (eu utilizei IntelliJ).

## Como Usar

1.  Clone este repositório.
2.  Abra o projeto na sua IDE Java preferida.
3.  Adicione a biblioteca jSerialComm ao seu projeto.
4.  Para usar o modo de teste, defina a variável `testMode` como `true` na classe `SensorDataReceiver`.
5.  Execute a classe `Main`.

## Modo de Teste

O modo de teste simula a leitura de dados de sensores sem a necessidade de hardware físico. Ele gera dados fictícios de temperatura, umidade e luminosidade e os processa como se fosse de uma porta serial.

Para ativar o modo de teste, defina a variável `testMode` como `true` na classe `SensorDataReceiver`:

```java
private boolean testMode = true;
```

    classDiagram
        class Main {
        +main(args : String[]) : void
        }

        class SensorDataReceiver {
            -serialCommunication : SerialCommunication
            -dataProcessor : SensorDataProcessor
            -testMode : boolean
            +SensorDataReceiver(portName : String)
            +start() : void
            +close() : void
        }
    
        class SerialCommunication {
            -serialPort : SerialPort
            +SerialCommunication(portName : String)
            +initialize() : boolean
            +readData() : String
            +close() : void
        }
    
        class SensorDataProcessor {
            +generateTestData() : String
            +processSensorData(data : String) : void
        }
    
        class SerialPort {
            <<external>>
            +openPort() : boolean
            +setComPortParameters(int baudRate, int dataBits, int stopBits, int parity) : boolean
            +getInputStream() : InputStream
            +closePort() : boolean
            +getSystemPortName() : String
            +isOpen() : boolean
            +getCommPorts() : SerialPort[]
        }
    
        class InputStream {
            <<external>>
        }
    
        class Scanner {
            <<external>>
            +Scanner(InputStream source)
            +hasNextLine() : boolean
            +nextLine() : String
            +close() : void
        }

    Main "1" --> "1" SensorDataReceiver : cria
    SensorDataReceiver "1" --* "1" SerialCommunication : tem um
    SensorDataReceiver "1" --* "1" SensorDataProcessor : tem um
    SerialCommunication "1" --* "1" SerialPort : usa
    SerialCommunication "1" --* "1" InputStream : usa
    SerialCommunication "1" --* "1" Scanner : usa