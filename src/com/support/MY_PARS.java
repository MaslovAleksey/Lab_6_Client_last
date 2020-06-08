package com.support;

import com.beg_data.*;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Класс преобразующий JSONObject в Dragon

/**
 * Класс преобразующий JSONObject в Dragon
 */
public class MY_PARS
{
    public static Double d;

    private boolean f = false;
    private Scanner in_conv = new Scanner (System.in);
    private String name_st;
    //------------------------------------------------------------------------------------------
    // 1.) Метод, генерирующий объект Dragon на основании данных из JSON

    /**
     * Метод, генерирующий объект Dragon на основании данных из JSON
     * @param _dop Объект, требующий приведения к Dragon
     * @throws NoSuchElementException Отслеживание команды завершения пользовательского ввода
     * @return Элемент коллекции, полученный из JSON
     */
    public Dragon convertor(JSONObject _dop) throws NoSuchElementException
    {
        Dragon dop_dr = new Dragon();
        // Извлечение значений из JSONObject
        extract_name(_dop.get("name"), dop_dr);
        extract_age(_dop.get("age"), dop_dr);
        extract_color(_dop.get("color"), dop_dr);
        extract_coordinates(_dop.get("coordinates"), dop_dr);
        extract_speaking(_dop.get("speaking"), dop_dr);
        extract_cave(_dop.get("cave"), dop_dr);
        extract_type(_dop.get("type"), dop_dr);

        return dop_dr;
    }
    //-----------------------------------------------------------------------------------------------------------------
    //2.) Метод, отвечающий за извлечение имени

    /**
     * Метод, отвечающий за извлечение имени
     * @param name_ob Имя объекта
     * @param _dop_dr Элемент коллекции, генерируемый на основании данных из JSON
     * @throws NoSuchElementException Отслеживание команды завершения пользовательского ввода
     */
    private void extract_name (Object name_ob, Dragon _dop_dr) throws NoSuchElementException
    {
        //Object name_ob = _dop.get("name");
        boolean name_b;

        while (true) {
            if (name_ob != null) {
                name_st = name_ob.toString();
                name_b = isNumber(name_st);
                if ((!name_b) && (!name_st.isEmpty())) {
                    _dop_dr.set_name(name_st);
                    break;
                } else {
                    System.out.print("Имя объекта не соответствует требованиям, задайте корректное имя (возможно только " +
                            "использование символов, длина имени > 0) - ");
                    name_ob = Empty(in_conv.nextLine().trim());
                }
            } else {
                System.out.print("Имя объекта не соответствует требованиям, задайте корректное имя (возможно только " +
                        "использование символов, длина имени > 0) - ");
                name_ob = Empty(in_conv.nextLine().trim());
            }
        }
    }
    //--------------------------------------------------------------------------------------------------
    // 3.)Метод, отвечающий за извлечение возраста

    /**
     * Метод, отвечающий за извлечение возраста
     * @param age_ob Возраст объекта
     * @param _dop_dr Элемент коллекции, генерируемый на основании данных из JSON
     * @throws NoSuchElementException Отслеживание команды завершения пользовательского ввода
     */
    private void extract_age(Object age_ob, Dragon _dop_dr) throws NoSuchElementException
    {
        String age_st;
        boolean age_b;

        while (true) {
            if (age_ob != null) {
                age_st = age_ob.toString();
                age_b = isNumber(age_st);
                if ((age_b) && (d > 0)) {
                    _dop_dr.set_age(d.longValue());
                    break;
                } else {
                    System.out.print("Возраст объекта " + name_st + "  не соответствует требованиям, " +
                            "задайте корректное значение возраста (age > 0) - ");
                    age_ob = Empty(in_conv.nextLine().trim());
                }
            } else {
                System.out.print("Возраст объекта " + name_st + "  не соответствует требованиям, " +
                        "задайте корректное значение возраста (age > 0) - ");
                age_ob = Empty(in_conv.nextLine().trim());
            }
        }
    }
    //----------------------------------------------------------------------------------------
    // 4.) Метод, отвечающий за извлечение цвета

