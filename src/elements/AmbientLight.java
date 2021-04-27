package elements;

import primitives.Color;

/**
 * A class for ambient light
 */
public class AmbientLight {

    Color _intensity;

    /**
     *
     * @param Ia The power of the light)
     * @param Ka A parameter that indicates the intensity of the light
     */
    public AmbientLight(Color Ia, double Ka) {
        _intensity = Ia.scale(Ka);
    }

    /**
     *
     * @return The ambient light
     */
    public Color getIntensity() {
        return _intensity;
    }
}
