package com.example.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public int plus(int num1, int num2) {
        return num1 + num2;  //В случае переполнения ошибки нет. Крутит следующий оборот
    }
    public int minus(int num1, int num2) {
        return num1 - num2;
    }
    public int multiply(int num1, int num2) {
        return num1 * num2;
    }
    public double divide(int num1, int num2) {
        if (num2 == 0) {
            //Если ничего не проверять, то ошибки не возникнет,
            //т.к. при действии с числами с пл.запятой при делении на 0 вернется INFINITY
            //Можно бросить стандартное IllegalArgumentException или ArithmeticException.
            //Тогда сообщение останется в логе, но пользователю придет непонятное 500 Internal Server Error
            //Поэтому бросаем свое самодельное исключение,
            //а в ResponseStatus указываем что-нибудь чуть более понятное. У меня 406
            throw new DivisionByZeroException();
        }
        return (double)num1 / num2;
    }
}
