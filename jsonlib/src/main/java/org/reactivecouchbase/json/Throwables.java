package org.reactivecouchbase.json;

public class Throwables {
    private Throwables() {}

    public static RuntimeException propagate(Throwable throwable) {
        if (throwable == null) {
            throw new NullPointerException();
        }
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException) throwable;
        }
        if (throwable instanceof Error) {
            throw (Error) throwable;
        }
        throw new RuntimeException(throwable);
    }
}
