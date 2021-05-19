package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Representing a scene with a background color, ambient light and geometries
 */
public class Scene {
    private final String _name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight(Color.BLACK, 1);
    public Geometries geometries = null;
    public List<LightSource> _lights = new LinkedList<LightSource>();

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

    /**
     *
     * @param lights A multiple (or just one) light objects
     * @return The same scene but with the new lights
     */
    public Scene setLights(List<LightSource> lights) {
        _lights = lights;
        return this;
    }
}
