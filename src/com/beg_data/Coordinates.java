package com.beg_data;

import java.io.Serializable;

/**
 * Класс, отвечающий за размещение объекта класса Dragon в пространстве
 */
public class Coordinates implements Serializable
{
    // 1) Начальные поля
    private Double x; //Значение поля должно быть больше -704, Поле не может быть null
    private int y; //Максимальное значение поля: 28

    // 2) Установка значений в полях
    public void set_X(Double x) { this.x = x; }
    public void set_Y(int y) { this.y = y; }

    // 3) Доступ к значениям полей
    public Double get_X() {return x;}
    public int get_Y() {return y;}

}