package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SphereTest {

    @Test
    void getNormal() {
        Point3D v = new Point3D(0,2,0);
        Sphere sphere = new Sphere(2, new Point3D(0,0,0));
        assertEquals(sphere.getNormal(v), new Vector(0,1,0), "ERROR: the getNormal function not working well");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)/////////////////////////////////////////////////////////////////////////////////
        assertNull(sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points - TC02");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)/////////////////////////////////////////////////////////////////////////////////
        p1 = new Point3D(1.515589476006346, -0.855181938998403, 0.053210369688937);
        result = sphere.findIntersections(new Ray(new Point3D(0.5, -0.4, 0.3),
                new Vector(1.015589476006346, -0.455181938998403, -0.246789630311063)));
        assertEquals(1, result.size(), "Wrong number of points - TC03");

        assertEquals(result, List.of(p1),
                "Ray's line starts inside the sphere but the function does not return the right point"
        );

        // TC04: Ray starts after the sphere (0 points)/////////////////////////////////////////////////////////////////////////////////
        assertNull(sphere.findIntersections(new Ray(new Point3D(3,0.2,0.3), new Vector(1, 0, 0))), "Ray's starting after the sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)/////////////////////////////////////////////////////////////////////////////////
        p1 = new Point3D(0.114673382304, -0.364830121081, 0.2882633565872);
        result = sphere.findIntersections(new Ray(new Point3D(1.9058047269068, -0.1444243154556, 0.3983207423905),
                new Vector(-1.7911313446028, -0.2204058056254, -0.1100573858034)));
        assertEquals(1, result.size(), "Wrong number of points - TC11");

        assertEquals(result, List.of(p1),
                "Ray's line starts at the sphere and crosses the sphere only in 1 point"
        );

        // TC12: Ray starts at sphere and goes outside (0 points)/////////////////////////////////////////////////////////////////////////////////
        assertNull(sphere.findIntersections(new Ray(new Point3D(1.9058047269068, -0.1444243154556, 0.3983207423905),
                new Vector(1.0941952730932, 0.3444243154556, 0.1016792576095))), "Ray's starting at the sphere and goes outside");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)/////////////////////////////////////////////////////////////////////////////////
        p1 = new Point3D(1.9863939238321, 0, 0.1643989873054);
        p2 = new Point3D(0.0136060761679, 0, -0.1643989873054);
        result = sphere.findIntersections(new Ray(new Point3D(-2,0,-0.5),
                new Vector(3,0,0.5)));
        assertEquals(2, result.size(), "Wrong number of points - TC13");

        assertEquals(result, List.of(p2, p1),
                "Ray's line starts outside the sphere, goes through the center and crosses the sphere in 2 points"
        );

        // TC14: Ray starts at sphere and goes inside (1 points)/////////////////////////////////////////////////////////////////////////////////
        p1 = new Point3D(1.5132032452431, 0.1023799424431, -0.8521389419909);
        result = sphere.findIntersections(new Ray(new Point3D(0.4867967547569, -0.1023799424431, 0.8521389419909),
                new Vector(0.5132032452431,0.1023799424431,-0.8521389419909)));
        assertEquals(1, result.size(), "Wrong number of points - TC14");

        assertEquals(result, List.of(p1),
                "Ray's line starts at the sphere and crosses the sphere in 1 point"
        );

        // TC15: Ray starts inside (1 points)/////////////////////////////////////////////////////////////////////////////////
        p1 = new Point3D(0.141715405743, 0.1981563050891, -0.4733726164605);
        result = sphere.findIntersections(new Ray(new Point3D(1.7058876269046,-0.1629716819939,0.3893205996132),
                new Vector(-0.7058876269046, 0.1629716819939, -0.3893205996132)));
        assertEquals(1, result.size(), "Wrong number of points - TC15");

        assertEquals(result, List.of(p1),
                "Ray's line starts at the sphere and crosses the sphere in 1 point"
        );

        // TC16: Ray starts at the center (1 points)/////////////////////////////////////////////////////////////////////////////////
        p1 = new Point3D(1.900269628904, -0.1527708950857, 0.4076464753776);
        result = sphere.findIntersections(new Ray(sphere.getCenter(),
                new Vector(0.900269628904, -0.1527708950857, 0.4076464753776)));
        assertEquals(1, result.size(), "Wrong number of points - TC15");

        assertEquals(result, List.of(p1),
                "Ray's line starts at the center of the sphere and crosses the sphere in 1 point"
        );

        // TC17: Ray starts at sphere and goes outside (0 points)/////////////////////////////////////////////////////////////////////////////////
        assertNull(sphere.findIntersections(new Ray(new Point3D(1.900269628904,-0.1527708950857,0.4076464753776),
                new Vector(0.4619674124196,-0.0783933755287,0.2091810957139))), "Ray's starting at the sphere and goes through the center and outside ");

        // TC18: Ray starts after sphere (0 points)/////////////////////////////////////////////////////////////////////////////////
        assertNull(sphere.findIntersections(new Ray(new Point3D(2.3622370413236,-0.2311642706144,0.6168275710915),
                new Vector(0.532236400171, -0.0903176433379, 0.2409992358194))), "Ray's starting at the sphere and goes through the center and outside ");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point/////////////////////////////////////////////////////////////////////////////////
        p1 = new Point3D(1,0,1);
        result = sphere.findIntersections(new Ray(new Point3D(-1,1,1),
                new Vector(2,-1,0)));
        assertEquals(1, result.size(), "Wrong number of points - TC19");

        assertEquals(result, List.of(p1),
                "Ray's line starts before the tangent point and goes through her (tangent to the center of the sphere)"
        );

        // TC20: Ray starts at the tangent point/////////////////////////////////////////////////////////////////////////////////
        assertNull(sphere.findIntersections(new Ray(new Point3D(1,0,1),
                new Vector(2,-1,0))), "Ray's line starts at the tangent point");

        // TC21: Ray starts after the tangent point/////////////////////////////////////////////////////////////////////////////////
        assertNull(sphere.findIntersections(new Ray(new Point3D(1.777663406527548,-0.388831703263774,1),
                new Vector(2,-1,0))), "Ray's line starts after the tangent point");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line/////////////////////////////////////////////////////////////////////////////////
        assertNull(sphere.findIntersections(new Ray(new Point3D(1,0,2),
                new Vector(1.678259367806104, -1, 0))), "Ray's line starts at the tangent point");
    }
}