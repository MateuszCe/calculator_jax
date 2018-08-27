package ciezki.jeppesen.calcullator;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj wyrażenie infiksowe: ");

        // pobieramy od użytkownika wyrażenie
        String input = sc.nextLine();

        // tworzymy nowy obiekt klasy OdwrotnaNotacjaPolska
        // i przekazujemy do konstruktora pobrane od użytkownika wyrażenie
        Calculator onp = new Calculator(input);

        // wyświetlamy wyrażenie w postaci postfiksowej
        System.out.println(onp);
    }
}
