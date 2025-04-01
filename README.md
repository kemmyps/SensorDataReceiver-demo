# 📡 Sensor Data Receiver

Este projeto é um sistema de monitoramento de sensores que se comunica com uma porta serial para obter leituras de **temperatura, umidade e luminosidade**. Ele exibe as leituras de forma organizada no terminal, fornece alertas quando os valores estão fora dos padrões e simula leituras no **modo de teste**.

---

## ✨ Funcionalidades

✅ Leitura de dados de sensores por meio de uma porta serial.\
✅ Processamento de dados e exibição estruturada no terminal.\
✅ Alertas para temperatura alta, umidade baixa e luz intensa.\
✅ Registro de histórico de leituras em `sensor_log.txt`.\
✅ **Modo de Teste** para simular dados sem hardware físico.\
✅ Interface interativa no terminal, permitindo múltiplas leituras.

---

## 🛠 Estrutura do Projeto

📂 **`Main.java`** → Ponto de entrada da aplicação.\
📂 **`SensorManager.java`** → Gerencia a leitura de sensores e interação com o usuário.\
📂 **`SensorDataReceiver.java`** → Responsável por coletar dados do sensor.\
📂 **`SensorData.java`** → Modela os dados do sensor e gera alertas com base nos valores.\
📂 **`SerialCommunication.java`** → Realiza a comunicação serial com os sensores físicos.\
📂 **`SensorDataProcessor.java`** → Processa os dados e gera leituras no modo de teste.\
📂 **`sensor_log.txt`** → Registro histórico das leituras dos sensores.

---

## 🔧 Pré-requisitos

📌 **Java Development Kit (JDK)** instalado.\
📌 Biblioteca **jSerialComm** adicionada ao projeto.\
📌 IDE da sua preferência (**IntelliJ**, **Eclipse**, **VS Code** etc.).

---

## 🚀 Como Usar

1️⃣ Clone este repositório:

```sh
git clone https://github.com/seu-repositorio.git
```

2️⃣ Abra o projeto na sua **IDE Java** preferida.\
3️⃣ **Adicione a biblioteca jSerialComm** ao projeto.\
4️⃣ Para usar o **modo de teste**, defina `testMode = true` em `SensorDataReceiver.java`.\
5️⃣ Execute a classe `Main` para iniciar o monitoramento.

---

## 🎭 Modo de Teste

O **modo de teste** gera dados simulados para testar o sistema sem necessidade de hardware físico. Para ativá-lo, modifique a variável `testMode`:

```java
private boolean testMode = true; // true para simulação, false para hardware físico
```

---

## 📊 UML - Diagrama de Classes

```mermaid
classDiagram
    class Main {
        +main(args : String[]) : void
    }
    class SensorManager {
        - receiver : SensorDataReceiver
        - scanner : Scanner
        +SensorManager(portName : String)
        +start() : void
    }
    class SensorDataReceiver {
        -serialCommunication : SerialCommunication
        -dataProcessor : SensorDataProcessor
        -testMode : boolean
        +SensorDataReceiver(portName : String)
        +readSensorData() : String
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
    class SensorData {
        - temperature : double
        - humidity : int
        - light : int
        +SensorData(temp : double, hum : int, light : int)
        +formatData() : String
        +getSystemActions() : String
    }
    SensorManager "1" --> "1" SensorDataReceiver : cria
    SensorDataReceiver "1" --* "1" SerialCommunication : tem um
    SensorDataReceiver "1" --* "1" SensorDataProcessor : tem um
    SerialCommunication "1" --* "1" SerialPort : usa
    SensorManager "1" --* "1" SensorData : processa
```

---

## 🛠 Protótipo Arduino

Este projeto conta com um **protótipo no Arduino** para simular sensores de **temperatura, umidade e iluminação**.

🔗 [**Acesse a simulação no Tinkercad**](https://www.tinkercad.com/things/6uVQnDJF6tp/editel?sharecode=StbhpyHQ2qmJCWu9OA0liCQtoXbzjIlvm1-s5xy-HCU)

### 📸 Esquema do Circuito

![Esquema Arduino](images/arduino_circuit.png)
### 📜 Código do Arduino

```cpp
const int tempSensorPin = A3;
const int humidityPin = A1;
const int lightSensorPin = A2;
const int lightPin = 7;

void setup() {
  Serial.begin(9600);
  pinMode(humidityPin, INPUT);
  pinMode(lightSensorPin, INPUT);
  pinMode(lightPin, OUTPUT);
}

void loop() {
  int RawValue = analogRead(tempSensorPin);
  double Voltage = (RawValue / 1023.0) * 5000;
  double tempC = (Voltage - 500) * 0.1;

  int humiditysensorOutput = analogRead(humidityPin);
  int humidityPercentage = map(humiditysensorOutput, 0, 1023, 10, 70);

  int lightSensorOutput = analogRead(lightSensorPin);

  Serial.print("Temperatura em C: "); Serial.print(tempC, 1);
  Serial.print("\tUmidade: "); Serial.print(humidityPercentage);
  Serial.print("%\tLuminosidade: "); Serial.println(lightSensorOutput);

  if (tempC >= 30) Serial.println("Temperatura alta, resfriamento ativado.");
  else if (tempC < 20) Serial.println("Temperatura baixa, aquecimento ativado.");

  if (humidityPercentage < 40) Serial.println("Umidade baixa, umidificação ativada.");
  else if (humidityPercentage > 60) Serial.println("Umidade alta, umidificação desativada.");

  if (lightSensorOutput <= 512) digitalWrite(lightPin, LOW);
  else digitalWrite(lightPin, HIGH);

  Serial.println();
  delay(5000);
}
```

---

## 📱 Protótipo Mobile

Este projeto também inclui um **protótipo de aplicativo mobile** desenvolvido no **Figma** para monitoramento e controle dos sensores.

🔗 [**Acesse o protótipo no Figma**](https://www.figma.com/design/FcUjUmA3Yr3K1A1IGixOBM/Smart-Home---prototipo-PI?node-id=0-1&t=7nGNZpZq1SkoGvcp-1)

### 📸 Captura de Tela do Protótipo


![Protótipo Mobile](images/mobile_prototype.png)

---

## 📌 Observações

🔹 Se estiver rodando com sensores reais, certifique-se de definir `testMode = false`.\
🔹 O sistema exibe **alertas visuais** no terminal para valores fora dos padrões.\
🔹 O histórico das leituras é salvo automaticamente em `sensor_log.txt`.

💡 **Sugestões de melhoria são sempre bem-vindas!** 🚀

