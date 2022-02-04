package com.joshuacrotts.microscheme.parser;

@FunctionalInterface
public interface MSFunction<T, R> {

    R apply(T t) throws MSSemanticException;

}
