package org.reactivecouchbase.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Syntax {

    private Syntax() {
    }

    public static JsPair $(String name, JsValue value) {
        return new JsPair(name, value);
    }

    public static JsPair $(String name, Date value) {
        return $(name, value, DateTimeFormatter.ISO_DATE_TIME);
    }

    public static JsPair $(String name, Date value, String format) {
        return $(name, value, DateTimeFormatter.ofPattern(format));
    }

    public static JsPair $(String name, Date value, DateTimeFormatter format) {
        if (value == null) {
            return nul(name);
        }
        LocalDateTime date = value.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        return new JsPair(name, format.format(date));
    }

    public static JsPair $(String name, java.time.LocalDate value, String format) {
        if (value == null) {
            return nul(name);
        }
        return new JsPair(name, value.format(DateTimeFormatter.ofPattern(format)));
    }

    public static JsPair $(String name, java.time.LocalDate value, DateTimeFormatter format) {
        if (value == null) {
            return nul(name);
        }
        return new JsPair(name, value.format(format));
    }

    public static JsPair $(String name, java.time.LocalTime value, String format) {
        if (value == null) {
            return nul(name);
        }
        return new JsPair(name, value.format(DateTimeFormatter.ofPattern(format)));
    }

    public static JsPair $(String name, java.time.LocalTime value, DateTimeFormatter format) {
        if (value == null) {
            return nul(name);
        }
        return new JsPair(name, value.format(format));
    }

    public static JsPair $(String name, LocalDateTime value, String format) {
        if (value == null) {
            return nul(name);
        }
        return new JsPair(name, value.format(DateTimeFormatter.ofPattern(format)));
    }

    public static JsPair $(String name, LocalDateTime value, DateTimeFormatter format) {
        if (value == null) {
            return nul(name);
        }
        return new JsPair(name, value.format(format));
    }

    public static JsPair $(String name, Long value) {
        return new JsPair(name, value);
    }

    public static JsPair $(String name, Integer value) {
        return new JsPair(name, value);
    }

    public static JsPair $(String name, Double value) {
        return new JsPair(name, value);
    }

    public static JsPair $(String name, BigDecimal value) {
        return new JsPair(name, value);
    }

    public static JsPair $(String name, BigInteger value) {
        return new JsPair(name, value);
    }

    public static JsPair nul(String name) {
        return new JsPair(name);
    }

    public static JsPair undefined(String name) {
        return new JsPair(name, JsUndefined.JSUNDEFINED_INSTANCE);
    }

    public static JsPair $(String name, String value) {
        return new JsPair(name, value);
    }

    public static JsPair $$$(String name, Object value) {
        return new JsPair(name, Json.wrap(value));
    }

    public static JsPair $(String name, Boolean value) {
        return new JsPair(name, value);
    }

    public static JsString string(String value) {
        return new JsString(value);
    }

    public static JsBoolean bool(Boolean value) {
        return new JsBoolean(value);
    }

    public static JsNumber number(Integer value) {
        return new JsNumber(value);
    }

    public static JsNumber number(Long value) {
        return new JsNumber(value);
    }

    public static JsNumber number(Double value) {
        return new JsNumber(value);
    }

    public static JsNumber number(BigDecimal value) {
        return new JsNumber(value);
    }

    public static JsNumber number(BigInteger value) {
        return new JsNumber(value);
    }

    public static JsNull nill() {
        return JsNull.JSNULL_INSTANCE;
    }

    public static JsUndefined undefined() {
        return JsUndefined.JSUNDEFINED_INSTANCE;
    }

    static Map<String, JsValue> asMap(String name, JsValue value) {
        Map<String, JsValue> values = new HashMap<String, JsValue>();
        values.put(name, value);
        return values;
    }
}
