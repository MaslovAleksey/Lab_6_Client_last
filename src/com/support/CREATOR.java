package com.support;

import com.beg_data.Color;
import com.beg_data.DragonType;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, генерирующий объект класса Json на основании введённых данных
 */
public class CREATOR
{
    /**
     * Метод, генерирующий объект класса Json на основании введённых данных
     * @throws NoSuchElementException Отслеживание команды завершения пользовательского ввода
     * @return Сгенерированный объект класса Json
     */
    public JSONObject create_json() throws NoSuchElementException
    {
        JSONObject dr_json = new JSONObject();
        JSONObject coordinates_json = new JSONObject();
        JSONObject cave_json = new JSONObject();
        Scanner in_cr = new Scanner (System.in);

        System.out.print("Введите имя объекта (возможно только использование символов, длина имени > 0) - ");
        dr_json.put("name",in_cr.nextLine().trim());

        System.out.print("Введите  координату X объекта (X > -704) - ");
        coordinates_json.put("x",in_cr.nextLine().trim());
        System.out.print("Введите  координату Y объекта (Y <= 28) - ");
        coordinates_json.put("y",in_cr.nextLine().trim());
        dr_json.put("coordinates",coordinates_json);

        System.out.print("Введите возраст объекта (age > 0) - ");
        dr_json.put("age",in_cr.nextLine().trim());

        System.out.print("Укажите, имеет ли объект способность говорить (true or false) - ");
        dr_json.put("speaking",in_cr.nextLine().trim());

        System.out.print("Введите цвет объекта: " + Arrays.toString((Color.values())) + " - ");
        dr_json.put("color",in_cr.nextLine().trim());

        System.out.print("Введите тип объекта: " + Arrays.toString((DragonType.values())) + " - ");
        dr_json.put("type",in_cr.nextLine().trim());

        System.out.print("Введите значение поля depth объекта (depth must be number) - ");
        cave_json.put("depth",in_cr.nextLine().trim());
        System.out.print("Введите значение поля numberOfTreasures объекта (numberOfTreasures > 0) - ");
        cave_json.put("numberOfTreasures",in_cr.nextLine().trim());
        dr_json.put("cave",cave_json);

        System.out.println();

        return dr_json;
    }
}
