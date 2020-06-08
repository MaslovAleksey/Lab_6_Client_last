package com.beg_data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Класс, экзэмпляры которого хранятся в коллекции
 */
public class Dragon implements Comparable<Dragon>, Serializable {
    static final long serialVersionUID = -4862926644813433707L;

    // 1) Начальные поля
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long age; //Значение поля должно быть больше 0, Поле не может быть null
    private boolean speaking;
    private Color color; //Поле может быть null
    private DragonType type; //Поле может быть null
    private DragonCave cave; //Поле не может быть null
    // 2) Установка значений в поля
    public void set_name (String name) { this.name = name; }

    public void set_Coordinates(Double x, int y )
    {
        coordinates = new Coordinates();
            coordinates.set_X(x);
            coordinates.set_Y(y);
    }

    public void set_DragonCave(Integer depth, Float numberOfTreasures)
    {
        cave = new DragonCave();
        cave.set_depth(depth);
        cave.set_numberOfTreasures(numberOfTreasures);
    }

    public void set_age (Long age)
    {
            this.age = age;
    }
    public void set_Color (Color color) { this.color = color; }
    public void set_speaking (boolean speaking) {this.speaking = speaking;}
    public void set_DragonType (DragonType type) { this.type = type; }


    // 3) Доступ к значениям полей
    public String get_name () {return name;}
    public Long get_age() {return age;}
    public Double get_x() { return coordinates.get_X();}
    public int get_y() { return coordinates.get_Y();}
    public String get_color()
    {
        if (color == null)
            return null;
        else return color.toString();
    }
    public boolean get_speaking() {return speaking;}
    public Integer get_id () {return  id;}
    public LocalDateTime get_creationDate () { return creationDate;}
    public Integer get_depth() {return cave.get_depth();}
    public Float get_numberOfTreasures() {return cave.get_numberOfTreasures();}
    public String get_DragonType()
    {
        if (type == null)
            return null;
        else return type.toString();
     }

    public String get_Coordinates()
    {
        Double x = coordinates.get_X();
        Integer y = coordinates.get_Y();
        String r = "X = " + x.toString() + ", Y = " + y.toString();
        return r;
    }

    public String get_DragonCave()
    {
        Integer d = cave.get_depth();
        Float n = cave.get_numberOfTreasures();
        String r = "depth = " + d + ", number_of_treasures = " + n.toString();
        return r;
    }

    // 4.) Реализация интерфейса Comparable

    /**
     * Реализация интерфейса Comparable
     * @param other Объект класса Dragon для сравнения
     * @return Результат сравнения
     */
    public int compareTo(Dragon other)
    {
        if(getClass() != other.getClass()) throw new ClassCastException(); // проверка на то, чтобы метод не был вызван у наследника Dragon
        return Integer.compare(id,other.get_id()); // по возрастанию id
    }

    // 5.) Переопределение toString();
    @Override
    public String toString()
    {
        String r = "";
        r = "name = " + name + "; age = " + age + "; color =  " + color + "; Coordinates: " + get_Coordinates() +
                    "; speaking = " + speaking + "; Cave: " + get_DragonCave() + "; Dragon_type = " + get_DragonType()
                    + "; id = " + id + "; creation_Date = " + get_creationDate();
        return r;
    }

    // 6.) Переопределение equals()
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Dragon dr_ = (Dragon) obj;
        return ((id == dr_.get_id()) && (name.equals(dr_.get_name())) && (this.get_Coordinates().equals(dr_.get_Coordinates())) &&
                (age == dr_.get_age()) && (speaking == dr_.get_speaking()) && (this.get_color().equals(dr_.get_color())) &&
                (this.get_DragonType().equals(dr_.get_DragonType())) && (dr_.get_DragonCave().equals(dr_.get_DragonCave())));
    }

    // 7.) Переопределение hashCode()
    @Override
    public int hashCode()
    {
        Double result = (name.length() * Math.random() * 7) + (id * 5) + (coordinates.get_X() * 11) + (coordinates.get_Y() * 13) + (age * 17) + (cave.get_numberOfTreasures() * 6);
        if (speaking) result *= 3;
        else result *=2;
        if (color != null) result += get_color().length() * Math.random() * 37;
        if (type != null) result += get_DragonType().length() * Math.random() * 25;
        if (cave.get_depth() != null) result += cave.get_depth() * 19;
        return result.intValue();
    }
}



