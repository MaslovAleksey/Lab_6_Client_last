package com.beg_data;

import java.io.Serializable;

/**
 * Класс, демонтсрирующий характеристики поля DragonCave  объекта класса Dragon
 */
public class DragonCave implements Serializable {
    // 1) Начальные поля
    private Integer depth; //Поле может быть null
    private Float numberOfTreasures; //Поле не может быть null, Значение поля должно быть больше 0

    // 2) Доступ к значениям полей
    public Integer get_depth() {return depth;}
    public Float get_numberOfTreasures() {return numberOfTreasures;}

    // 3) Установка значений в поля
    public void set_depth(Integer depth) { this.depth = depth; }
    public void set_numberOfTreasures(Float numberOfTreasures) { this.numberOfTreasures = numberOfTreasures; }
}