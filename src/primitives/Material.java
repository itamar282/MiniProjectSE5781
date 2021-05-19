package primitives;

public class Material {
    public double kD = 0;
    public double kS = 0;
    public int nShininess = 0;

    /**
     *
     * @param kD
     * @return The same object but with the new kD
     */
    public Material setkD(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     *
     * @param kS
     * @return The same object but with the new kS
     */
    public Material setkS(double kS) {
        this.kS = kS;
        return this;
    }

    /**
     *
     * @param nShininess
     * @return The same object but with the new shininess
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     *
     * @return The KD of the material
     */
    public double getkD() {
        return kD;
    }

    /**
     *
     * @return The KS of the material
     */
    public double getkS() {
        return kS;
    }

    /**
     *
     * @return The level of the shininess of the material
     */
    public int getnShininess() {
        return nShininess;
    }
}
