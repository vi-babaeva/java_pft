package ru.stqa.pft.sandbox;

public class MyFirstProgram {


    public static void main(String[] args) { /* функция main обращается к функции hello*/

        Rectangle r = new Rectangle(4,6);
      System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
    }


    }

  /* void - если функция не возвращает никаких значений */
  /* double -  если функция возвращает какой-то результат. Используем return */


