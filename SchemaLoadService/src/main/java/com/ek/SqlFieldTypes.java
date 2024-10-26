package com.ek;

import com.ek.dto.Field;

public enum SqlFieldTypes {
    INT, VARCHAR, DATE, BIGINT;

    public static String fromField(Field field) {

        if (field.getType().equals("integer")) {
            if (field.getFormat().equals("int32")) {
                return INT.name();
            } else if (field.getFormat().equals("int64")) {
                return BIGINT.name();
            }
        } else if (field.getType().equals("text") || field.getType().equals("string")) {
            return VARCHAR.name();
        } else if (field.getType().equals("date")) {
            return DATE.name();
        }

        throw new IllegalArgumentException();
    }
}
