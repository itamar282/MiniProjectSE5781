package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * A class represents a Tube shape
 */
public class Tube implements Geometry{
    final protected Ray _axisRay;
    final protected double _radius;

    /**
     *
     * @param axisRay Represents the direction of the tube
     * @param radius Represents the radius of the tube
     */
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    @Override
    public Vector getNormal(Point3D p) {
        Point3D p0 = _axisRay.getP0();
        Vector v = _axisRay.getDir();
        Vector p0_p = p.subtract(p0);
        double t = v.dotProduct(p0_p);
        Point3D o = p0.add(v.scale(t));
        Vector o_p = p.subtract(o);
        o_p.normalize();
        return o_p;
    }

    /**
     *
     * @return The Ray representing the direction of the tube
     */
    public Ray getAxisRay() {
        return _axisRay;
    }

    /**
     *
     * @return The radius of the tube
     */
    public double getRadius() {
        return _radius;
    }
}
