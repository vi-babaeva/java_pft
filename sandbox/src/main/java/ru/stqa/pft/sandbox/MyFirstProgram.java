package ru.stqa.pft.sandbox;

public class MyFirstProgram {


    public static void main(String[] args) { /* функция main обращается к функции hello*/
        hello("world");
        hello("Victoria");
        hello("user");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4,6);
      System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
    }

    public static void hello(String somebody) {  /*String somebody - параметр функции hello */
        System.out.println("Hello, " + somebody + "!");
    }

  /* void - если функция не возвращает никаких значений */
  /* double -  если функция возвращает какой-то результат. Используем return */


}