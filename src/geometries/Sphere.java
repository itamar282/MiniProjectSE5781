package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * A class represents a Sphere shape with a 3D point (the center of the Sphere) and a radius
 */
public class Sphere implements Geometry{
    final Point3D _center;
    final double _radius;

    /**
     *
     * @param center Represents the center of the Sphere
     * @param radius Represents the radius of the Sphere
     */
    public Sphere(Point3D center, double radius) {
        _center = center;
        _radius = radius;
    }

    /**
     *
     * @param point A specific point on the sphere
     * @return The normal of the point
     */
    @Override
    public Vector getNormal(Point3D point) {
        Vector v = point.subtract(_center);
        v.normalize();
        return v;
    }

    /**
     *
     * @return The center of the Sphere
     */
    public Point3D getCenter() {
        return _center;
    }

    /**
     *
     * @return The radius of the Sphere
     */
    public double getRadius() {
        return _radius;
    }
}
