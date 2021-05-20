package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{

    private Vector _direction;

    /**
     *
     * @param intensity The intensity of the light
     * @param direction The direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        _direction = direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }

    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }
}
