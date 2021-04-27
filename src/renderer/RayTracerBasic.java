package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

/**
 * A basic class for ray tracer
 */
public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * @param ray A specific ray
     * @return The color of the closest intersected point with the ray
     */
    @Override
    public Color traceRay(Ray ray) {
        var intersections = _scene.geometries.findIntersections(ray);
        if (intersections == null) {
            return _scene.background;
        }
        var closestPoint = ray.findClosestPoint(intersections);
        return calcColor(closestPoint);
    }

    /**
     * @param point A specific point
     * @return The color of the point
     */
    public Color calcColor(Point3D point) {
        return _scene.ambientLight.getIntensity();
    }
}
