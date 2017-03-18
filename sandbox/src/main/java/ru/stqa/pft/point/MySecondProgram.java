package ru.stqa.pft.point;

/**
 * Created by dequa on 17.03.17.
 */
public class MySecondProgram {

  public static void main(String[] args) {
    Point p1 = new Point();
    p1.x = 5;
    p1.y = 7;
    System.out.println("Точка с координатами: x1 = " + p1.x + " и y1 = " + p1.y);

    Point p2 = new Point();
    p2.x = 17;
    p2.y = 12;
    System.out.println("Точка с координатами: x2 = " + p2.x + " и y2 = " + p2.y);

    System.out.println("Расстояние между точкой p1 и p2 = " + Point.distance(p1, p2));
  }
}
