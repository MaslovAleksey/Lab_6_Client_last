package com.support;

import com.beg_data.*;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Класс, реализующий работу клиентского модуля
 */
public class USER_DRAG_CLIENT
{
    private LinkedList<String> list_command = new LinkedList<>();

    /**
     * Класс, позволяющий создать Json объект на основании введённых данных
     */
    private CREATOR creator = new CREATOR(); // нет собственных переменных

    /**
     * Класс, преобразующий объект класс Json в объект класса Dragon
     */
    private MY_PARS my_pars = new MY_PARS();

    /**
     * Класс, осуществляющий серриализацию объекта и взаимодействие с сервером
     */
    private CONNECT_TO_SERVER connection = new CONNECT_TO_SERVER();

    private JSONObject json_obj;
    private Dragon drag_obj;

    private String host;
    private Integer port = 8734;
    /**
     * Переменная, содержащая в себе целочисленной значение из строки в случае успешного преобразования
     */
    private Integer k;
    //-----------------------------------------------------------------------------------------------------
    //1.) Метод, реализующий работу клиентского приложения
    /**
     * Метод, реализующий работу клиентского приложения
     */
    public void process ()
    {
        Scanner in = new Scanner (System.in);
        try {
            receiveAddress(in);
            while (true) {
                System.out.println("\nВведите очередную команду из списка:\n" +
                      "[help, info, show, insert_key {element}, update_id {element}, remove_key {key}, clear,\n" +
                      "execute_script {file_name}, exit, history, replace_if_lowe_key {element}, remove_greater_key {key},\n" +
                      "average_of_age, count_less_then_cave {cave}, print_ascending, <ctrl> + <d>]\n");
              perform(in);
             }
        } catch (NoSuchElementException e)
        {
            System.out.println("Поступила команда завершения пользовательского ввода, для продолжения работы перезапустите приложение");
            System.exit(0);
        }
        catch (IOException e)
        {
            System.out.println("Потеряна связь с сервером, для продолжения работы перезапустите приложение");
            System.exit(0);
        }
    }
    //---------------------------------------------------------------------------------------------------------------------------------
    //2.) Метод, осуществялющий передачу команды на выполнение
    /**
     * Метод, осуществялющий передачу команды на выполнение
     * @throws NoSuchElementException Отслеживание команды завершения пользовательского ввода
     * @param scan_ Содержит команды для выполнения
     */
    private void perform (Scanner scan_) throws NoSuchElementException, IOException
    {
        boolean com = true; // Проверка соотвествия команды
        boolean needToExit = false;
        String command = scan_.next();
        OBJ_TO_SERV ob_to_serv = new OBJ_TO_SERV(command);
        switch (command) {
            case "help":
            case "info":
            case "show":
            case "clear":
            case "history":
            case "average_of_age":
            case "print_ascending":
                break;

            case "update_id":
                if ((isInteger(scan_.nextLine().trim())) && (k > 0)) {
                    ob_to_serv.set_value(k);
                    json_obj = creator.create_json();
                    drag_obj = my_pars.convertor(json_obj);
                    ob_to_serv.set_Dragon(drag_obj);
                }
                else {
                    System.out.println("Некорректное значение id ((id must be Integer) and (id > 0))");
                    com = false;
                }
                break;

            case "insert_key":
                if (isInteger(scan_.nextLine().trim())) {
                    ob_to_serv.set_value(k);
                    json_obj = creator.create_json();
                    drag_obj = my_pars.convertor(json_obj);
                    ob_to_serv.set_Dragon(drag_obj);
                }
                else {
                    System.out.println("Некорректное значение ключа (key must be Integer)");
                    com = false;
                }
                break;

            case "remove_key":
                if (isInteger(scan_.nextLine().trim())) {
                    ob_to_serv.set_value(k);
                }
                else {
                    System.out.println("Некорректное значение ключа (key must be Integer)");
                    com = false;
                }
                break;

            case "replace_if_lowe_key":
                if (isInteger(scan_.nextLine().trim())) {
                    ob_to_serv.set_value(k);
                    json_obj = creator.create_json();
                    drag_obj = my_pars.convertor(json_obj);
                    ob_to_serv.set_Dragon(drag_obj);
        }
                else {
                    System.out.println("Некорректное значение ключа (key must be Integer)");
                    com = false;
                }
                break;

            case "remove_greater_key":
                if (isInteger(scan_.nextLine().trim())) {
                    ob_to_serv.set_value(k);
                }
                else {
                    System.out.println("Некорректное значение ключа (key must be Integer)");
                    com = false;
                }
                break;

            case "count_less_then_cave":
                if (MY_PARS.isNumber(scan_.nextLine().trim())) {
                    ob_to_serv.set_cave(MY_PARS.d);
                }
                else {
                    System.out.println("Некорректное значение поля cave (cave must be Number)");
                    com = false;
                }
                break;

            case "execute_script":
                try{
                    FileReader reader = new FileReader(new File(scan_.nextLine().trim()));
                    Scanner scan_script = new Scanner(reader);
                    while (scan_script.hasNext() ) {
                        perform(scan_script);
                    }
                } catch (FileNotFoundException ex)
                {
                    System.out.println("Ошибка считывания файла");
                }
                com = false; // команда не отправлется на сервер
                break;

            case "exit":
                needToExit = true;
                break;

            default:
                System.out.println("Некорректная команда");
                com = false;
        }
        if (com)
            connection.connect(ob_to_serv, host, port, needToExit);
    }
    //------------------------------------------------------------------------------------------------------------------
    //3.) Метод, считывающий значение хоста и порта
    /**
     * Метод, считывающий значение хоста и порта
     */
    private void receiveAddress(Scanner in) throws UnknownHostException
    {

        String host_;
        String port_;
        System.out.println("\nВведите данные для соединения с сервером\n" +
                "(для использования данных по умолчанию оставьте поля пустыми");

        System.out.print("\nХост (доменное имя или IP) - ");
        host_ = in.nextLine().trim();
        if (host_.length() == 0) {
            host = InetAddress.getLocalHost().getHostName();
            System.out.println("Доменное имя - " + host);
            System.out.println("IP адрес - " + InetAddress.getLocalHost().getHostAddress());
        }
        else host = InetAddress.getLocalHost().getHostName();

        while (true) {
            System.out.print("\nПорт (целочисленное значение) - ");
            port_ = in.nextLine().trim();
            if (port_.length() == 0) {
                System.out.println("Порт - " + port);
                break;
            }
            else {
                if(isInteger(port_)) {
                    port = k;
                    break;
                }
                else System.out.println("Введите корректное значение порта (целочисленное значение)");
            }
        }

    }
    //------------------------------------------------------------------------------------------------------------------
    //4.) Метод, проверяющий строку на целочисленной значение
    /**
     * Метод, проверяющий строку на целочисленной значение
     * @param st Строка для проверки
     * @return Успешно ли выполнена провека (true/false)
     */
    private boolean isInteger(String st)
    {
        try {
            k = Integer.parseInt(st);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}