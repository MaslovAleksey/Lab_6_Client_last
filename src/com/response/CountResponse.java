package com.response;

import java.io.Serializable;

/**
 * Класс, являющийся результатом обработки ответа сервера
 */

public class CountResponse implements Serializable {
    long count;

    public long getCount() {
        return count;
    }

    public CountResponse(long count) {
        this.count = count;
    }
}