    /**
     * Метод, отвечающий за извлечение цвета
     * @param color_ob Цвет объекта
     * @param _dop_dr Элемент коллекции, генерируемый на основании данных из JSON
     * @throws NoSuchElementException Отслеживание команды завершения пользовательского ввода
     */
    private void extract_color(Object color_ob, Dragon _dop_dr) throws NoSuchElementException
    {
        String color_st;
        boolean color_b;
        f = false;

        while (true) {
            if (color_ob != null) {
                color_st = color_ob.toString();
                color_b = isNumber(color_st);
                if (!color_b) {
                    switch (color_st) {
                        case "GREEN":
                            _dop_dr.set_Color(Color.GREEN);
                            f = true;
                            break;
                        case "YELLOW":
                            _dop_dr.set_Color(Color.YELLOW);
                            f = true;
                            break;
                        case "ORANGE":
                            _dop_dr.set_Color(Color.ORANGE);
                            f = true;
                            break;
                        case "BROWN":
                            _dop_dr.set_Color(Color.BROWN);
                            f = true;
                            break;
                        case "":
                            _dop_dr.set_Color(null);
                            f = true;
                            break;
                        default:
                            System.out.print("Цвет объекта " + name_st + " не соответствует требованиям, " +
                                    "задайте корректное значение цвета: " + Arrays.toString((Color.values())) + " - ");
                            color_ob = Empty(in_conv.nextLine().trim());
                    }
                    if (f)
                        break;
                } else {
                    System.out.print("Цвет объекта " + name_st + " не соответствует требованиям, " +
                            "задайте корректное значение цвета: " + Arrays.toString((Color.values())) + " - ");
                    color_ob = Empty(in_conv.nextLine().trim());
                }
            } else {
                _dop_dr.set_Color(null);
                break;
            }
        }
    }
    //-----------------------------------------------------------------------------------------
    // 5.) Метод, отвечающий за извлечение координат

    /**
     * Метод, отвечающий за извлечение координат
     * @param coordinates_ob Координаты объекта
     * @param _dop_dr Элемент коллекции, генерируемый на основании данных из JSON
     * @throws NoSuchElementException Отслеживание команды завершения пользовательского ввода
     */
    private void extract_coordinates (Object coordinates_ob, Dragon _dop_dr)throws NoSuchElementException
    {
        JSONObject coordinates = (JSONObject) coordinates_ob;
        Object x_ob;
        Object y_ob;
        String x_st;
        String y_st;
        boolean x_b;
        boolean y_b;
        Double x;
        int y;

        x_ob = coordinates.get("x");
        while (true) {
            if (x_ob != null) {
                x_st = x_ob.toString();
                x_b = isNumber(x_st);
                if ((x_b) && (d > -704)) {
                    x = d;
                    break;
                } else {
                    System.out.print("Значение координаты Х у объекта " + name_st + " не соответствует требованиям," +
                            " задайте корректное значение координаты Х (X > -704) - ");
                    x_ob = Empty(in_conv.nextLine().trim());
                }
            } else {
                System.out.print("Значение координаты Х у объекта " + name_st + " не соответствует требованиям," +
                        " задайте корректное значение координаты Х (X > -704) - ");
                x_ob = Empty(in_conv.nextLine().trim());
            }
        }

        y_ob = coordinates.get("y");
        while (true) {
            if (y_ob != null) {
                y_st = y_ob.toString();
                y_b = isNumber(y_st);
                if ((y_b) && (d <= 28)) {
                    y = d.intValue();
                    break;
                } else {
                    System.out.print("Значение координаты Y у объекта " + name_st + " не соответствует требованиям," +
                            " задайте корректное значение координаты (Y <= 28) - ");
                    y_ob = Empty(in_conv.nextLine().trim());
                }
            } else {
                System.out.print("Значение координаты Y у объекта " + name_st + " не соответствует требованиям," +
                        " задайте корректное значение координаты (Y <= 28) - ");
                y_ob = Empty(in_conv.nextLine().trim());
            }
        }
        _dop_dr.set_Coordinates(x, y);
    }
    //------------------------------------------------------------------------------------------------------------
    // 6.) Метод, отвечающий за извлечение способности говорить

    /**
     * Метод, отвечающий за извлечение способности говорить
     * @param speaking_ob Способность объекта говорить
     * @param _dop_dr Элемент коллекции, генерируемый на основании данных из JSON
     * @throws NoSuchElementException Отслеживание команды завершения пользовательского ввода
     */
    private void extract_speaking (Object speaking_ob, Dragon _dop_dr) throws NoSuchElementException
    {
        String speaking_st;
        boolean speaking_b;

        while (true) {
            if (speaking_ob != null) {
                speaking_st = speaking_ob.toString();
                speaking_b = isNumber(speaking_st);
                if ((!speaking_b) && ((speaking_st.equals("true")) || (speaking_st.equals("false")))) {
                    _dop_dr.set_speaking(Boolean.parseBoolean(speaking_st));
                    break;
                } else {
                    System.out.print("Способность разговаривать у объекта " + name_st + "  не соответствует требованиям, " +
                            "задайте корректное значение поля (true/false) - ");
                    speaking_ob = Empty(in_conv.nextLine().trim());
                }
            } else {
                System.out.print("Способность разговаривать у объекта " + name_st + "  не соответствует требованиям, " +
                        "задайте корректное значение поля (true/false) - ");
                speaking_ob = Empty(in_conv.nextLine().trim());
            }
        }
    }
    //-----------------------------------------------------------------------------------------------------------
    // 7.) Метод, отвечающий за извлечение данных о пещере

