package org.reactivecouchbase.json;

import com.google.common.collect.ImmutableMap;
import org.joda.time.DateTime;
import org.reactivecouchbase.common.Functionnal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultReaders {

    public static <T> CReader<List<T>> seq(final Reader<T> reader) {
        return new CReader<List<T>>() {
            @Override
            public JsResult<List<T>> read(JsValue value) {
                try {
                    JsArray array = value.as(JsArray.class);
                    return new JsSuccess<List<T>>(array.mapWith(reader));
                } catch (Exception e) {
                    return new JsError<List<T>>(e);
                }
            }
        };
    }

    public static <A> CReader<A> pure(final A a) {
        return new CReader<A>() {
            @Override
            public JsResult<A> read(JsValue value) {
                return new JsSuccess<A>(a);
            }
        };
    }

    public static <T> Functionnal.Option<Reader<T>> getReader(Class<T> clazz) {
        Reader<T> val = (Reader<T>) readers.get(clazz);
        if (val == null) return Functionnal.Option.none();
        return Functionnal.Option.some(val);
    }

    public static final CReader<JsObject> JS_OBJECT_READER = new CReader<JsObject>() {
        @Override
        public JsResult<JsObject> read(JsValue value) {
            if (value.is(JsObject.class)) {
                return new JsSuccess<JsObject>((JsObject) value);
            }
            return new JsError<JsObject>(new IllegalAccessError("Not a JsObject"));
        }
    };

    public static final CReader<JsArray> JS_ARRAY_READER = new CReader<JsArray>() {
        @Override
        public JsResult<JsArray> read(JsValue value) {
            if (value.is(JsArray.class)) {
                return new JsSuccess<JsArray>((JsArray) value);
            }
            return new JsError<JsArray>(new IllegalAccessError("Not a JsArray"));
        }
    };

    public static final CReader<JsBoolean> JS_BOOLEAN_READER = new CReader<JsBoolean>() {
        @Override
        public JsResult<JsBoolean> read(JsValue value) {
            if (value.is(JsBoolean.class)) {
                return new JsSuccess<JsBoolean>((JsBoolean) value);
            }
            return new JsError<JsBoolean>(new IllegalAccessError("Not a JsBoolean"));
        }
    };

    public static final CReader<JsPair> JS_PAIR_READER = new CReader<JsPair>() {
        @Override
        public JsResult<JsPair> read(JsValue value) {
            if (value.is(JsPair.class)) {
                return new JsSuccess<JsPair>((JsPair) value);
            }
            return new JsError<JsPair>(new IllegalAccessError("Not a JsPair"));
        }
    };

    public static final CReader<JsNull> JS_NULL_READER = new CReader<JsNull>() {
        @Override
        public JsResult<JsNull> read(JsValue value) {
            if (value.is(JsNull.class)) {
                return new JsSuccess<JsNull>((JsNull) value);
            }
            return new JsError<JsNull>(new IllegalAccessError("Not a JsNull"));
        }
    };

    public static final CReader<JsUndefined> JS_UNDEFINED_READER = new CReader<JsUndefined>() {
        @Override
        public JsResult<JsUndefined> read(JsValue value) {
            if (value.is(JsUndefined.class)) {
                return new JsSuccess<JsUndefined>((JsUndefined) value);
            }
            return new JsError<JsUndefined>(new IllegalAccessError("Not a JsUndefined"));
        }
    };

    public static final CReader<Syntax.JsonFormattedValue> JS_FORMATTED_READER = new CReader<Syntax.JsonFormattedValue>() {
        @Override
        public JsResult<Syntax.JsonFormattedValue> read(JsValue value) {
            if (value.is(Syntax.JsonFormattedValue.class)) {
                return new JsSuccess<Syntax.JsonFormattedValue>((Syntax.JsonFormattedValue) value);
            }
            return new JsError<Syntax.JsonFormattedValue>(new IllegalAccessError("Not a JsonFormattedValue"));
        }
    };

    public static final CReader<JsNumber> JS_NUMBER_READER = new CReader<JsNumber>() {
        @Override
        public JsResult<JsNumber> read(JsValue value) {
            if (value.is(JsNumber.class)) {
                return new JsSuccess<JsNumber>((JsNumber) value);
            }
            return new JsError<JsNumber>(new IllegalAccessError("Not a JsNumber"));
        }
    };

    public static final CReader<JsString> JS_STRING_READER = new CReader<JsString>() {
        @Override
        public JsResult<JsString> read(JsValue value) {
            if (value.is(JsString.class)) {
                return new JsSuccess<JsString>((JsString) value);
            }
            return new JsError<JsString>(new IllegalAccessError("Not a JsString"));
        }
    };

    public static final CReader<Boolean> BOOLEAN_READER = new CReader<Boolean>() {
        @Override
        public JsResult<Boolean> read(JsValue value) {
            if (value.is(JsBoolean.class)) {
                return new JsSuccess<Boolean>(((JsBoolean) value).value);
            }
            return new JsError<Boolean>(new IllegalAccessError("Not a JsBoolean"));
        }
    };

    public static final CReader<String> STRING_READER = new CReader<String>() {
        @Override
        public JsResult<String> read(JsValue value) {
            if (value.is(JsString.class)) {
                return new JsSuccess<String>(((JsString) value).value);
            }
            return new JsError<String>(new IllegalAccessError("Not a JsString"));
        }
    };

    public static final CReader<Double> DOUBLE_READER = new CReader<Double>() {
        @Override
        public JsResult<Double> read(JsValue value) {
            if (value.is(JsNumber.class)) {
                return new JsSuccess<Double>(((JsNumber) value).value.doubleValue());
            }
            return new JsError<Double>(new IllegalAccessError("Not a JsNumber"));
        }
    };

    public static final CReader<Short> SHORT_READER = new CReader<Short>() {
        @Override
        public JsResult<Short> read(JsValue value) {
            if (value.is(JsNumber.class)) {
                return new JsSuccess<Short>(((JsNumber) value).value.shortValue());
            }
            return new JsError<Short>(new IllegalAccessError("Not a JsNumber"));
        }
    };

    public static final CReader<Float> FLOAT_READER = new CReader<Float>() {
        @Override
        public JsResult<Float> read(JsValue value) {
            if (value.is(JsNumber.class)) {
                return new JsSuccess<Float>(((JsNumber) value).value.floatValue());
            }
            return new JsError<Float>(new IllegalAccessError("Not a JsNumber"));
        }
    };

    public static final CReader<Long> LONG_READER = new CReader<Long>() {
        @Override
        public JsResult<Long> read(JsValue value) {
            if (value.is(JsNumber.class)) {
                return new JsSuccess<Long>(((JsNumber) value).value.longValue());
            }
            return new JsError<Long>(new IllegalAccessError("Not a JsNumber"));
        }
    };

    public static final CReader<Integer> INTEGER_READER = new CReader<Integer>() {
        @Override
        public JsResult<Integer> read(JsValue value) {
            if (value.is(JsNumber.class)) {
                return new JsSuccess<Integer>(((JsNumber) value).value.intValue());
            }
            return new JsError<Integer>(new IllegalAccessError("Not a JsNumber"));
        }
    };

    public static final CReader<BigDecimal> BIGDEC_READER = new CReader<BigDecimal>() {
        @Override
        public JsResult<BigDecimal> read(JsValue value) {
            if (value.is(JsNumber.class)) {
                return new JsSuccess<BigDecimal>(((JsNumber) value).value);
            }
            return new JsError<BigDecimal>(new IllegalAccessError("Not a JsNumber"));
        }
    };

    public static final CReader<BigInteger> BIGINT_READER = new CReader<BigInteger>() {
        @Override
        public JsResult<BigInteger> read(JsValue value) {
            if (value.is(JsNumber.class)) {
                return new JsSuccess<BigInteger>(((JsNumber) value).value.toBigInteger());
            }
            return new JsError<BigInteger>(new IllegalAccessError("Not a JsNumber"));
        }
    };

    public static final CReader<DateTime> DATETIME_READER = new CReader<DateTime>() {
        @Override
        public JsResult<DateTime> read(JsValue value) {
            if (value.is(JsString.class)) {
                try {
                    return new JsSuccess<DateTime>(DateTime.parse(value.as(String.class)));
                } catch (Exception e) {
                    return new JsError<DateTime>(e);
                }
            }
            return new JsError<DateTime>(new IllegalAccessError("Not a JsString"));
        }
    };

    public static final CReader<JsValue> JSVALUE_READER = new CReader<JsValue>() {
        @Override
        public JsResult<JsValue> read(JsValue value) {
            return new JsSuccess<JsValue>(value);
        }
    };

    static final Map<Class<?>, CReader<?>> readers = ImmutableMap.copyOf(new HashMap<Class<?>, CReader<?>>() {{
        put(JsObject.class, JS_OBJECT_READER);
        put(JsArray.class, JS_ARRAY_READER);
        put(JsBoolean.class, JS_BOOLEAN_READER);
        put(JsPair.class, JS_PAIR_READER);
        put(JsNull.class, JS_NULL_READER);
        put(JsUndefined.class, JS_UNDEFINED_READER);
        put(Syntax.JsonFormattedValue.class, JS_FORMATTED_READER);
        put(JsNumber.class, JS_NUMBER_READER);
        put(JsString.class, JS_STRING_READER);
        put(Boolean.class, BOOLEAN_READER);
        put(String.class, STRING_READER);
        put(Double.class, DOUBLE_READER);
        put(Long.class, LONG_READER);
        put(Short.class, SHORT_READER);
        put(Float.class, FLOAT_READER);
        put(Integer.class, INTEGER_READER);
        put(BigDecimal.class, BIGDEC_READER);
        put(BigInteger.class, BIGINT_READER);
        put(JsValue.class, JSVALUE_READER);
        put(DateTime.class, DATETIME_READER);
    }});
}