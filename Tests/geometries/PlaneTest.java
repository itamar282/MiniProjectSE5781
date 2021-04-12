package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PlaneTest {

    @Test
    void getNormal() {
        //TODO later
    }

    @Test
    void findIntersections() {
        Point3D p1;
        List<Point3D> result;
        Plane plane = new Plane(new Point3D(0, 0, 1), new Vector(-2, 0, 0));

     // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the plane
        p1 = new Point3D(0, 1, 0);
        result = plane.findIntersections(new Ray(new Point3D(2, 2, 3), new Vector(-2, -1, -3)));
        assertEquals(result, List.of(p1), "Ray's line intersects the plane in 1 point");

        // TC02: Ray does not intersect the plane
        assertNull(plane.findIntersections(new Ray(new Point3D(0, 2, 4),
                new Vector(0, 2, 4))), "Ray's line does not intersects the plane");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane
        // TC03: Ray's included in the plane
        assertNull(plane.findIntersections(new Ray(new Point3D(0, 1, 0),
                new Vector(0, 2, 4))), "Ray's line is laying on the plane");

        // TC04: Ray's not included in the plane - same as TC02
        assertNull(plane.findIntersections(new Ray(new Point3D(0, 2, 4),
                new Vector(0, 2, 4))), "Ray's line does not intersects the plane");

        // **** Group: Ray is orthogonal to the plane
        // TC05: Ray's starts before the plane
        p1 = new Point3D(0, 1, 0);
        result = plane.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(-2, 0, 0)));
        assertEquals(result, List.of(p1), "Ray's line intersects the plane in 1 point and orthogonal to the plane");

        // TC06: Ray's not starts inside the plane
        assertNull(plane.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(-2, 0, 0))),
                "Ray's line starts at the plane and orthogonal to the plane");

        // TC07: Ray starts after the plane
        assertNull(plane.findIntersections(new Ray(new Point3D(-1, 1, 1), new Vector(-2, 0, 0))),
                "Ray's line starts at the plane and orthogonal to the plane");

        // **** Group: other
        // TC08: Ray is neither orthogonal nor parallel to and begins at the plane
        assertNull(plane.findIntersections(new Ray(new Point3D(0, 0, 2), new Vector(1.95, -0.57, 1.48))),
                "Ray's line starts at the plane");

        // TC09: Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane (Q)
        assertNull(plane.findIntersections(new Ray(plane._q0, new Vector(1.95, -0.57, 1.48))),
                "Ray's line starts at the plane");

    }
}