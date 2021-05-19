package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * A class represents a Tube shape
 */
public class Tube extends RadialGeometry{
    final protected Ray _axisRay;

    /**
     *
     * @param axisRay Represents the direction of the tube
     * @param radius Represents the radius of the tube
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        _axisRay = axisRay;
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

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return null;
    }
}
