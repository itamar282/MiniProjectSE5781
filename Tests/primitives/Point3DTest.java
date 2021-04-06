package primitives;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {


    Point3D p1 = new Point3D(1.0d, 2.0d, 3.0d);
    Point3D p2 = new Point3D(1.000000000000000001d, 2.0d, 3.0d);

    @Test
    void testEquals()
    {
        assertEquals(p1, p2);
    }

}