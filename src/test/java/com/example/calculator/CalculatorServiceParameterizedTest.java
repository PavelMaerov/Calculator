package com.example.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorServiceParameterizedTest {
    private final CalculatorService out = new CalculatorService();  //инъекцию не использую, т.к. у этого сервиса нет данных

    public static Stream<Arguments> paramsForPlusTest() {  //Spring ищет строго статичесткий метод
        return Stream.of(Arguments.of(6, 1, 5),
                         Arguments.of(-2, Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @ParameterizedTest
    @MethodSource("paramsForPlusTest")
    void plusTest(int result, int num1, int num2) {
        assertEquals(result, out.plus(num1, num2));
    }

    public static Stream<Arguments> paramsForMinusTest() {
        return Stream.of(Arguments.of(0, 5, 5),
                         Arguments.of(Integer.MAX_VALUE, Integer.MIN_VALUE, 1));
    }

    @ParameterizedTest
    @MethodSource("paramsForMinusTest")
    void minusTest(int result, int num1, int num2) {
        assertEquals(result, out.minus(num1, num2));
    }

    public static Stream<Arguments> paramsForMultiplyTest() {
        return Stream.of(Arguments.of(25, 5, 5),
                         Arguments.of(1, Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @ParameterizedTest
    @MethodSource("paramsForMultiplyTest")
    void multiplyTest(int result, int num1, int num2) {
        assertEquals(result, out.multiply(num1, num2));
    }

    public static Stream<Arguments> paramsForDivideTest() {
        return Stream.of(Arguments.of(1.0, 5, 5),
                         Arguments.of((double)-Integer.MAX_VALUE, Integer.MAX_VALUE, -1),
                         Arguments.of(0.5, 1, 2));
    }

    @ParameterizedTest
    @MethodSource("paramsForDivideTest")
    void divideTest(Double result, int num1, int num2) {
        assertEquals(result, out.divide(num1, num2));
    }
}