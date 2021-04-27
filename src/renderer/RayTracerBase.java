package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * An abstract class for a ray tracer
 */
public abstract class RayTracerBase {
    protected Scene _scene;

    /**
     * @param scene A scene for the ray tracer
     */
    public RayTracerBase(Scene scene) {
        _scene = scene;
    }

    /**
     * @param ray A specific ray
     * @return The color of the closest intersected point with the ray
     */
    public abstract Color traceRay(Ray ray);
}
