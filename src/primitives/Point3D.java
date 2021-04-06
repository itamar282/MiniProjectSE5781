package primitives;

import java.awt.*;
import java.util.Objects;

/**
 * Basic object representing 3D point
 */

public class Point3D {
    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    static public Point3D ZERO = new Point3D(0, 0, 0);

    /**
     *
     * @param x represents the x coordinate of the point
     * @param y represents the y coordinate of the point
     * @param z represents the z coordinate of the point
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this(x.coord, y.coord, z.coord);
    }

    /**
     *
     * @param x represents the value of the x coordinate of the point
     * @param y represents the value of the y coordinate of the point
     * @param z represents the value of the z coordinate of the point
     */
    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    /**
     *
     * @param o The other points...
     * @return Whether the points have the same coordinates or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) && _y.equals(point3D._y) && _z.equals(point3D._z);
    }

    /**
     *
     * @return A unique code for each 3D point object
     */
    @Override
    public int hashCode() {
        return Objects.hash(_x.coord, _y.coord, _z.coord);
    }

    /**
     * @param point
     * @return the squared distance between 2 points
     */
    public double distanceSquared(Point3D point) {
        double x1 = this._x.coord;
        double x2 = point._x.coord;
        double y1 = this._y.coord;
        double y2 = point._y.coord;
        double z1 = this._z.coord;
        double z2 = point._z.coord;

        return ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
    }

    /**
     * @param point
     * @return the distance between 2 points
     */
    public double distance(Point3D point) {
        return Math.sqrt(distanceSquared(point));
    }

    /**
     *
     * @param vec
     * @return Addition of 2 vectors
     */
    public Point3D add(Vector vec) {
        return new Point3D(
                vec._head._x.coord + _x.coord,
                vec._head._y.coord + _y.coord,
                vec._head._z.coord + _z.coord);
    }

    /**
     *
     * @param point
     * @return The subtraction between 2 3D points
     */
    public Vector subtract(Point3D point) {
        return new Vector(new Point3D(
                _x.coord - point._x.coord,
                _y.coord - point._y.coord,
                _z.coord - point._z.coord));
    }
}
