package elements;

import geometries.*;
import primitives.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing a camera-ray integration intersections
 */
public class CameraRayIntersectionIntegrationTests {

    /**
     * @param cam      Some camera
     * @param geo      Some geometry
     * @param expected The expected amount of intersected points of the camera and the geometries
     */
    void assertCountIntersections(Camera cam, Intersectable geo, int expected) {
        int count = 0;
        cam.setViewPlaneSize(3, 3).setDistance(1);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                var intersections = geo.findIntersections(cam.constructRayThroughPixel(3, 3, j, i));
                count += intersections == null ? 0 : intersections.size();
            }
        }
        assertEquals(count, expected, "result is:" + count + " but the expected value is:" + expected);
    }

    // Tests
    @Test
    public void CameraAndSphereIntegration() {
        Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, -1, 0));

        //Test - small sphere, 2 intersected points
        assertCountIntersections(cam1, new Sphere(1, new Point3D(0, 0, -3)), 2);

        //Test - big sphere, 18 intersected points
        assertCountIntersections(cam2, new Sphere(2.5, new Point3D(0, 0, -2.5)), 18);

        //Test - medium sphere, 10 intersected points
        assertCountIntersections(cam2, new Sphere(2, new Point3D(0, 0, -2)), 10);

        //Test - big sphere (actually the the size of the sphere when the camera is inside doesn't matter), camera inside sphere 9 intersected points
        assertCountIntersections(cam1, new Sphere(4, new Point3D(0, 0, -2)), 9);

        //Test - camera not looking at the sphere (starts after the sphere), 0 intersected points
        assertCountIntersections(cam1, new Sphere(1, new Point3D(0, 0, 1)), 0);
    }

    @Test
    public void CameraAndPlaneIntegration() {
        Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));

        //Test - plane is orthogonal to the camera, 9 intersected points
        assertCountIntersections(cam1, new Plane(
                        new Point3D(0, 0, -5),
                        new Vector(0, 0, 1)),
                9);

        //Test - plane has 9 intersected points but not orthogonal
        assertCountIntersections(cam1, new Plane(
                        new Point3D(0, 0, -3),
                        new Vector(0, 0.5, 1)),
                9
        );

        //Test - plane has 6 intersected points
        assertCountIntersections(cam1, new Plane(
                        new Point3D(0, 0, -3),
                        new Vector(0, 1, 1)),
                6);

    }

    @Test
    public void CameraAndTriangleIntegration() {
        Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));
        //Test - small triangle, orthogonal to the camera, 1 intersected point
        assertCountIntersections(cam1, new Triangle(
                        new Point3D(0, 1, -2),
                        new Point3D(1, -1, -2),
                        new Point3D(-1, -1, -2)),
                1);

        //Test - medium triangle, orthogonal to the camera, 2 intersected point
        assertCountIntersections(cam1, new Triangle(
                        new Point3D(0, 20, -2),
                        new Point3D(1, -1, -2),
                        new Point3D(-1, -1, -2)),
                2
        );
    }

}
