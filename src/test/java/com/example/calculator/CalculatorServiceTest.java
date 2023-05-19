package com.example.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {
    private final CalculatorService out = new CalculatorService();  //инъекцию не использую, т.к. у этого сервиса нет данных

    @Test
    void plusTest() {
        assertEquals(6, out.plus(1, 5));
        assertEquals(-2, out.plus(Integer.MAX_VALUE, Integer.MAX_VALUE)); //переполнения не возникает
    }

    @Test
    void minusTest() {
        assertEquals(0, out.minus(5, 5));
        assertEquals(Integer.MAX_VALUE, out.minus(Integer.MIN_VALUE, 1)); //переполнения не возникает
    }

    @Test
    void multiplyTest() {
        assertEquals(25, out.multiply(5, 5));
        assertEquals(1, out.multiply(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test
    void divideTest() {
        assertEquals(1, out.divide(5, 5));  //сравнение Integer и Double проходит корректно
        assertEquals(-Integer.MAX_VALUE, out.divide(Integer.MAX_VALUE, -1));
        assertEquals(0.5, out.divide(1, 2));
    }

    @Test
    void divisionByZeroExceptionTest() {
        assertThrows(DivisionByZeroException.class, ()->out.divide(5, 0));
    }
}