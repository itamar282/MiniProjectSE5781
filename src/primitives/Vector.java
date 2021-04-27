package primitives;

import java.util.Objects;

/**
 * A class represents a vector
 */
public class Vector {
    /**
     *
     * @param head A 3D point represents the top of the vector
     */
    Point3D _head;

    /**
     *
     * @return the vector represented by a 3D point
     */
    public Point3D getHead() {
        return new Point3D(_head._x, _head._y, _head._z);
    }

    /**
     *
     * @param x The x coordinate of the vector
     * @param y The y coordinate of the vector
     * @param z The z coordinate of the vector
     */
    public Vector(double x, double y, double z) {
        this(new Point3D(x, y, z));
    }

    /**
     *
     * @param head represents the top of the vector
     */
    public Vector(Point3D head) {
        if (head.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be 0,0,0");
        }

        _head = new Point3D(head._x, head._y, head._z);
    }

    /**
     *
     * @param vec The other vector
     * @return A new vector with the values of the addition of the 2 vectors
     */
    public Vector add(Vector vec) {
        return new Vector(_head.add(vec));
    }

    /**
     *
     * @param vec The subtracted vector
     * @return A new vector with the values of the subtraction of the 2 vectors
     */
    public Vector subtract(Vector vec) {
        return _head.subtract(vec._head);
    }

    /**
     *
     * @param scalar The number that the coordinates of the vector are multiplied at
     * @return A vector with values of the multiplication of each coordinate * scalar
     */
    public Vector scale(double scalar) {
        return new Vector(scalar * _head._x.coord, scalar * _head._y.coord, scalar * _head._z.coord);
    }

    /**
     *
     * @param vec The other vector of the dot product
     * @return  A scalar multiplication
     */
    public double dotProduct(Vector vec) {
        double u1 = _head._x.coord;
        double v1 = vec._head._x.coord;
        double u2 = _head._y.coord;
        double v2 = vec._head._y.coord;
        double u3 = _head._z.coord;
        double v3 = vec._head._z.coord;

        return (u1 * v1) + (u2 * v2) + (u3 * v3);
    }

    /**
     *
     * @param vec The other vector of the cross product
     * @return The cross product of the 2 vectors
     */
    public Vector crossProduct(Vector vec) {
        double scalar = vec._head._x.coord / this._head._x.coord;
//        if (this.scale(scalar).equals(vec)
//          throw new IllegalArgumentException("Parallel vectors");
        double u1 = _head._x.coord;
        double v1 = vec._head._x.coord;
        double u2 = _head._y.coord;
        double v2 = vec._head._y.coord;
        double u3 = _head._z.coord;
        double v3 = vec._head._z.coord;

        Point3D newNormal = new Point3D(u2 * v3 - u3 * v2, u3 * v1 - u1 * v3, u1 * v2 - u2 * v1);

        return new Vector(newNormal);
    }

    /**
     *
     * @return The squared length of the vector
     */
    public double lengthSquared() {
        return _head.distanceSquared(Point3D.ZERO);
    }

    /**
     *
     * @return The length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     *
     * @return THIS vector and changes him to the normalized vector
     */
    public Vector normalize() {
        double length = length();
        _head = new Point3D(
                _head._x.coord / length,
                _head._y.coord / length,
                _head._z.coord / length);
        return this;
    }

    /**
     *
     * @return A new vector represents the normalized vector of THIS vector
     */
    public Vector normalized() {
        return new Vector(new Point3D(_head._x, _head._y, _head._z)).normalize();
    }

    /**
     *
     * @param o The other Vector
     * @return Whether the vectors have the same values or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    /**
     *
     * @return A unique code for each vector object
     */
    @Override
    public int hashCode() {
        return Objects.hash(_head);
    }
}
