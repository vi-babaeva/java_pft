package ru.stqa.pft.sandbox;

/**
 * Created by dequa on 16.03.17.
 */
public class Square {

  public double l;

  public Square(double l){ /* конструктор */
    this.l=l;
  }

  public double area () {
    return this.l  * this.l;
  }

}
