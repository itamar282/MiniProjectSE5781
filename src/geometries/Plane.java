package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * A class represents a plane shape with a 3D point and a normal
 */
public class Plane implements Geometry{
    final Point3D _q0;
    final Vector _normal;

    /**
     *
     * @param point A point on the plane
     * @param normal Represents the normal of the plane
     */
    public Plane(Point3D point, Vector normal) {
        _q0 = point;
        _normal = normal;
    }

    /**
     *
     * @param p1 The first point on the plane
     * @param p2 The second point on the plane
     * @param p3 The third point on the plane
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _q0 = p1;
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        Vector v3 = v1.crossProduct(v2);
        v3.normalize();
        _normal = v3;
    }

    /**
     *
     * @return The normal of the plane
     */
    public Vector getNormal() {
        return _normal;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    /**
     *
     * @return A point on the plane
     */
    public Point3D getQ0() {
        return _q0;
    }
}
