package com.example.calculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.function.BiFunction;

///calculator/plus?num1=5&num2=5
@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    private final CalculatorService service;

    CalculatorController(CalculatorService service) {
        this.service = service;
    }

    @GetMapping
    public String greeting() {
        return "Добро пожаловать в калькулятор";
    }

    @GetMapping("/plus")
    //Без @RequestParam все будет работать, но не будет ошибки (400 Bad Request) при отсутствии параметра.
    //В метод придет Параметр = null.
    //С @RequestParam при отсутствии пользователю придет 400 Bad Request,
    //а логах останется какой параметр отсутствовал
    public String plus(@RequestParam Integer num1, @RequestParam Integer num2) {
        return view(num1, num2, '+', service::plus);
    }
    //вариант с лямбдой: return view(num1, num2, '+', (a,b)->a+b);
    //вариант с анонимным классом: return view(num1, num2, '-', new BiFunction<Integer, Integer, Number>(){
    //        public Number apply(Integer a, Integer b) {return a+b;}})

    @GetMapping("/minus")
    public String minus(@RequestParam Integer num1, @RequestParam Integer num2) {
        return view(num1, num2, '-', service::minus);
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam Integer num1, @RequestParam Integer num2) {
        return view(num1, num2, '*', service::multiply);
    }

    @GetMapping("/divide")
    public String divide(@RequestParam Integer num1, @RequestParam Integer num2) {
        return view(num1, num2, '/', service::divide);
    }

    private String view(Integer num1, Integer num2, char actionView, BiFunction<Integer, Integer, Number> action) {
        //if ((num1 == null) || (num2 == null)) {
        ///    return "Необходимо задать 2 параметра: num1 и num2";
        //}
        //if ((actionView == '/') && (num2 == 0)) {
        //    return "На 0 делить нельзя";
        //}
        return num1 + " " + actionView + " " + num2 + " = " + action.apply(num1, num2);
    }
}


