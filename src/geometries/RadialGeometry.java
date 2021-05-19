package geometries;

/**
 * A class representing a geometry with a radius
 */
public abstract class RadialGeometry extends Geometry{
    final protected double _radius;

    public RadialGeometry(double radius) {
        _radius = radius;
    }

    /**
     * @return The radius of the Sphere
     */
    public double getRadius() {
        return _radius;
    }
}
