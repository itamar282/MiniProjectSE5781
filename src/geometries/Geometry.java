package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * A basic interface represents a Geometry shape
 */
public interface Geometry {
    public Vector getNormal(Point3D point);
}
