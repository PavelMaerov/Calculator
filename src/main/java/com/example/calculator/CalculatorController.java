package com.example.calculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//calculator/plus?num1=5&num2=5
@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    private final CalculatorServis servis;

    CalculatorController(CalculatorServis servis) {
        this.servis = servis;
    }

    @GetMapping
    public String greeting() {
        return "Добро пожаловать в калькулятор";
    }
    private final static String ERROR="Необходимо задать 2 параметра: num1 и num2";
    @GetMapping("/plus")
    public String plus(Integer num1, Integer num2) {
        return check(num1, num2) ? view(num1, num2, '+', servis.plus(num1,num2)) : ERROR;
    }

    @GetMapping("/minus")
    public String minus(Integer num1, Integer num2) {
        return check(num1, num2) ? view(num1, num2, '-', servis.minus(num1,num2)) : ERROR;
    }

    @GetMapping("/multiply")
    public String multiply(Integer num1, Integer num2) {
        return check(num1, num2) ? view(num1, num2, '*', servis.multiply(num1,num2)) : ERROR;
    }

    @GetMapping("/divide")
    public String divide(Integer num1, Integer num2) {
        if (num2 != null && num2 == 0) return "На 0 делить нельзя";
        return check(num1, num2) ? view(num1, num2, '/', servis.divide(num1,num2)) : ERROR;
    }

    private Boolean check(Integer num1, Integer num2) {
        return (num1 != null) && (num2 != null);
    }
    private String view(Integer num1, Integer num2, char actionView, Number result) {
        return num1 + " " + actionView + " " + num2 + " = " + result;
    }
}
