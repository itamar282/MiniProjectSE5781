package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A basic interface representing an Intersectable object
 */
public interface Intersectable {

    /**
     *  A class representing point on a geometry object
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;
        public GeoPoint(Geometry geometry, Point3D point){
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }
    }

    /**
     *
     * @param ray A ray that intersecting or not intersecting the object
     * @return The intersected points of the ray and the object
     */
    default List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList
                .stream()
                .map(gp -> gp.point)
                .collect(Collectors.toList());
    }

    List<GeoPoint> findGeoIntersections(Ray ray);
}
