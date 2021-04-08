package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 *  A class represents a Cylinder mesh with a height a Ray and a radius (extends the Tube class)
 */
public class Cylinder extends Tube{
    int wow;
    final double _height;

    /**
     *
     * @param axisRay The direction of the Cylinder
     * @param radius The radius of the Cylinder
     * @param height The height of the Cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        _height = height;
    }

    /**
     *
     * @param point Represents a specific point on the Cylinder
     * @return The normal vector of the point
     */
    @Override
    public Vector getNormal(Point3D point) {
        return super.getNormal(point);
    }

    /**
     *
     * @return The height of the Cylinder
     */
    public double getHeight() {
        return _height;
    }
}
