package org.reactivecouchbase.json;

import java.math.BigDecimal;
import java.math.BigInteger;

public class JsNumber extends JsValue implements java.lang.Comparable<JsNumber> {
    public final BigDecimal value;
    public JsNumber(BigDecimal value) {
        if (value == null) throw new IllegalArgumentException("Value can't be null !");
        this.value = value;
    }
    public JsNumber(BigInteger value) {
        if (value == null) throw new IllegalArgumentException("Value can't be null !");
        this.value = new BigDecimal(value);
    }
    public JsNumber(Integer value) {
        if (value == null) throw new IllegalArgumentException("Value can't be null !");
        this.value = BigDecimal.valueOf(value).setScale(0);
    }
    public JsNumber(Long value) {
        if (value == null) throw new IllegalArgumentException("Value can't be null !");
        this.value = BigDecimal.valueOf(value).setScale(0);
    }
    public JsNumber(Double value) {
        if (value == null) throw new IllegalArgumentException("Value can't be null !");
        this.value = BigDecimal.valueOf(value);
    }
    @Override
    public int compareTo(JsNumber jsNumber) {
        return value.compareTo(jsNumber.value);
    }
    public String toJsonString() {
        return value.toString();
    }
    public String toString() {
        return "JsNumber(" + value.toString() + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JsNumber)) return false;
        JsNumber jsNumber = (JsNumber) o;
        if (!value.equals(jsNumber.value)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}