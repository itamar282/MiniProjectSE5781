package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * An interface representing the source of the light
 */
public interface LightSource {
    /**
     *
     * @param p A specific point
     * @return The amount of the intensity at the specific point
     */
    public Color getIntensity(Point3D p);

    /**
     *
     * @param p A specific point
     * @return The amount of the light at the specific point
     */
    public Vector getL(Point3D p);

    double getDistance(Point3D point);

}
