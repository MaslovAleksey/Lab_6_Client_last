package com.support;

import com.beg_data.Dragon;
import java.io.Serializable;

/**
 * Класс, содержащий информацию для передачи на сервер
 */
public class ObjToServer implements Serializable {
    private Dragon dragon;
    private Integer value;
    private String command;
    private String fileName;
    private Double cave;


    public ObjToServer(String command) { this.command = command; }

    public void setDragon (Dragon dragon) { this.dragon = dragon; }
    public void setValue (Integer value) { this.value = value; }
    public void setFileName (String fileName) { this.fileName = fileName; }
    public void setCave (Double cave) { this.cave = cave; }

    public String getCommand() {return command; }
    public Dragon getDragon () { return dragon; }
    public Integer getValue () { return value; }
    public String getFileName () { return fileName; }
    public Double getCave () { return cave; }

}
