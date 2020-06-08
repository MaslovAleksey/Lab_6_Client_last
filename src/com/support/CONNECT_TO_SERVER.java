package com.support;

import com.beg_data.Dragon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.response.AVG_AGE_RESPONSE;
import com.response.COUNT_RESPONSE;
import com.response.INFO_RESPONSE;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * Класс, осуществляющий серриализацию объекта и взаимодействие с сервером
 */
public class CONNECT_TO_SERVER {
    /**
     * Список элементов коллекции, полученный от сервера
     */
    private LinkedList<Dragon> dragons = new LinkedList<Dragon>();

    /**
     * Метод, осуществляющий серриализацию объекта и взаимодействие с сервером
     * @param inputData Объект для передачи на сервер
     * @param host Хост
     * @param port Порт
     */

    public void connect(OBJ_TO_SERV inputData, String host, Integer port, boolean needExit) throws IOException {
        SocketChannel client = SocketChannel.open(); // создание канала для связи с сервером
        client.connect(new InetSocketAddress(host, port)); // установление соединения
        client.configureBlocking(false); // Неблокирующий режим


        // Серриализация объекта
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {

            objectOutputStream.writeObject(inputData);
            objectOutputStream.flush();

            ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray()); // Обёртывание массива байтов в буфер
            client.write(buffer); // Запись данных из буфера в канал


            ByteBuffer buf = ByteBuffer.allocate(10240);
            client.read(buf);

            if (needExit) {
                System.out.println("Завершение работы клиентского приложения");
                System.exit(0);
            }


            System.out.println("\nОжидание ответа от сервера");
            while (client.read(buf) == 0) {}

            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buf.array()));
            Serializable result = (Serializable) ois.readObject();
            processResult(inputData, result);
        } catch (IOException | ClassNotFoundException ignore) {
            System.out.println(ignore);
        }

        client.close(); // Отключение отсервера
    }

    /**
     * Метод, осуществляющий обработку результатов, полученных от сервера
     * @param request Запрос, отправленный на сервер
     * @param result Ответ сервера
     */
    private void processResult(OBJ_TO_SERV request, Serializable result) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        switch (request.get_command()) {
            case "info":
                INFO_RESPONSE r = (INFO_RESPONSE) result;
                System.out.printf("Информация о коллекции:\nТип коллекции - %s\nКоличество элементов - %s\n",
                        r.getCollectionType(), r.getSize());
                if (r.getSize() != 0)
                    System.out.printf("Тип элементов - %s\nДата создания - %s\n", r.getElementType(), r.getCreationDate());
                break;

            case "show":
                dragons = (LinkedList<Dragon>) result;
                System.out.println("Элементы коллекции в строковом представлении:");
                for (Dragon dr : dragons)
                    System.out.println(dr.toString());
                break;

            case "help":
                System.out.println("Список доступных команд:");
                LinkedList<String> help = (LinkedList<String>) result;
                for (String command:help)
                    System.out.println(command);
                break;

            case "clear":
                System.out.println("Коллекция очищена");
                break;

            case "history":
                LinkedList<String> history = (LinkedList<String>) result;
                System.out.println("Последние команды, вызванные пользователем(не более 6):");
                System.out.println(history.toString());
                break;

            case "print_ascending":
                System.out.println("Элементы коллекции в порядке возрастания (на основании id)");
                dragons = (LinkedList<Dragon>) result;
                for (Dragon dr : dragons)
                    System.out.println(dr.toString());
                break;

            case "average_of_age":
                AVG_AGE_RESPONSE avg = (AVG_AGE_RESPONSE) result;
                System.out.println("Средний возраст элементов коллекции: " + avg.getAverageAge());
                break;

            case "remove_key":
                System.out.println("Коллекция после попытки удаления элемента (key = " + request.get_value() + "):");
                dragons = (LinkedList<Dragon>) result;
                for (Dragon dr : dragons)
                    System.out.println(dr.toString());
                break;

            case "remove_greater_key":
                System.out.println("Коллекция после удаления элементов, ключ которых превышает заданный (key = " + request.get_value() + "):");
                dragons = (LinkedList<Dragon>) result;
                for (Dragon dr : dragons)
                    System.out.println(dr.toString());
                break;

            case "replace_if_lowe_key":
                System.out.println("Коллекция после попытки замены элемента (key = " + request.get_value() + "):");
                dragons = (LinkedList<Dragon>) result;
                for (Dragon dr : dragons)
                    System.out.println(dr.toString());
                break;

            case "update_id":
                System.out.println("Коллекция после попытки обновления элемента (id = " + request.get_value() + "):");
                dragons = (LinkedList<Dragon>) result;
                for (Dragon dr : dragons)
                    System.out.println(dr.toString());
                break;

            case "insert_key":
                dragons = (LinkedList<Dragon>) result;
                System.out.println("Дракон с заданным ключом - {" + request.get_value() + "} добавлен в коллекцию");
                System.out.println("Элементы коллекции в строковом представлении:");
                for (Dragon dr : dragons)
                    System.out.println(dr.toString());
                break;

            case "count_less_then_cave":
                COUNT_RESPONSE count = (COUNT_RESPONSE) result;
                System.out.println(count.getCount() + " caves found");
                break;

            default:
                System.out.println("Response not supported");
        }
    }
}


