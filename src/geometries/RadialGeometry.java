package geometries;

public class RadialGeometry {
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