    /**
     * Метод, отвечающий за извлечение данных о пещере
     * @param cave_ob Инфлормациия о пещере
     * @param _dop_dr Элемент коллекции, генерируемый на основании данных из JSON
     * @throws NoSuchElementException Отслеживание команды завершения пользовательского ввода
     */
    private void extract_cave (Object cave_ob, Dragon _dop_dr) throws NoSuchElementException
    {
        JSONObject cave = (JSONObject) cave_ob;
        Object depth_ob;
        Object numberOfTreasures_ob;
        String depth_st;
        String numberOfTreasures_st;
        boolean depth_b;
        boolean numberOfTreasures_b;
        Integer depth;
        Float numberOfTreasures;

        depth_ob = cave.get("depth");
        while (true) {
            if ((depth_ob != null) && (Empty(depth_ob.toString()) != null)) {
                depth_st = depth_ob.toString();
                depth_b = isNumber(depth_st);
                if (depth_b) {
                    depth = d.intValue();
                    break;
                } else {
                    System.out.print("Значение поля depth у объекта " + name_st + " не соответствует требованиям," +
                            " задайте корректное значение поля depth (depth must be number) - ");
                    depth_ob = Empty(in_conv.nextLine().trim());
                }
            } else {
                depth = null;
                break;
            }

        }

        numberOfTreasures_ob = cave.get("numberOfTreasures");
        while (true) {
            if (numberOfTreasures_ob != null) {
                numberOfTreasures_st = numberOfTreasures_ob.toString();
                numberOfTreasures_b = isNumber(numberOfTreasures_st);
                if ((d > 0) && (numberOfTreasures_b)) {
                    numberOfTreasures = d.floatValue();
                    break;
                } else {
                    System.out.print("Значение поля numberOfTreasures у объекта " + name_st + " не соответствует требованиям," +
                            " задайте корректное значение поля numberOfTreasures " +
                            "(numberOfTreasures > 0) - ");
                    numberOfTreasures_ob = Empty(in_conv.nextLine().trim());
                }
            } else {
                System.out.print("Значение поля numberOfTreasures у объекта " + name_st + " не соответствует требованиям," +
                        " задайте корректное значение поля numberOfTreasures " +
                        "(numberOfTreasures > 0) - ");
                numberOfTreasures_ob = Empty(in_conv.nextLine().trim());
            }
        }
        _dop_dr.set_DragonCave(depth, numberOfTreasures);
    }
    //------------------------------------------------------------------------------------------------------------
    // 8.) Метод, отвечающий за извлечение типа

    /**
     * Метод, отвечающий за извлечение типа
     * @param type_ob Тип объекта
     * @param _dop_dr Элемент коллекции, генерируемый на основании данных из JSON
     * @throws NoSuchElementException Отслеживание команды завершения пользовательского ввода
     */
    private void extract_type (Object type_ob, Dragon _dop_dr) throws NoSuchElementException
    {
        String type_st;
        boolean type_b;
        f = false;

        while (true) {
            if (type_ob != null) {
                type_st = type_ob.toString();
                type_b = isNumber(type_st);
                if (!type_b) {
                    switch (type_st) {
                        case "WATER":
                            _dop_dr.set_DragonType(DragonType.WATER);
                            f = true;
                            break;
                        case "AIR":
                            _dop_dr.set_DragonType(DragonType.AIR);
                            f = true;
                            break;
                        case "FIRE":
                            _dop_dr.set_DragonType(DragonType.FIRE);
                            f = true;
                            break;
                        case "":
                            _dop_dr.set_DragonType(null);
                            f = true;
                            break;
                        default:
                            System.out.print("Тип объекта " + name_st + " не соответствует требованиям, " +
                                    "задайте корректное значение цвета: " + Arrays.toString((DragonType.values())) + " - ");
                            type_ob = Empty(in_conv.nextLine().trim());
                    }
                    if (f)
                        break;
                } else {
                    System.out.print("Тип объекта " + name_st + " не соответствует требованиям, " +
                            "задайте корректное значение цвета: " + Arrays.toString((DragonType.values())) + " - ");
                    type_ob = Empty(in_conv.nextLine().trim());
                }
            } else {
                type_ob = null;
                break;
            }
        }
    }
    //-----------------------------------------------------------------------------------------------------------------
    // 9.) Метод, проверяющий строку на число

    /**
     * Метод, проверяющий строку на численное значение
     * @param st Строка для проверки
     * @return Успешно ли выполнена провека на (true/false)
     */
    public static boolean isNumber(String st)
    {
        try {
            d = Double.parseDouble(st);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    //------------------------------------------------------------------------------------------------------------------
    // 10.) Метод, проверяющий строку на наличие символов

    /**
     * Метод, проверяющий строку на наличие символов
     * @param st Строка для проверки
     * @return Успешно ли выполнена провека на (true/false)
     */
    private String Empty(String st)
    {
        if (st.isEmpty())
            return null;
        else return st;
    }
}
