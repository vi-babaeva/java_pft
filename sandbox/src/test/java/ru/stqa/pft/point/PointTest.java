package ru.stqa.pft.point;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by dequa on 21.03.17.
 */
public class PointTest {

  @Test
  public void testDistance1(){
    Point p1 = new Point(5,7);
    Point p2 = new Point(17, 12);
    Assert.assertEquals(p1.distance(p2), 13.0);
  }

  @Test
  public void testDistance2(){
    Point p1 = new Point(41,2);
    Point p2 = new Point(41, 2);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  @Test
  public void testDistance3(){
    Point p1 = new Point(1,0);
    Point p2 = new Point(2, 0);
    Assert.assertEquals(p1.distance(p2), 1.0);

  }
}
