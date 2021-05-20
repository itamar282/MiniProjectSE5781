package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

    private Point3D _position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    public PointLight setPosition(Point3D position) {
        _position = position;
        return this;
    }

    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     *
     * @param intensity The intensity of the light
     * @param position The position of the source of the light
     */
    public PointLight(Color intensity, Point3D position) {
        super(intensity);
        _position = position;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double d = p.distance(_position);
        Color iL = getIntensity().reduce(kC + kL*d + kQ*d*d);
        return iL;
    }

    @Override
    public Vector getL(Point3D p) {
        return new Vector(p).subtract(new Vector(_position)).normalize();
    }

    @Override
    public double getDistance(Point3D point) {
        return _position.distance(point);
    }
}
