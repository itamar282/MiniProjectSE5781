package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void getNormal() {
        Ray ray = new Ray(new Point3D(0,0,0), new Vector(0,0,1));
        Tube tube = new Tube(ray, 2);
        Vector normal = tube.getNormal(new Point3D(0, 2, 5));
        assertEquals(normal, new Vector(0,1,0), "ERROR: the normal vector is incorrect");
    }
}