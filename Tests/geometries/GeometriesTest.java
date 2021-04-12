package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class GeometriesTest {

    @Test
    void findIntersections() {
        Geometry sphere = new Sphere(1, Point3D.ZERO);
        Geometry triangle = new Triangle(new Point3D(2,6,0), new Point3D(0,6,3), new Point3D(-2,6,0));
        Geometry plane = new Plane(new Point3D(0,-5,0), new Vector(0,1,0));
        Intersectable geometries = new Geometries(sphere, triangle, plane);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Some Geometries have intersections
        List<Point3D> intersections = geometries.findIntersections(new Ray(new Point3D(0,6,3), new Vector(0,-6,-3)));
        assertEquals(intersections.size(), 3, "Number of intersected points is wrong");


        // =============== Boundary Values Tests ==================
        // TC11: No geometry has intersections
        assertNull(geometries.findIntersections(new Ray(new Point3D(0,6,3), new Vector(2, 0, -3)))
                , "Empty list");

        // TC11: Only one geometry has intersection/s
        intersections = geometries.findIntersections(new Ray(new Point3D(0,6,5), new Vector(-2,-11,-5)));
        assertEquals(intersections.size(), 1,
                "Number of intersected points is wrong when only 1 geometry is intersected");

        // TC11: All of the geometries have intersection/s
        intersections = geometries.findIntersections(new Ray(new Point3D(0,7,2), new Vector(0,-11,-4.5)));
        assertEquals(intersections.size(), 4,
                "Number of intersected points is wrong when only 1 geometry is intersected");

        // TC11: Empty geometries list
        geometries = new Geometries();
        intersections = geometries.findIntersections(new Ray(new Point3D(1,1,1), new Vector(1,1,1)));
        assertNull(intersections,
                "Number of intersected points is wrong when no geometries is in the list");
    }
}