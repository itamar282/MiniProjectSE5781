package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight{

    protected Vector _direction;

    /**
     *
     * @param intensity The intensity of the light
     * @param position The position of the source of the light
     * @param direction The direction of the light
     */
    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        _direction = direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        double max = _direction.dotProduct(getL(p));
        if (max < 0){
            max = 0;
        }
        Color iL = super.getIntensity(p).scale(max);
        return iL;
    }
}
