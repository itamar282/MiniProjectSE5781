package primitives;

public class Material {
    /**
     * Diffusive level of the material
     */
    public double kD = 0;

    /**
     * Specular level of the material
     */
    public double kS = 0;

    /**
     * Transparency level of the material
     */
    public double kT = 0;

    /**
     * Reflection level of the material
     */
    public double kR = 0;

    /**
     * Reflection level of the material
     */
    public int nShininess = 0;

    /**
     *
     * @param kD The diffusive level of the material
     * @return The same object but with the new kD
     */
    public Material setkD(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     *
     * @param kS The specular level of the material
     * @return The same object but with the new kS
     */
    public Material setkS(double kS) {
        this.kS = kS;
        return this;
    }

    /**
     *
     * @param kT The level of Transparency of the material
     * @return The same material object but with the new kT
     */
    public Material setkT(double kT) {
        this.kT = kT;
        return this;
    }

    /**
     *
     * @param kR The level of Reflection of the material
     * @return The same material object but with the new kR
     */
    public Material setkR(double kR) {
        this.kR = kR;
        return this;
    }

    /**
     *
     * @param nShininess The level of shininess of the material
     * @return The same object but with the new shininess
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     *
     * @return The reflectance level of the material
     */
    public double getkT() {
        return kT;
    }

    /**
     *
     * @return The Transparency level of the material
     */
    public double getkR() {
        return kR;
    }

    /**
     *
     * @return The diffusive level of the material
     */
    public double getkD() {
        return kD;
    }

    /**
     *
     * @return The specular level of the material
     */
    public double getkS() {
        return kS;
    }

    /**
     *
     * @return The shininess level of the material
     */
    public int getnShininess() {
        return nShininess;
    }
}
