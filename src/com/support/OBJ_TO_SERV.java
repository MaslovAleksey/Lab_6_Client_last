package com.support;

import com.beg_data.Dragon;
import java.io.Serializable;

/**
 * Класс, содержащий информацию для передачи на сервер
 */
public class OBJ_TO_SERV implements Serializable {
    private Dragon dragon;
    private Integer value;
    private String command;
    private String file_name;
    private Double cave;


    public OBJ_TO_SERV(String command) { this.command = command; }

    public void set_Dragon (Dragon dragon) { this.dragon = dragon; }
    public void set_value (Integer value) { this.value = value; }
    public void set_file_name (String file_name) { this.file_name = file_name; }
    public void set_cave (Double cave) { this.cave = cave; }

    public String get_command() {return command; }
    public Dragon get_Dragon () { return dragon; }
    public Integer get_value () { return value; }
    public String get_file_name () { return file_name; }
    public Double get_cave () { return cave; }

}
