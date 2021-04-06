package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * A class represents a Tube shape
 */
public class Tube implements Geometry{
    protected Ray _axisRay;
    protected double _radius;

    /**
     *
     * @param axisRay Represents the direction of the tube
     * @param radius Represents the radius of the tube
     */
    public Tube(Ray axisRay, double radius) {
        this._axisRay = axisRay;
        this._radius = radius;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
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
