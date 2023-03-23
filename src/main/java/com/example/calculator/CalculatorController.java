package com.example.calculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.function.BiFunction;

///calculator/plus?num1=5&num2=5
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

    @GetMapping("/plus")
    public String plus(Integer num1, Integer num2) {
        return view(num1, num2, '+', servis::plus);
    }

    @GetMapping("/minus")
    public String minus(Integer num1, Integer num2) {
        return view(num1, num2, '-', servis::minus);
    }

    @GetMapping("/multiply")
    public String multiply(Integer num1, Integer num2) {
        return view(num1, num2, '*', servis::multiply);
    }

    @GetMapping("/divide")
    public String divide(Integer num1, Integer num2) {
        return view(num1, num2, '/', servis::divide);
    }

    private String view(Integer num1, Integer num2, char actionView, BiFunction<Integer, Integer, Number> action) {
        if ((num1 == null) || (num2 == null)) {
            return "Необходимо задать 2 параметра: num1 и num2";
        }
        if ((actionView == '/') && (num2 == 0)) {
            return "На 0 делить нельзя";
        }
        return num1 + " " + actionView + " " + num2 + " = " + action.apply(num1, num2);
    }
}


