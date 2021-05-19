package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * A basic interface represents a Geometry shape
 */
public abstract class Geometry implements Intersectable{
    protected Color emission = Color.BLACK;
    private Material _material = new Material();

    /**
     *
     * @return The emission color
     */
    public Color getEmission() {
        return emission;
    }

    /**
     *
     * @param emission The specific emission color
     * @return The Geometry object of the emission
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     *
     * @return The material of the geometry
     */
    public Material getMaterial() {
        return _material;
    }

    public Geometry setMaterial(Material material) {
        _material = material;
        return this;
    }

    /**
     *
     * @param point The point of the wanted normal
     * @return The normal of the object at this point
     */
    public abstract Vector getNormal(Point3D point);
}
