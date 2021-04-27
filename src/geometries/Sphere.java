package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * A class represents a Sphere shape with a 3D point (the center of the Sphere) and a radius
 */
public class Sphere extends RadialGeometry implements Geometry {
    final Point3D _center;

    /**
     * @param center Represents the center of the Sphere
     * @param radius Represents the radius of the Sphere
     */
    public Sphere(double radius, Point3D center) {
        super(radius);
        _center = center;
    }

    /**
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
     * @return The center of the Sphere
     */
    public Point3D getCenter() {
        return _center;
    }


    @Override
    public List<Point3D> findIntersections(Ray ray) {
        if (ray.getP0() == _center) {
            return List.of(ray.getTargetPoint(_radius));
        }
        Point3D p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector u = _center.subtract(p0);
        double tm = v.dotProduct(u);
        double d = Math.sqrt(alignZero(u.lengthSquared() - (tm * tm)));
        if (alignZero(d - _radius) > 0)
            return null;

        double th = Math.sqrt(alignZero((_radius * _radius) - (d * d)));

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        // If the point is on the surface of the sphere - then u get 2 same points
        if (isZero(_radius - d))
            t2 = -1; // remove 1 point

        if (t1 > 0 && t2 > 0) // return 2 points:
            return List.of(ray.getTargetPoint(t1), ray.getTargetPoint(t2));

        if (t1 > 0) // return 1 points - if t1 > 0 but t2 < 0:
            return List.of(ray.getTargetPoint(t1));

        if (t2 > 0) // return 1 points - if t2 > 0 but t1 < 0
            return List.of(ray.getTargetPoint(t2));

        return null;
    }
}
