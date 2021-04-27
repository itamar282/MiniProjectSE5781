package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * A basic interface represents a Geometry shape
 */
public interface Geometry extends Intersectable{
    /**
     *
     * @param point The point of the wanted normal
     * @return The normal of the object at this point
     */
    public Vector getNormal(Point3D point);
}
