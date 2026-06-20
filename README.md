# gRPC Numbers Client-Server Application

Клиент-серверное приложение на **gRPC**, где сервер стримит последовательность чисел, а клиент их обрабатывает.

## Технологии
- Java 17+
- gRPC
- Protocol Buffers
- Maven

## Как запустить

### 1. Запуск сервера
1. Запускаем класс `NumbersServer` - `src/main/java/com/example/server/NumbersServer.java`

Ожидаемый вывод: === Server started on port 8080 ===

### 2. Запуск клиента
1. Запускаем класс `NumbersClient` - `src/main/java/com/example/client/NumbersClient.java`

Клиент будет каждую секунду выводить `currentValue`, а каждые 2 секунды получать новое значение от сервера.

## Логика работы

- Сервер генерирует числа от 1 до 30 с интервалом **2 секунды**.
- Клиент каждую секунду увеличивает `currentValue` по формуле: currentValue = currentValue + (последнее число от сервера) + 1
- Значение от сервера учитывается **только один раз**.

## Пример вывода клиента
    numbers Client is starting...
    currentValue:1
    new value:1
    currentValue:3
    currentValue:4
    new value:2
    currentValue:7