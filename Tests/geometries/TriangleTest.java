package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class TriangleTest {

    @Test
    void findIntersections() {
        Point3D p;
        Point3D p1 = new Point3D(1, 1, 1);
        Point3D p2 = new Point3D(1, 3, 1);
        Point3D p3 = new Point3D(1, 2, 4);

        List<Point3D> result;
        Triangle triangle = new Triangle(p1, p2, p3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray goes through the triangle
        p = new Point3D(1, 2, 3);
        result = triangle.findIntersections(new Ray(new Point3D(-1, 2, 1), new Vector(2, 0, 2)));
        assertEquals(result, List.of(p), "Ray goes through the triangle");

        // TC02: Ray goes outside against edge
        assertNull(triangle.findIntersections(new Ray(new Point3D(-1, 2, 1),
                new Vector(1.9311952143758, 0.8240102366972, 2))), "Ray goes outside against edge");

        // TC03: Ray goes outside against vertex
        assertNull(triangle.findIntersections(new Ray(new Point3D(-1, 2, 1),
                new Vector(2, 2, -1))), "Ray goes outside against vertex");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray begins before the plane
        // TC04: The intersection is on an edge
        assertNull(triangle.findIntersections(new Ray(new Point3D(-1, 2, 1),
                new Vector(2, 0.5, 0))), "The intersection is on an edge");

        // TC05: The intersection is in a vertex
        assertNull(triangle.findIntersections(new Ray(new Point3D(-1, 2, 1),
                new Vector(2, 1, 0))), "The intersection is on an edge");

        // TC06: The intersection is on an edge continuation
        assertNull(triangle.findIntersections(new Ray(new Point3D(-1, 2, 1),
                new Vector(2, 3, 0))), "The intersection is on an edge");
    }
}