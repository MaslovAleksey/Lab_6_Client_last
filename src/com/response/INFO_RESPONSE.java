package com.response;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Класс, являющийся результатом обработки ответа сервера
 */

public class INFO_RESPONSE implements Serializable {
    static final long serialVersionUID = -4862926644813433708L;
    String collectionType;
    int size;
    String elementType;
    LocalDateTime creationDate;

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public int getSize() {
        return size;
    }

    public String getElementType() {
        return elementType;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
