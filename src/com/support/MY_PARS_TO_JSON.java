package com.support;

import com.beg_data.Dragon;
import org.json.simple.JSONObject;

/**
 * Класс реализующий преобразование объекта класса Dragon в объект класса Json
 */
public class MY_PARS_TO_JSON {
    /**
     * Метод, преобразующий объект класса Dragon в объект класса Json
     * @param _drag Элемент коллекции, преобразуемый в объект класса Json
     * @return Преобразованный элемент коллекции
     */
    public JSONObject convertor_to_json(Dragon _drag) // метод, переводящий объект класса Dragon в объект класса Json
    {
        JSONObject drag_json = new JSONObject();
        JSONObject coordinates_json = new JSONObject();
        JSONObject cave_json = new JSONObject();

        drag_json.put("name", _drag.get_name());

        coordinates_json.put("x", _drag.get_x());
        coordinates_json.put("y", _drag.get_y());
        drag_json.put("coordinates", coordinates_json);

        drag_json.put("age", _drag.get_age());

        drag_json.put("speaking", _drag.get_speaking());

        drag_json.put("color",_drag.get_color());

        drag_json.put("type", _drag.get_DragonType());

        cave_json.put("depth", _drag.get_depth());
        cave_json.put("numberOfTreasures", _drag.get_numberOfTreasures());
        drag_json.put("cave", cave_json);

        return drag_json;
    }
}
