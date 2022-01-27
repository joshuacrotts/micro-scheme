package com.joshuacrotts.minischeme.parser;

@FunctionalInterface
public interface MSFunction<T, R> {

    R apply(T t) throws MSSemanticException;

}
