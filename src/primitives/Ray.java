package primitives;

import java.util.Objects;

/**
 * A class representing a collection of 3D points from 1 point to infinite (with a vector)
 */
public class Ray {
    final Point3D _p0;
    final Vector _dir;

    /**
     *
     * @param point Represents the edge of the ray
     * @param vec Represents the direction of the ray
     */
    public Ray(Point3D point, Vector vec){
        _p0 = point;
        _dir = vec.normalized();
    }

    public Point3D getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }

    /**
     *
     * @param o The other Ray object
     * @return Whether the Rays are same or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    /**
     *
     * @return A unique code for each Ray object
     */
    @Override
    public int hashCode() {
        return Objects.hash(_p0, _dir);
    }

    public Point3D getTargetPoint(double t) {
        Point3D result = _p0.add(_dir.scale(t));
        return result;
    }
}
