package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * A class represents a Triangle
 */
public class Triangle extends Polygon {
    /**
     *
     * @param vertices Representing a list of 3D points that the polygon is created from
     */
    public Triangle(Point3D... vertices) {
        super(vertices);
    }

    public List<Point3D> findIntersections(Ray ray) {
        Point3D p1 = vertices.get(0);
        Point3D p2 = vertices.get(1);
        Point3D p3 = vertices.get(2);
        Plane plane = new Plane(p1, p2, p3);
        if (plane.findIntersections(ray) == null)
            return null;
        Point3D p0 = ray.getP0();

        Vector v = ray.getDir();
        Vector v1 = p1.subtract(p0);
        Vector v2 = p2.subtract(p0);
        Vector v3 = p3.subtract(p0);

        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        int sign1 = v.dotProduct(n1) > 0 ? 1 : -1;
        int sign2 = v.dotProduct(n2) > 0 ? 1 : -1;
        int sign3 = v.dotProduct(n3) > 0 ? 1 : -1;

        if (sign1 != sign2 || sign1 != sign3 || sign2 != sign3)
            return null;

        return plane.findIntersections(ray);
    }
}
