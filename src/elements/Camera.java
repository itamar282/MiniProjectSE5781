package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * A class representing a camera.
 */
public class Camera {
    final Point3D _p0;
    final Vector _vUp;
    final Vector _vTo;
    final Vector _vRight;

    double _width;
    double _height;
    double _distance;

    /**
     *
     * @param p0 The current point of the camera
     * @param vTo The vector of the straight-direction of the camera
     * @param vUp The vector of the up-direction of the camera
     */
    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        if (vTo.getHead().equals(Point3D.ZERO) || vUp.getHead().equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be (0,0,0)");
        }
        _p0 = p0;
        _vTo = vTo.normalized();
        _vUp = vUp.normalized();
        if (!isZero(_vTo.dotProduct(_vUp))) {
            throw new IllegalArgumentException("vTo and vUp are not orthogonal");
        }
        // Calculating the right-direction vector:
        _vRight = _vTo.crossProduct(_vUp);
    }

    /**
     *
     * @param width The actual width of the screen (camera)
     * @param height The actual height of the screen (camera)
     * @return
     */
    public Camera setViewPlaneSize(double width, double height) {
        _width = width;
        _height = height;
        return this;
    }

    /**
     *
     * @param distance The distance of the camera from the plane
     * @return The camera with the new distance value
     */
    public Camera setDistance(double distance) {
        _distance = distance;
        return this;
    }

    /**
     *
     * @param nX Number of horizontal pixels
     * @param nY Number of vertical pixels
     * @param j The current horizontal pixel Where the ray is passing through
     * @param i The current vertical pixel Where the ray is passing through
     * @return
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point3D Pc = _p0.add(_vTo.scale(_distance)); // Pc = p0 + (d * vTo)
//        𝑅𝑦 = ℎ/𝑁𝑦
//        𝑅𝑥 = 𝑤/𝑁x
        double Ry = _height / nY;
        double Rx = _height / nX;

//        𝑦𝑖 = −(𝑖 – (𝑁𝑦 − 1)/2) ∙ 𝑅𝑦
//        𝑥𝑗 = (𝑗 – (𝑁𝑥 − 1)/2) ∙ 𝑅x
        double Yi = -(i - (nY - 1) / 2d) * Ry;
        double Xj = (j - (nX - 1) / 2d) * Rx;
        Point3D Pij = Pc;

        if (!isZero(Xj)) {
            Pij = Pij.add(_vRight.scale(Xj));
        }
        if (!isZero(Yi)) {
            Pij = Pij.add(_vUp.scale(Yi));
        }
        //Vector Vij = Pij.subtract(_p0);
        return new Ray(_p0, Pij.subtract(_p0));
    }

//Getters:
    public Point3D getP0() {
        return _p0;
    }

    public Vector get_vUp() {
        return _vUp;
    }

    public Vector get_vTo() {
        return _vTo;
    }

    public Vector get_vRight() {
        return _vRight;
    }
}
