package elements;

import primitives.Color;

/**
 * A class representing a power of light
 */
public abstract class Light {

    private Color _intensity;

    /**
     *
     * @param intensity The intensity of the light
     */
    protected Light(Color intensity) {
        _intensity = intensity;
    }

    /**
     *
     * @return The intensity of the light
     */
    public Color getIntensity() {
        return _intensity;
    }

}
