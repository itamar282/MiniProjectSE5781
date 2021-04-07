package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
        Point3D v = new Point3D(0,2,0);
        Sphere sphere = new Sphere(new Point3D(0,0,0), 2);
        assertEquals(sphere.getNormal(v), new Vector(0,1,0), "ERROR: the getNormal function not working well");
    }
}