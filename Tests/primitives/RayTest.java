package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void findClosestPoint() {
        Ray ray = new Ray(new Point3D(0, 0, 0), new Vector(1, 1, 1));
        //case1:
        List<Point3D> list = new LinkedList<>();
        list.add(new Point3D(5, 5, 5));
        list.add(new Point3D(2, 2, 2));
        list.add(new Point3D(4, 4, 4));
        assertEquals(list.get(1), ray.findClosestPoint(list), "case1 ERROR");

        /////////////////////boundary cases:
        //case2:
        list = new LinkedList<>();
        assertNull(ray.findClosestPoint(list), "case 2 ERROR");

        //case3:
        list = new LinkedList<>();
        list.add(new Point3D(0.5, 0.5, 0.5));
        list.add(new Point3D(7, 7, 7));
        list.add(new Point3D(-1, -1, -1));
        assertEquals(list.get(0), ray.findClosestPoint(list), "case 3 ERROR");

        //case4:
        list = new LinkedList<>();
        list.add(new Point3D(0.5, 0.5, 0.5));
        list.add(new Point3D(7, 7, 7));
        list.add(new Point3D(0.2, 0.2, 0.2));
        assertEquals(list.get(list.size() - 1), ray.findClosestPoint(list), "case 4 ERROR");
    }
}