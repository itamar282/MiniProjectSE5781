package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/**
 * Representing a scene with a background color, ambient light and geometries
 */
public class Scene {
    private final String _name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight(Color.LIGHT_GRAY, 1d);
    public Geometries geometries = null;

    /**
     * @param name The name of the scene
     */
    public Scene(String name) {
        _name = name;
        geometries = new Geometries();
    }

    /**
     * @param background The new background of the scene
     * @return The new scene with the new background
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * @param ambientLight The new ambient light of the scene
     * @return The new scene with the new ambient light
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * @param geometries The new geometries to add to the scene
     * @return The new scene with the new geometries
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
