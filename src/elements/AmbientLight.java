package elements;

import primitives.Color;

/**
 * A class for ambient light
 */
public class AmbientLight extends Light {
    /**
     *
     * @param Ia The power of the light)
     * @param Ka A parameter that indicates the intensity of the light
     */
    public AmbientLight(Color Ia, double Ka) {
        super(Ia.scale(Ka));
    }

    /**
     * Intensity = black
     */
    public AmbientLight(){
        super(Color.BLACK);
    }

}
