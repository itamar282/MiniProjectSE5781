package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 * A basic interface representing an Intersectable object
 */
public interface Intersectable {
    /**
     *
     * @param ray A ray that intersecting or not intersecting the object
     * @return The intersected points of the ray and the object
     */
    List<Point3D> findIntersections(Ray ray);
}
