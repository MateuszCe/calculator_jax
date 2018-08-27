package ciezki.jeppesen.calcullator;

import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {
    private String infixExpression;
    private String postfixExpression;
    private double result;

    public Calculator(String input) {
        this.infixExpression = input;
        postfixExpression = "";
        result = 0.0;
        convertInfixToPostfix();
        result = calculate();
    }

    public String getInfixExpression() {
        return infixExpression;
    }

    public String getPostfixExpression() {
        return postfixExpression;
    }

    public double getResult() {
        return result;
    }
    private static boolean isOperator(String element) {
        if (element.equals("+")) {
            return true;
        } else if (element.equals("-")) {
            return true;
        } else if (element.equals("*")) {
            return true;
        } else if (element.equals("/")) {
            return true;
        }
        return false;
    }

//    metoda zamienia input na output za pomoca ONP
    private void convertInfixToPostfix() {
        // tworzymy pusty stos
        Stack<String> stack = new Stack<String>();

        // dzielimy wyrażenie infiksowe na części na podstawie separatorów
        StringTokenizer stringTokenizer = new StringTokenizer(infixExpression, "+-*/()", true);

        // pobieranie elementow z tokenow; sprawdzenie czy jest operatorem by sprawdzic piorytet piorytetu
        while (stringTokenizer.hasMoreTokens()) {
            String element = stringTokenizer.nextToken();

            if (isOperator(element)) {
                while (!stack.empty() && piority(stack.peek()) >= piority(element))
                    postfixExpression += stack.pop() + " ";
                stack.push(element);
            } else postfixExpression += element + " ";
        }
        // ściągamy ze stosu pozostałe operatory i dodajemy je do wyrażenia postfiksowego
        while (!stack.empty()) postfixExpression += stack.pop() + " ";

    }

    // ustalanie piorytetu operatora
    public static int piority(String operator) {
        if (operator.equals("+") || operator.equals("-")) return 1;
        else if (operator.equals("*") || operator.equals("/")) return 2;
//        pozwala na wstawianie pustych znakow
        else return 0;
    }

    // obliczenie wyniku
    private double calculate() {
        Stack<Double> stack = new Stack<Double>();
        StringTokenizer stringTokenizer = new StringTokenizer(postfixExpression, " ");
        while (stringTokenizer.hasMoreTokens()) {
            String element = stringTokenizer.nextToken();
            // sprawdzenie czy element posiadad wartosc
            if (!isOperator(element)) {
                double value = Double.parseDouble(element);
                // odlozenie na stosie
                stack.push(value);
            } else {
                //  jeśli element jest operatorem ściągamy dwie wartości ze stosu
                double value1 = stack.pop();
                double value2 = stack.pop();
                // w zależności od operatora obliczamy wynik i odkładamy go na stos
                switch (element.charAt(0)) {
                    case '*': {
                        stack.push(value2 * value1);
                        break;
                    }
                    case '+': {
                        stack.push(value2 + value1);
                        break;
                    }
                    case '-': {
                        stack.push(value2 - value1);
                        break;
                    }
                    case '/': {
                        stack.push(value2 / value1);
                        break;
                    }
                }
            }
        }
        return stack.pop();
    }
    public String toString() {
        return "\nWynik: " + result;
    }




}
