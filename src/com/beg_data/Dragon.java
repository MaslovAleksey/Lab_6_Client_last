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
    public void setName (String name) { this.name = name; }

    public void setCoordinates(Double x, int y )
    {
        coordinates = new Coordinates();
            coordinates.set_X(x);
            coordinates.set_Y(y);
    }

    public void setDragonCave(Integer depth, Float numberOfTreasures)
    {
        cave = new DragonCave();
        cave.set_Depth(depth);
        cave.set_NumberOfTreasures(numberOfTreasures);
    }

    public void setAge (Long age)
    {
            this.age = age;
    }
    public void setColor (Color color) { this.color = color; }
    public void setSpeaking (boolean speaking) {this.speaking = speaking;}
    public void setDragonType (DragonType type) { this.type = type; }


    // 3) Доступ к значениям полей
    public String getName () {return name;}
    public Long getAge() {return age;}
    public Double getX() { return coordinates.get_X();}
    public int getY() { return coordinates.get_Y();}
    public String getColor()
    {
        if (color == null)
            return null;
        else return color.toString();
    }
    public boolean getSpeaking() {return speaking;}
    public Integer getId () {return  id;}
    public LocalDateTime getCreationDate () { return creationDate;}
    public Integer getDepth() {return cave.get_Depth();}
    public Float getNumberOfTreasures() {return cave.get_NumberOfTreasures();}
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
        Integer d = cave.get_Depth();
        Float n = cave.get_NumberOfTreasures();
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
        return Integer.compare(id,other.getId()); // по возрастанию id
    }

    // 5.) Переопределение toString();
    @Override
    public String toString()
    {
        String r = "";
        r = "name = " + name + "; age = " + age + "; color =  " + color + "; Coordinates: " + get_Coordinates() +
                    "; speaking = " + speaking + "; Cave: " + get_DragonCave() + "; Dragon_type = " + get_DragonType()
                    + "; id = " + id + "; creation_Date = " + getCreationDate();
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
        return ((id == dr_.getId()) && (name.equals(dr_.getName())) && (this.get_Coordinates().equals(dr_.get_Coordinates())) &&
                (age == dr_.getAge()) && (speaking == dr_.getSpeaking()) && (this.getColor().equals(dr_.getColor())) &&
                (this.get_DragonType().equals(dr_.get_DragonType())) && (dr_.get_DragonCave().equals(dr_.get_DragonCave())));
    }

    // 7.) Переопределение hashCode()
    @Override
    public int hashCode()
    {
        Double result = (name.length() * Math.random() * 7) + (id * 5) + (coordinates.get_X() * 11) + (coordinates.get_Y() * 13) + (age * 17) + (cave.get_NumberOfTreasures() * 6);
        if (speaking) result *= 3;
        else result *=2;
        if (color != null) result += getColor().length() * Math.random() * 37;
        if (type != null) result += get_DragonType().length() * Math.random() * 25;
        if (cave.get_Depth() != null) result += cave.get_Depth() * 19;
        return result.intValue();
    }
}



